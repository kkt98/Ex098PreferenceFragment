package com.kkt1019.ex098preferencefragment

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

//PreferenceFragmentCompat 사용 - 별도 라이브러리 추가
class SettingFragment : PreferenceFragmentCompat()  {
    //일반 Fragment와 다르게 설정화면을 직접 layout으로 xml로 설계하는 방식이 아니라
    //설정항목을 별도 xml파일로 만들어 지정하면 자동으로 화면이 만들어짐
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        //res >> xml 폴더안에 setting.xml 문서를 만들고 설정항목을 정한 작성한 후
        //이 프레그먼트에서 setting.xml을 화면으로 지정하기
        setPreferencesFromResource(R.xml.setting, rootKey)

    }

    val pref:SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(requireContext()) }

    override fun onResume() {
        super.onResume()

        pref.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onPause() {
        super.onPause()

        pref.unregisterOnSharedPreferenceChangeListener(listener)
    }

    val listener = object : SharedPreferences.OnSharedPreferenceChangeListener{
        override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
            //두번째 파라미터 p1 : 변경된 항목의 key 속성값

            val buffer = StringBuffer()
            when(p1) {

                "message","vibration" -> buffer.append(p1 + " : " + pref.getBoolean(p1, false))
                "nickname" -> {
                    val s = pref.getString(p1,"")
                    buffer.append("$p1 : $s")
                    //Summery에 값 적용하기 : 해당설정항목 객체 찾아오기
                    val etPref = findPreference<EditTextPreference>(p1)
                    etPref?.summary = s
                }
                "sound" -> {
                    val s = pref.getString(p1,"")
                    buffer.append("$p1 : $s")
                    findPreference<ListPreference>(p1)?.summary=s
                }
                "favor" -> {
                    val datas:MutableSet<String>? = pref.getStringSet(p1, mutableSetOf<String>())
                    //for (s in datas!!) buffer.append(s)
                    datas?.forEach { buffer.append(it) }
                }

            }

            AlertDialog.Builder(context)
            AlertDialog.Builder(requireContext()).setMessage(buffer.toString()).create().show()
        }

    }

}