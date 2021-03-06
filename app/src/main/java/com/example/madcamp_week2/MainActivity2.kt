package com.example.madcamp_week2

//import com.example.madcamp_week2.ui.chatroom.ChatRoomFragment

//import com.example.madcamp_week2.ui.chatroom.FragmentThree
//import com.example.madcamp_week2.ui.chatroom.FragmentThree
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.madcamp_week2.ui.chatroom.FragmentThree
import com.example.madcamp_week2.ui.dashboard.FragmentTwo
import com.example.madcamp_week2.ui.home.FragmentOne
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    private val fragmentOne by lazy { FragmentOne() }
    private val fragmentTwo by lazy { FragmentTwo() }
    private val fragmentThree by lazy { FragmentThree() }
//    private val fragmentFour by lazy { FragmentFour() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var actionBar = supportActionBar
        actionBar?.hide()
        var bnv_main = findViewById<BottomNavigationView>(R.id.nav_view)

        val intent = intent
        println("난 못 받음")

        this.intent.putExtra("id", intent.getLongExtra("userid", 0).toString())
        this.intent.putExtra("userName", intent.getStringExtra("userName")!!)
        this.intent.putExtra("userThumnail", intent.getStringExtra("userThumbnail")!!)
        this.intent.putExtra("userEmail", intent.getStringExtra("userEmail")!!)

        Log.e("id", intent.getLongExtra("userid", 0).toString())
        Log.e("userName", intent.getStringExtra("userName")!!)
        Log.e("userThumnail", intent.getStringExtra("userThumbnail")!!)
        Log.e("userEmail", intent.getStringExtra("userEmail")!!)

        bnv_main.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> {
                        changeFragment(fragmentOne)
                    }
                    R.id.navigation_dashboard -> {
                        changeFragment(fragmentTwo)
                    }
                    R.id.navigation_chatroom -> {
                        changeFragment(fragmentThree)
                    }
                }
                true
            }
            selectedItemId = R.id.navigation_home
        }
    }

    override fun onRestart() {
        super.onRestart()
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }

    fun changeFragmentOne(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragmentOne)
            .commit()
    }

//
    fun changeFragmentThree(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragmentThree)
            .commit()
    }

}