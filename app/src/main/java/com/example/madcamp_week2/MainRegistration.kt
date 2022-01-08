package com.example.madcamp_week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.madcamp_week2.ui.home.RetrofitUser
import com.example.madcamp_week2.ui.home.registrationresult
import com.example.madcamp_week2.ui.home.testresult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registration)

        lateinit var email:String

        val input_nickname = findViewById<EditText>(R.id.join_name)
        val input_email = findViewById<EditText>(R.id.join_email)

        val emailcheckbutton = findViewById<Button>(R.id.check_button)
        val regbutton = findViewById<Button>(R.id.join_button)
        val delbutton = findViewById<Button>(R.id.delete)


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



    }
}