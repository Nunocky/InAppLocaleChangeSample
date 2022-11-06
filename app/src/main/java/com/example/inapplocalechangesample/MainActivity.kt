package com.example.inapplocalechangesample

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_en).setOnClickListener {
            preference(this)?.edit()?.putString(LANGUAGE, LANG_EN)?.apply()
            finish()
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_ja).setOnClickListener {
            preference(this)?.edit()?.putString(LANGUAGE, LANG_JA)?.apply()
            finish()
            startActivity(intent)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        // デフォルトはシステムの言語設定に合わせる
        var locale = newBase?.resources?.configuration?.locales?.get(0)
        val lang = preference(newBase)?.getString(LANGUAGE, locale?.language)

        locale = when (lang) {
            LANG_JA -> Locale.JAPANESE
            else -> Locale.ENGLISH
        }

        super.attachBaseContext(newBase?.createLocalizedContext(locale))
    }

    private fun preference(context: Context?): SharedPreferences? =
        context?.getSharedPreferences(PREF_SETTING, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_SETTING = "setting"
        private const val LANGUAGE = "language"
        private const val LANG_EN = "en"
        private const val LANG_JA = "ja"
    }

}

private fun Context.createLocalizedContext(locale: Locale): Context {
    val res = resources
    val config = Configuration(res.configuration)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.setLocales(LocaleList(locale))
    } else {
        config.setLocale(locale)
    }
    return createConfigurationContext(config)
}