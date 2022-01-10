package com.example.madcamp_week2.ui.dashboard

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.madcamp_week2.R
import com.example.madcamp_week2.ui.home.Feed
import com.example.madcamp_week2.ui.home.RetrofitUser
import com.example.madcamp_week2.ui.home.getEveryFeedResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var v:View

class FragmentTwo : Fragment() {
    var feedlist = ArrayList<Feed>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        feedlist = ArrayList<Feed>()
        v = inflater.inflate(R.layout.fragment_two, container, false)

        val mAdapter = FoodAdapter(v.context, feedlist)

        val gridview = v.findViewById<GridView>(R.id.gridView)
        gridview.adapter = mAdapter



        val today = LocalDate.now()
        val Strnow = today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
        val year = today.getYear().toString()
        val month = today.getMonth().toString()
        val day = today.getDayOfMonth().toString()

        val tempdate = Strnow
        Log.e("test", Strnow)

        val content:String
        content = "hello world"



//        for(i:Int in 0..30){
//            feedlist.add(dataFeed(R.drawable.istockphoto, tempdate, content))
//        }

        val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
            GsonConverterFactory.create()).build()
        var server = retrofit.create(RetrofitUser::class.java)

        //피드 모두 불러오기
        server.getEveryFeed().enqueue((object: Callback<getEveryFeedResult> {
            override fun onFailure(call: Call<getEveryFeedResult>, t: Throwable) {
                Log.e("get user", "error")

            }
            override fun onResponse(call: Call<getEveryFeedResult>, response: Response<getEveryFeedResult>) {
                if(response.body() == null){
                    Log.e("get user", response.body().toString())
                }else{
                    Log.e("get user", response.body().toString())
                    Log.e("get user", response.body()!!.feeds.size.toString())

                    val feedListget = response.body()!!

                    for(i: Int in 0..feedListget.feeds.size-1){
                        (gridview.adapter as FoodAdapter).addItem(feedListget.feeds[i])
                    }

                }
            }
        }))

        return v
    }
    


}

class FoodAdapter(private var context: Context?, private var feedlist: ArrayList<Feed>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val food : Feed = feedlist[position]
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val foodView : View = inflater.inflate(R.layout.frag2_gridview_image, null)
        val photo = foodView.findViewById<ImageView>(R.id.imageView1)

        Glide.with(photo).load(food.image).into(photo)

        foodView.setOnLongClickListener {
            Log.e("test", "here")
            val dialogView = inflater.inflate(R.layout.feed_layout, null)

            val image = dialogView.findViewById<ImageView>(R.id.feed_image)
            val date = dialogView.findViewById<TextView>(R.id.feed_date)
            val content = dialogView.findViewById<TextView>(R.id.feed_content)
            val userName = dialogView.findViewById<TextView>(R.id.feed_userName)

            val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                GsonConverterFactory.create()).build()
            var server = retrofit.create(RetrofitUser::class.java)

            //피드 모두 불러오기
            server.getFeed(food._id).enqueue((object: Callback<Feed> {
                override fun onFailure(call: Call<Feed>, t: Throwable) {
                    Log.e("get user", "error")

                }
                override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                    if(response.body() == null){
                        Log.e("get user", response.body().toString())
                    }else{
                        val nickName = response.body()!!.nickName
                        Glide.with(image).load(food.image).into(image)

                        date.text = food.time
                        content.text = food.content
                        userName.text = nickName

                        val alertDialog = AlertDialog.Builder(v.context)
                            .setView(dialogView)
                            .create()

                        alertDialog.show()


                    }
                }
            }))

//            Glide.with(image).load(food.image).into(image)
//
//            date.text = food.time
//            content.text = food.content
//
//            val alertDialog = AlertDialog.Builder(v.context)
//                .setView(dialogView)
//                .create()
//
//            alertDialog.show()


            return@setOnLongClickListener true
        }


        return foodView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return feedlist.size
    }

    fun addItem(dataVo: Feed) {
        feedlist.add(dataVo)
        //갱신처리 반드시 해야함
        notifyDataSetChanged()
    }

}