package no.amund.networktest.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Amund Bogetvedt on 27/08/2020.
 */

class PetRepository {
    private val userInterface: PetInterface = APIClient.client.create(PetInterface::class.java)

    suspend fun getPets() : List<String>? {
        return withContext(Dispatchers.IO) {
            val call = userInterface.getPets()
            val response = call.execute()
            response.body()
        }
    }
}