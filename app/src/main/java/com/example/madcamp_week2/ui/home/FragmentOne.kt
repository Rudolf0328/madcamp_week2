package com.example.madcamp_week2.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madcamp_week2.ImgurResponse
import com.example.madcamp_week2.MainActivity
import com.example.madcamp_week2.R
import com.example.madcamp_week2.Retrofit_imgur
import com.kakao.sdk.user.UserApiClient
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOne : Fragment() {
    lateinit var id: String
    lateinit var nickName: String
    lateinit var userThumnail: String
    val REQUEST_CODE = 0
    lateinit var uri: Uri
    lateinit var image_FeedPost:ImageView
    lateinit var imageUploadButton:Button
    lateinit var content_FeedPost:String
    lateinit var one_imgv_profile_PopUp:ImageView



    private fun gallery() {
        Toast.makeText(requireContext(), "사진이 업로드되기까지 기다려주세요", Toast.LENGTH_SHORT).show()
        var intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,REQUEST_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //startActivityForResult를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과값
        super.onActivityResult(requestCode, resultCode, data)

        //사진을 성공적으로 가져 온 경우
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK ) {
            Log.e("reach here", "test")
            uri = data?.data!!

            image_FeedPost.setImageURI(uri)
            imageUploadButton.visibility = View.INVISIBLE
            image_FeedPost.visibility = View.VISIBLE
        }

        if (requestCode == 1 && resultCode == Activity.RESULT_OK ) {

        }
    }

    private fun copyStreamToFile(uri: Uri): File {
        val outputFile = File.createTempFile("temp", null)

        requireContext().contentResolver.openInputStream(uri)?.use { input ->
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var Feedlist = ArrayList<Feed>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        Feedlist = ArrayList<Feed>()
        val v = inflater.inflate(R.layout.fragment_one, container, false)
        var mAdapter = CustomAdapter(v.context, Feedlist)
        val recycler_view = v.findViewById<RecyclerView>(R.id.one_rcv_list)
        val image = v.findViewById<ImageView>(R.id.one_imgv_profile)
        val logout = v.findViewById<ImageButton>(R.id.one_btn_logout)
        val userProfile = v.findViewById<ConstraintLayout>(R.id.userProfile)

        val textview_userNick = v.findViewById<TextView>(R.id.one_tv_name)
        val textview_userId = v.findViewById<TextView>(R.id.one_tv_id)
        val feed_add = v.findViewById<ImageButton>(R.id.feed_post)
        val one_tv_post_num = v.findViewById<TextView>(R.id.one_tv_post_num)

        id = requireActivity().intent.getStringExtra("userEmail")!!
        nickName = requireActivity().intent.getStringExtra("userName")!!
        userThumnail = requireActivity().intent.getStringExtra("userThumnail")!!

        Log.e("frag1", id!!);
        Log.e("frag1", nickName!!)
        Log.e("frag1", userThumnail!!)

        textview_userId.text = id
        textview_userNick.text = nickName
        if(userThumnail != "null"){
            Glide.with(image).load(userThumnail).circleCrop().into(image)
        }else {
            Glide.with(image).load(R.drawable.ic_baseline_account_box_24).circleCrop().into(image)
        }


        //피드 불러오기
        val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
            GsonConverterFactory.create()).build()
        var server = retrofit.create(RetrofitUser::class.java)

        //유저 정보 불러오기
        server.getUser(id).enqueue((object: Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                if(response.body() == null){
                    Log.e("get user", response.body().toString())
                }else{
                    Log.e("get user", response.body().toString())
                    Log.e("get user", response.body()!!.feeds.size.toString())
                    one_tv_post_num.text = response.body()!!.feeds.size.toString()

                    val FeedIdList = response.body()!!.feeds

                    for(i: Int in 0..response.body()!!.feeds.size-1){
                        val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()).build()
                        var server = retrofit.create(RetrofitUser::class.java)

                        Log.e("why?", FeedIdList[i])

                        //유저 정보 불러오기
                        server.getFeed(FeedIdList[i]).enqueue((object: Callback<Feed> {
                            override fun onFailure(call: Call<Feed>, t: Throwable) {
                                Log.e("feed response1", response.body().toString())
                            }
                            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                                if(response.body() == null){
                                    Log.e("feed response2", response.body().toString())
                                }else{

                                    mAdapter.addItem(response.body()!!)


                                }
                            }
                        }))

                    }

                }
            }
        }))


