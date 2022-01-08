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
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var a = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var userList = arrayListOf<DataVo>(
        DataVo("IU","전주시", "010-1111-1111","user_img_01"),
        DataVo("홍길동", "서울시","010-1234-5678", "user_img_02"),
        DataVo("김영수", "광주시", "010-0000-0000", "user_img_03")
    )

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

        val id = requireActivity().intent.getStringExtra("id");
        val userName = requireActivity().intent.getStringExtra("userName");
        val userThumnail = requireActivity().intent.getStringExtra("userThumnail");

        Log.e("frag1", id!!);
        Log.e("frag1", userName!!)
        Log.e("frag1", userThumnail!!)

        Glide.with(image).load(userThumnail).circleCrop().into(image);

        val data:User = User(id, userName, userThumnail)

        val retrofit = Retrofit.Builder().baseUrl("http://172.10.18.77:80").addConverterFactory(GsonConverterFactory.create()).build()
        var server = retrofit.create(RetrofitUser::class.java)
//        server.postRequest(userName, userThumnail, id).enqueue((object:Callback<newuserresult>{
//            override fun onFailure(call: Call<newuserresult>, t: Throwable) {
//                Log.e("response", "error")
//            }
//            override fun onResponse(call: Call<newuserresult>, response: Response<newuserresult>) {
//                Log.d("response : ", response?.body().toString())
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

        val mAdapter = CustomAdapter(v.context, userList)
        recycler_view.adapter = mAdapter

        val layout = LinearLayoutManager(requireContext())
        recycler_view.layoutManager = layout
        recycler_view.setHasFixedSize(true)

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


        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentOne.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOne().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}