package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.yariksoffice.lingver.Lingver

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, "en") // default language
    }
}
