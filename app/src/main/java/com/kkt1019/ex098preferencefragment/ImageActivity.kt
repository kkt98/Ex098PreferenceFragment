package com.kkt1019.ex098preferencefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val iv = findViewById<PhotoView>(R.id.phv)
        Glide.with(this).load("https://img.animalplanet.co.kr/news/2019/10/07/700/99pk9saw36t7q8t3o0gt.jpg").into(iv)
    }
}