package no.amund.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.amund.networktest.network.PetRepository
import no.amund.networktest.network.UserRepository
import kotlin.coroutines.CoroutineContext
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val userApi = UserRepository()
    private val petApi = PetRepository()

    val users = mutableListOf<String>()
    val pets = mutableListOf<String>()

    var update: Boolean by Delegates.observable(false) { _, _, newValue ->
        if (newValue) {
            updateList()
        }
    }

    private lateinit var nameRecycler: RecyclerView
    private lateinit var newName: EditText
    private lateinit var submit: Button

    private val nameAdapter = NameListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameRecycler = findViewById(R.id.rvNames)
        newName = findViewById(R.id.newName)
        submit = findViewById(R.id.btnAdd)

        nameRecycler.adapter = nameAdapter

        launch {
            userApi.getUsers()?.let {
                fillList(it)
            }
            petApi.getPets()?.let {
                pets.addAll(it)
            }
        }

        submit.setOnClickListener {
            launch {
                userApi.addUser(newName.text.toString(), {list ->
                    fillList(list)
                }) { error ->
                    Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun fillList(list: List<String>?) {
        if(list != null) {
            users.clear()
            users.addAll(list)
            update = true
        }
    }

    private fun updateList() {
        nameAdapter.replaceList(users)
        update = false
    }
}