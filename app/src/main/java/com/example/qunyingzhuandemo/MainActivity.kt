package com.example.qunyingzhuandemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var max_layout = findViewById<ViewGroup>(R.id.max_layout)

        var view = layoutInflater.inflate(R.layout.art_tv_color_tag, max_layout,false)
        max_layout.addView(view)
//        for (i in 0..5) {
//            if (i%2==0){
//                var view = layoutInflater.inflate(R.layout.art_tv_color_tag, max_layout,false)
//                max_layout.addView(view)
//            }else{
//                var view = layoutInflater.inflate(R.layout.art_tv_normal_tag, max_layout,false)
//                max_layout.addView(view)
//            }
//        }
    }
}