package com.example.madcamp_week2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp_week2.ui.home.RetrofitUser
import com.example.madcamp_week2.ui.home.registrationresult
import com.example.madcamp_week2.ui.home.testresult
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit


class MainRegistration : AppCompatActivity() {

    val REQUEST_CODE = 0
    lateinit var uri:Uri
    var userThumbnail = "null"

    //갤러리열기
    private fun gallery() {
        Toast.makeText(baseContext, "사진이 업로드되기까지 기다려주세요", Toast.LENGTH_SHORT).show()
        var intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,REQUEST_CODE)
    }

    // uri에서 file 변환
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

            val file = copyStreamToFile(uri)
            val filePart = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody())
            val timeset = OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS).callTimeout(100, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build()


            //갤러리 사진 imgur 서버에 올린 후 url 받아오기
            val retrofit = Retrofit.Builder().baseUrl("https://api.imgur.com").client(timeset).addConverterFactory(
                GsonConverterFactory.create()).build()
            var server = retrofit.create(Retrofit_imgur::class.java)


            server.postRequest("Client-ID 4c6e8e69f2f3062", filePart).enqueue((object: Callback<ImgurResponse> {
                override fun onFailure(call: Call<ImgurResponse>, t: Throwable) {
                    Log.e("imgur", "fail")
                    t.printStackTrace()
                }
                override fun onResponse(call: Call<ImgurResponse>, response: Response<ImgurResponse>) {
                    if (response?.body() != null ) {
                        Log.e("imgur", "success")
                        val data:ImgurResponse = response.body()!!
                        userThumbnail = data.data.link
                        Toast.makeText(baseContext, "사진이 업로드되었습니다!", Toast.LENGTH_SHORT).show()

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



                //유저 정보 불러오기
                server.postRequest(nickname.toString(), userThumbnail, email.toString())
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