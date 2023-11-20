package com.plcoding.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.todolist.ui.theme.TodoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowText()
                }
            }
        }
    }
}

fun ajouterTache(titre: String, description: String) {
    val NewTask = Tache(titre, description)
    AddTask.add(NewTask)
}

fun supprimerTache(tache: Tache) {
    AddTask.remove(tache)
}

private val AddTask = mutableListOf<Tache>()

data class Tache(val nom: String, val description: String)

fun getListeTaches(): List<Tache> {
    return AddTask.toList()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowText() {
    var NewText by remember {
        mutableStateOf("")
    }

    var NewDesc by remember {
        mutableStateOf("")
    }

    var getListeTaches by remember {mutableStateOf(getListeTaches())}
            Column {
                for (x in getListeTaches) {
                    Text(text = x.nom)
                    Text(text = x.description)
                    Button(onClick = {
                        supprimerTache(x)
                        getListeTaches = getListeTaches()
                    }) {

                    }
                }
                TextField(value = NewText, onValueChange = {T -> NewText = T})
                TextField(value = NewDesc, onValueChange = {T -> NewDesc = T})
                
                Button(onClick = {
                    ajouterTache(titre = NewText, description = NewDesc)
                    getListeTaches = getListeTaches()
                }) {
                }
            }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListTheme {
        ajouterTache("Android", "d")
    }
}