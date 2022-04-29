package com.kkt1019.ex098preferencefragment

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SettingFragment를 동적 추가
        //리플레이스 프래그먼트가 있다면 뜯어내고 다시 붙이고 없다면 그냥 add
        supportFragmentManager.beginTransaction().replace(R.id.container_fragment, SettingFragment()).commit()

        //저장된 설정값들 읽어오기
        loadPreference()

    }

    private fun loadPreference(){
        //shardPreference 에 저장된 설정값 가져오기
        val pref:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        var isMessage:Boolean = pref.getBoolean("message", false)
        var isVibratie:Boolean = pref.getBoolean("vibration", false)

        Toast.makeText(this, "sound message: $isMessage \n vibration: $isVibratie" , Toast.LENGTH_SHORT).show()
    }
}