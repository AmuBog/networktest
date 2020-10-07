package no.amund.networktest.network

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import no.amund.networktest.getError
import org.json.JSONObject


/**
 * Created by Amund Bogetvedt on 27/08/2020.
 */

class UserRepository {
    private val userInterface: UserInterface = APIClient.client.create(UserInterface::class.java)

    suspend fun getUsers() : List<String>? {
        return withContext(Dispatchers.IO) {
            val call = userInterface.getUsers()
            val response = call.execute()
            response.body()
        }
    }

    suspend fun getUsersWithPets() : List<UserAndPet>? {
        return withContext(Dispatchers.IO) {
            val call = userInterface.getUsersWithPets()
            val response = call.execute()
            if(!response.isSuccessful) {
                response.errorBody()?.let {
                    val error = it.getError()
                    Log.e(javaClass.canonicalName, "Status: ${error.status} Error: ${error.message}")
                }
            }
            response.body()
        }
    }

    suspend fun addUser(user: String, f: (userList: List<String>?) -> Unit, err: (error: ErrorMessage) -> Unit){
        return withContext(Dispatchers.Main) {
            val call = userInterface.addUser(user)
            val response = call.execute()
            if(!response.isSuccessful) {
                response.errorBody()?.let {
                    val error = it.getError()
                    Log.e(javaClass.canonicalName, "Status: ${error.status} Error: ${error.message}")
                    err(error)
                }
            } else {
                f(response.body())
            }
        }
    }

}