//        mAdapter = CustomAdapter(v.context, Feedlist)
        recycler_view.adapter = mAdapter

        val layout = LinearLayoutManager(requireContext())
        recycler_view.layoutManager = layout
        recycler_view.setHasFixedSize(true)


        //로그아웃, 짧게 누를 시 그냥 로그인창으로 이동
        logout.setOnClickListener {
            UserApiClient.instance.logout { error->
                if(error != null){
                    Log.e("kakao accout", "logout fail")
                }else{
                    Log.e("kakao account", "logout success")
                    val nextIntent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(nextIntent)
                }
            }
        }

        //로그아웃, 길게 누를 시 계정 삭제
        logout.setOnLongClickListener {
            val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                GsonConverterFactory.create()).build()
            var server = retrofit.create(RetrofitUser::class.java)

            //유저 정보 불러오기
            server.deleteUser(id).enqueue((object: Callback<deleteUserResult> {
                override fun onFailure(call: Call<deleteUserResult>, t: Throwable) {
                    Toast.makeText(requireContext(), "계정삭제에 실패했습니다", Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<deleteUserResult>, response: Response<deleteUserResult>) {
                    if(response.body() == null){
                        Toast.makeText(requireContext(), "계정삭제에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), "계정삭제에 성공했습니다", Toast.LENGTH_SHORT).show()
                        val nextIntent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(nextIntent)
                    }
                }
            }))
            return@setOnLongClickListener true
        }

        //피드 올리기
        feed_add.setOnClickListener {

            Log.e("feed add", "start")
            val FeedPost = layoutInflater.inflate(R.layout.view_item_layout_feed_post, null)
            val alertDialog = AlertDialog.Builder(v.context)
                .setView(FeedPost)
                .create()

            imageUploadButton = FeedPost.findViewById<Button>(R.id.photo_button_feedpost)
            image_FeedPost = FeedPost.findViewById<ImageView>(R.id.userImg_feedpost)
            val text_button = FeedPost.findViewById<EditText>(R.id.content_feedpost)
            val user_FeedPost = FeedPost.findViewById<TextView>(R.id.userName_feedpost)
            val sent_FeedPost = FeedPost.findViewById<Button>(R.id.sent_FeedPost)

            user_FeedPost.text = nickName

            imageUploadButton.setOnClickListener {
                Log.e("image button", "clicked!")
                gallery()
            }

            sent_FeedPost.setOnClickListener {

                var image_post = "null"
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
                            val data: ImgurResponse = response.body()!!
                            image_post = data.data.link
                            Toast.makeText(requireContext(), "사진이 업로드되었습니다!", Toast.LENGTH_SHORT).show()

                            val nickName_post = nickName
                            val today = LocalDate.now()
                            val Strnow = today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
                            val time_post = Strnow
                            val content_post =text_button.text.toString()

                            Log.e("post", id)
                            Log.e("post", image_post)
                            Log.e("post", nickName_post)
                            Log.e("post", time_post)
                            Log.e("post", content_post)





                            val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                                GsonConverterFactory.create()).build()
                            var server = retrofit.create(RetrofitUser::class.java)

                            //유저 정보 불러오기
                            server.postFeed(id, nickName_post,content_post, image_post, time_post).enqueue((object: Callback<feedPostResult> {
                                override fun onFailure(call: Call<feedPostResult>, t: Throwable) {
                                    Log.e("response1", "error")
                                    Toast.makeText(requireContext(), "피드를 올리지 못했습니다", Toast.LENGTH_SHORT).show()
                                    t.printStackTrace()
                                }
                                override fun onResponse(call: Call<feedPostResult>, response: Response<feedPostResult>) {
                                    if (response?.body() != null ) {
                                        Toast.makeText(requireContext(), "피드를 올렸습니다", Toast.LENGTH_SHORT).show()
                                        alertDialog.dismiss()
                                    }else{
                                        Toast.makeText(requireContext(), "피드를 올리지 못했습니다", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }))



                        }else{
                            Log.e("imgur", "no")
                            Log.e("reply", response.body().toString())

                        }
                    }
                }))





//                val nickName_post = nickName
//                val today = LocalDate.now()
//                val Strnow = today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
//                val time_post = Strnow
//                val content_post =text_button.text.toString()
//
//                Log.e("post", image_post)
//                Log.e("post", nickName_post)
//                Log.e("post", time_post)
//                Log.e("post", content_post)

            }

            alertDialog.show()


        }

        //유저 정보 수정
        userProfile.setOnClickListener {
            Log.e("userProfile", "click")
            val userProfilePopUp = layoutInflater.inflate(R.layout.user_profile, null)
            one_imgv_profile_PopUp = userProfilePopUp.findViewById<ImageView>(R.id.one_imgv_profile)
            val one_tv_name_PopUp = userProfilePopUp.findViewById<TextView>(R.id.one_tv_name)
            val one_tv_id_PopUp = userProfilePopUp.findViewById<TextView>(R.id.one_tv_id)
            val one_tv_post_num_PopUp = userProfilePopUp.findViewById<TextView>(R.id.one_tv_post_num)
            val userProfile_edit_photo = userProfilePopUp.findViewById<TextView>(R.id.userProfile_edit_photo)
            val userProfile_edit_send = userProfilePopUp.findViewById<TextView>(R.id.userProfile_edit_send)

            if(userThumnail != "null"){
                Glide.with(one_imgv_profile_PopUp).load(userThumnail).circleCrop().into(one_imgv_profile_PopUp)
            }else {
                Glide.with(one_imgv_profile_PopUp).load(R.drawable.ic_baseline_account_box_24).circleCrop().into(one_imgv_profile_PopUp)
            }

            one_tv_name_PopUp.text = nickName
            one_tv_id_PopUp.text = id
            one_tv_post_num_PopUp.text = one_tv_post_num.text



            val alertDialog = AlertDialog.Builder(v.context)
                .setView(userProfilePopUp)
                .create()


            alertDialog.show()

            userProfile_edit_photo.setOnClickListener{
                gallery()
                uri

            }

        }

        return v
    }

}