package com.example.profileapp.screens.profileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.profileapp.database.BuliderDatabase
import com.example.profileapp.database.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    builderDatabase: BuliderDatabase,
    id: Int
) {

    val user = builderDatabase.getDao().getCustomUser(id)

    var name by remember {
        mutableStateOf(user.name)
    }

    var age by remember {
        mutableStateOf(user.age)
    }

    var job by remember {
        mutableStateOf(user.jobDescription)
    }

    Column  (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        OutlinedTextField(value = name, onValueChange = { newName ->
            name = newName
        },
            label = {
                Text(text = "Enter your name")
            })
        OutlinedTextField(
            value = age.toString(),
            onValueChange = { newAge ->
                kotlin.runCatching { age = newAge.toInt() }
                    .getOrDefault(0)
            },
            label = {
                Text(text = "Enter your age")
            })
        OutlinedTextField(
            value = job,
            onValueChange = { newJob ->
                kotlin.runCatching { job = newJob }
                    .getOrDefault(0)
            },
            label = {
                Text(text = "Enter your job description")
            })

        Button(onClick = {
            val newUser = User( id = id ,name = name , age = age , jobDescription = job)
            builderDatabase.getDao().updateUser(newUser)
        }) {
            Text(text = "update")
        }

        OutlinedButton(onClick = {
            navController.navigate("home")

        }) {
            Text(text = "Back")
        }
    }

}