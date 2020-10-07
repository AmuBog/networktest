package no.amund.networktest.network

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Amund Bogetvedt on 27/08/2020.
 */

interface UserInterface {
    @GET("/users")
    fun getUsers() : Call<List<String>>

    @GET("/usersandpets")
    fun getUsersWithPets() : Call<List<UserAndPet>>

    @FormUrlEncoded
    @POST("/user")
    fun addUser(@Field("name") name: String) : Call<List<String>>
}