package com.example.smarthome.utils

import android.util.Log
import java.util.regex.Pattern

class DebugLog {
    private val prefixTag = "SMART_HOME"

    fun d(msg: String) {
        Log.d(getTag(), msg)
    }

    private fun getTag(): String {
        val element = Throwable().stackTrace[2] ?: return prefixTag
        val pkgPattern = Pattern.compile(".*\\.")
        val className = pkgPattern.matcher(element.className).replaceAll("")
        val line = element.lineNumber
        return "$prefixTag|$className($line)"
    }
}