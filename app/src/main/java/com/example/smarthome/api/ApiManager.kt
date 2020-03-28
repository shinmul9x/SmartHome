package com.example.smarthome.api


class ApiManager {
    private fun getURL() = "http://sv-procon.uet.vnu.edu.vn:3000"
    fun getApiService(): IApiService? {
        return ApiClient().getClient(getURL())?.create(IApiService::class.java)
    }
}