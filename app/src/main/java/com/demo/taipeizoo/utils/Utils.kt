package com.demo.taipeizoo.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.demo.taipeizoo.R

fun launchBrowser(activity: Activity, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        activity.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(activity, R.string.open_link_error, Toast.LENGTH_SHORT).show()
    }
}