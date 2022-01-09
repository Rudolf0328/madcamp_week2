package com.example.madcamp_week2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.madcamp_week2.ui.home.RetrofitUser
import com.example.madcamp_week2.ui.home.registrationresult
import com.example.madcamp_week2.ui.home.testresult
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import java.io.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class MainRegistration : AppCompatActivity() {

    val REQUEST_CODE = 0
    lateinit var uri:Uri
    lateinit var userThumbnail: String

    private fun gallery() {
        var intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,REQUEST_CODE)
    }

    fun getFullPath(uri: Uri) :String? {
        val context = this
        val contentResolver = context.contentResolver ?: return null

        // Create file path inside app's data dir
        val filePath = (context.applicationInfo.dataDir + File.separator
                + System.currentTimeMillis())
        val file = File(filePath)
        try {
            val inputStream = contentResolver.openInputStream(uri) ?: return null
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            /*  절대 경로를 getGps()에 넘겨주기   */
            getGps(file.getAbsolutePath())
        }
        return file.getAbsolutePath()
    }

    fun getGps(photoPath: String) {
        var exif: ExifInterface?= null
        try{
            exif = ExifInterface(photoPath)
        }catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun bitmapToFile(bitmap:Bitmap, path : String?) : File {
        val file = File(path)
        var out : OutputStream
        file.createNewFile()
        out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,out)
        out.close()
        return file
    }

//    fun bitmapToByteArray( bitmap:Bitmap): ByteArray {
//        val stream = ByteArrayOutputStream() ;
//        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
//        val byteArray = stream.toByteArray();
//        return byteArray
//    }

    private fun copyStreamToFile(uri: Uri): File {
        val outputFile = File.createTempFile("temp", null)

        contentResolver.openInputStream(uri)?.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
        return outputFile
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //startActivityForResult를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과값
        super.onActivityResult(requestCode, resultCode, data)


        //사진을 성공적으로 가져 온 경우
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK ) {
            uri = data?.data!!
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                Intent.FLAG_GRANT_READ_URI_PERMISSION
//                baseContext.grantUriPermission(baseContext.applicationContext.packageName,uri!!,Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            }
//            Log.e("uri", uri.toString())
//
//            val path = getFullPath(uri!!)
//            Log.e("path",path!!)
//            var input = this.contentResolver.openInputStream(uri!!)
//            var image = BitmapFactory.decodeStream(input)
//            val file : File = bitmapToFile(image,path)
//            this.intent.putExtra("photo",uri)
//
//            Log.e("api", file.name)
//
//            var requestBody = file.asRequestBody("image/*".toMediaType())
//            var body = MultipartBody.Part.createFormData("image",file.name,requestBody)

            val file = copyStreamToFile(uri)
            val filePart = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody())



            val timeset = OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS).callTimeout(100, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build()

            val retrofit = Retrofit.Builder().baseUrl("https://api.imgur.com").client(timeset).addConverterFactory(
                GsonConverterFactory.create()).build()
            var server = retrofit.create(Retrofit_imgur::class.java)

//            Log.e("test", body.toString())

            //유저 정보 불러오기
            server.postRequest("Client-ID 4c6e8e69f2f3062", filePart).enqueue((object: Callback<ImgurResponse> {
                override fun onFailure(call: Call<ImgurResponse>, t: Throwable) {
                    Log.e("imgur", "fail")
                    t.printStackTrace()
                }
                override fun onResponse(call: Call<ImgurResponse>, response: Response<ImgurResponse>) {
                    if (response?.body() != null ) {
                        Log.e("imgur", "success")
                        Log.e("reply", response.body().toString())

                    }else{
                        Log.e("imgur", "no")
                        Log.e("reply", response.body().toString())

                    }
                }
            }))



        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registration)

        lateinit var email:String

        val input_nickname = findViewById<EditText>(R.id.join_name)
        val input_email = findViewById<EditText>(R.id.join_email)

        val emailcheckbutton = findViewById<Button>(R.id.check_button)
        val regbutton = findViewById<Button>(R.id.join_button)
        val delbutton = findViewById<Button>(R.id.delete)
        val photo_button = findViewById<Button>(R.id.photo_button)


        var checked = false

        emailcheckbutton.setOnClickListener {
            email = input_email.text.toString()
            val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                GsonConverterFactory.create()).build()
            var server = retrofit.create(RetrofitUser::class.java)

            //유저 정보 불러오기
            server.test(email).enqueue((object: Callback<testresult> {
                override fun onFailure(call: Call<testresult>, t: Throwable) {
                    Log.e("response1", "error")
                    t.printStackTrace()
                }
                override fun onResponse(call: Call<testresult>, response: Response<testresult>) {
                    if (response?.body() != null ) {
                        Toast.makeText(baseContext, email + "는 이미 존재하는 이메일입니다", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(baseContext, email + "는 사용 가능한 이메일입니다", Toast.LENGTH_SHORT).show()
                        checked = true
                    }
                }
            }))

        }

        regbutton.setOnClickListener{
            Log.e("uri", uri.toString())
            if(!checked){
                Toast.makeText(baseContext, "이메일 체크해주세요", Toast.LENGTH_SHORT).show()
            }else {

                var nickname = input_nickname.text
                val email = input_email.text

                val retrofit =
                    Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                        GsonConverterFactory.create()
                    ).build()
                var server = retrofit.create(RetrofitUser::class.java)

                Log.e("please", "please")
                Log.e("please", uri.toString())
                val bitmap:Bitmap
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

//                val jebal = bitmapToByteArray(bitmap)
//                Log.e("please", uri.toString())


                //유저 정보 불러오기
                server.postRequest(nickname.toString(), "null", email.toString())
                    .enqueue((object : Callback<registrationresult> {
                        override fun onFailure(call: Call<registrationresult>, t: Throwable) {
                            Toast.makeText(baseContext, "회원가입실패", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<registrationresult>,
                            response: Response<registrationresult>
                        ) {
                            if (response.body() == null) {
                                Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()

                            } else {
                                Toast.makeText(baseContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                                val nextIntent = Intent(baseContext, MainActivity::class.java)
                                startActivity(nextIntent)

                            }
                        }
                    }))
            }


        }

        delbutton.setOnClickListener {
            val nextIntent = Intent(baseContext, MainActivity::class.java)
            startActivity(nextIntent)
        }

        photo_button.setOnClickListener {
            gallery()
        }





    }
}