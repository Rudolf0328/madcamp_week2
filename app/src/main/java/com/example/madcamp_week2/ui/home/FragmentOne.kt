package com.example.madcamp_week2.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.content.res.AssetManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madcamp_week2.MainActivity
import com.example.madcamp_week2.R
import com.google.android.material.button.MaterialButton
import com.kakao.sdk.user.UserApiClient
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path


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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var Feedlist = ArrayList<Feed>()

//    var Feedlist = arrayListOf<Feed>(
//        Feed("IU","전주시", "010-1111-1111","user_img_01"),
//        Feed("홍길동", "서울시","010-1234-5678", "user_img_02"),
//        Feed("김영수", "광주시", "010-0000-0000", "user_img_03")
//    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_one, container, false)
        val recycler_view = v.findViewById<RecyclerView>(R.id.recycler_view)
        val image = v.findViewById<ImageView>(R.id.loadingImage)
        val logout = v.findViewById<Button>(R.id.logout)

        val textview_userNick = v.findViewById<TextView>(R.id.textView1)
        val textview_userId = v.findViewById<TextView>(R.id.textView2)

        id = requireActivity().intent.getStringExtra("userEmail")!!
        nickName = requireActivity().intent.getStringExtra("userName")!!
        userThumnail = requireActivity().intent.getStringExtra("userThumnail")!!

        Log.e("frag1", id!!);
        Log.e("frag1", nickName!!)
        Log.e("frag1", userThumnail!!)

        textview_userId.text = id
        textview_userNick.text = nickName
        if(userThumnail == "none"){

        }else {
            Glide.with(image).load(userThumnail).circleCrop().into(image)
        }
//        Glide.with(image).load(userThumnail).circleCrop().into(image)


//        val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(GsonConverterFactory.create()).build()
//        var server = retrofit.create(RetrofitUser::class.java)

        //유저 정보 불러오기
//        server.test(id).enqueue((object:Callback<testresult>{
//            override fun onFailure(call: Call<testresult>, t: Throwable) {
//                Log.e("response1", "error")
//                t.printStackTrace()
//            }
//            override fun onResponse(call: Call<testresult>, response: Response<testresult>) {
//                if (response?.body() != null ) {
//                    val result = response?.body()!!
//                    nickName = result.nickName
//                    userThumnail = result.profile
//                    textview_userId.text = id
//                    textview_userNick.text = nickName
//                    Glide.with(image).load(userThumnail).circleCrop().into(image)
//                }else{
//                    Log.e("111", "null")
//                }
//            }
//        }))






//        server.postRequest(userName, userThumnail, id).enqueue((object:Callback<registrationresult>{
//            override fun onFailure(call: Call<registrationresult>, t: Throwable) {
//                Log.e("response", "error")
//                t.printStackTrace()
//            }
//            override fun onResponse(call: Call<registrationresult>, response: Response<registrationresult>) {
//                val result = response.body()
//                Log.d("response add user ", response?.body().toString())
////                Log.d("response", result!!.result)
//            }
//        }))
//        server.test().enqueue((object:Callback<testresult>{
//            override fun onFailure(call: Call<testresult>, t: Throwable) {
//                Log.e("response1", "error")
//                t.printStackTrace()
//            }
//            override fun onResponse(call: Call<testresult>, response: Response<testresult>) {
//                Log.d("response2: ", response?.body().toString())
//            }
//        }))

        val mAdapter = CustomAdapter(v.context, Feedlist)
        recycler_view.adapter = mAdapter

        val layout = LinearLayoutManager(requireContext())
        recycler_view.layoutManager = layout
        recycler_view.setHasFixedSize(true)




        //로그아웃, 로그인창으로 이동
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

        return v
    }

}