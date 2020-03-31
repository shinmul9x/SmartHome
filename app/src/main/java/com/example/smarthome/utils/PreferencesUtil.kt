package com.example.smarthome.utils

import android.content.Context


class PreferencesUtil {
    private val preferenceFile = "preference_file"
    private val stringDefault = ""
    private val tokenKey = "token_value"

    fun putToken(context: Context, value: String) {
        putString(context, tokenKey, value)
    }

    fun getToken(context: Context): String {
        return getString(context, tokenKey)
    }

    fun removeToken(context: Context) {
        remove(context, tokenKey)
    }

    private fun putString(context: Context, key: String, value: String) {
        val sharedPref =
            context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE) ?: return
        sharedPref.edit().putString(key, value).apply()
        DebugLog().d("<< put String >> key:$key value:$value")
    }

    private fun getString(context: Context, key: String): String {
        val sharedPref =
            context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE)
                ?: return stringDefault
        return sharedPref.getString(key, stringDefault).toString()
    }

    private fun remove(context: Context, key: String) {
        val sharedPref =
            context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE) ?: return
        sharedPref.edit().remove(key).apply()
        DebugLog().d("<< remove String >> key:$key")
    }
}
