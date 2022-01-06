package com.example.madcamp_week2.ui.home

import android.app.AlertDialog
import android.content.res.AssetManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week2.R
import com.google.android.material.button.MaterialButton
import org.json.JSONException
import org.json.JSONObject
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
        val v =  inflater.inflate(R.layout.fragment_one, container, false)
        val recycler_view = v.findViewById<RecyclerView>(R.id.recycler_view)


        val mAdapter = CustomAdapter(v.context, userList)
        recycler_view.adapter = mAdapter

        val layout = LinearLayoutManager(requireContext())
        recycler_view.layoutManager = layout
        recycler_view.setHasFixedSize(true)


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