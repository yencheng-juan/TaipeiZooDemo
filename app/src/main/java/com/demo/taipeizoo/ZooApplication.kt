package com.demo.taipeizoo

import android.app.Application
import android.content.Context


class ZooApplication : Application() {

    companion object {
        private lateinit var zooApplication: ZooApplication
        fun getApplicationContext(): Context {
            return zooApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        zooApplication = this
    }
}