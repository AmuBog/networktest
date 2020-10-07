package no.amund.networktest.network

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Amund Bogetvedt on 27/08/2020.
 */

interface PetInterface {
    @GET("/pets")
    fun getPets() : Call<List<String>>
}