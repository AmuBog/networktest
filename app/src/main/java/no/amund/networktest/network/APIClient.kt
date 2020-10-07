package no.amund.networktest.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Amund Bogetvedt on 27/08/2020.
 */

object APIClient {
    const val HOME_URL = "http://192.168.1.124"
    const val WORK_URL = "http://10.1.100.5"
    val client: Retrofit

    init {
        val httpClient = OkHttpClient.Builder()
        client = Retrofit.Builder()
            .baseUrl("$HOME_URL:3000")
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}