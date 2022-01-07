package com.example.madcamp_week2.ui.dashboard

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madcamp_week2.R
import android.database.DataSetObserver

import android.database.DataSetObservable
import android.media.Image
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.io.File

import java.nio.file.Files.size
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var v:View

class FragmentTwo : Fragment() {
    val feedlist = ArrayList<dataFeed>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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



        for(i:Int in 0..30){
            feedlist.add(dataFeed(R.drawable.istockphoto, tempdate, content))
        }

        return v
    }
    


}

class FoodAdapter(private var context: Context?, private var feedlist: ArrayList<dataFeed>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val food : dataFeed = feedlist[position]
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val foodView : View = inflater.inflate(R.layout.frag2_gridview_image, null)
        val photo = foodView.findViewById<ImageView>(R.id.imageView1)
        photo.setImageResource(food.photo)

        foodView.setOnLongClickListener {
            Log.e("test", "here")
            val dialogView = inflater.inflate(R.layout.frag2_view_item_layout, null)

            val image = dialogView.findViewById<ImageView>(R.id.userImg)
            val date = dialogView.findViewById<TextView>(R.id.date)
            val content = dialogView.findViewById<TextView>(R.id.userName)
            image.setImageResource(food.photo)
            date.text = food.date
            content.text = food.contents

            val alertDialog = AlertDialog.Builder(v.context)
                .setView(dialogView)
                .create()

            alertDialog.show()


            return@setOnLongClickListener true
        }

//        foodView.imageView.setOnClickListener {
//            val intent = Intent(context, FoodDetailActivity::class.java)
//            intent.putExtra("name", food.name!!)
//            intent.putExtra("des", food.des!!)
//            intent.putExtra("image", food.image!!)
//            context!!.startActivity(intent)
//        }
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

}