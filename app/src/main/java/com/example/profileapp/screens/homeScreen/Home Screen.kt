package com.example.profileapp.screens.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.profileapp.database.BuliderDatabase
import com.example.profileapp.database.User
import com.example.profileapp.screens.homeScreen.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    builderDatabase: BuliderDatabase,
    homeViewModel: HomeViewModel
) {


    var list by remember {
        mutableStateOf(builderDatabase.getDao().gelAllUser())
    }
    Scaffold(
        floatingActionButtonPosition = FabPosition.End, floatingActionButton = {
            IconButton(onClick = {
                homeViewModel.show.value = true

            }, modifier = Modifier.background(color = Color.Cyan)) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn()
            {
                items(list) { user ->
                    CardToShowList(user = user,

                        onClick = {
                            navController.navigate("profile/${user.id}")
                        },
                        onIconClicked = { builderDatabase.getDao().deleteUser(user)
                        list= builderDatabase.getDao().gelAllUser()})


                }
            }

            if (homeViewModel.show.value) {
                InsertInformationDialog(
                    showDialog = homeViewModel.show.value,
                    homeViewModel = homeViewModel,
                    onClickAdd = {
                        val user = User(
                            name = homeViewModel.name.value,
                            age = homeViewModel.age.value,
                            jobDescription = homeViewModel.jobDescription.value
                        )
                        builderDatabase.getDao().inserUser(user)
                        list = builderDatabase.getDao().gelAllUser()
                    },
                    onDismissClicked = { homeViewModel.show.value = false })


            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertInformationDialog(
    showDialog: Boolean,
    homeViewModel: HomeViewModel,
    onClickAdd: () -> Unit,
    onDismissClicked: () -> Unit
) {

    AnimatedVisibility(visible = showDialog) {
        AlertDialog(
            title = {
                Text(text = "Information Profile")
            },
            text = {
                Column {
                    OutlinedTextField(value = homeViewModel.name.value, onValueChange = { newName ->
                        homeViewModel.name.value = newName
                    },
                        label = {
                            Text(text = "Enter your name")
                        })
                    OutlinedTextField(
                        value = homeViewModel.age.value.toString(),
                        onValueChange = { newAge ->
                            kotlin.runCatching { homeViewModel.age.value = newAge.toInt() }
                                .getOrDefault(0)
                        },
                        label = {
                            Text(text = "Enter your age")
                        })
                    OutlinedTextField(
                        value = homeViewModel.jobDescription.value,
                        onValueChange = { newJob ->
                            kotlin.runCatching { homeViewModel.jobDescription.value = newJob }
                                .getOrDefault(0)
                        },
                        label = {
                            Text(text = "Enter your job description")
                        })

                }
            },
            onDismissRequest = {
                onDismissClicked()
            },
            confirmButton = {
                Button(onClick = {
                    onClickAdd()
                    onDismissClicked()
                }) {
                    Text(text = "Add")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                       homeViewModel.name.value = ""
                        homeViewModel.age.value = 0
                        homeViewModel.jobDescription.value = ""
                    onDismissClicked()
                }) {
                    Text(text = "No")
                }


            })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardToShowList(
    user: User,
    onClick: () -> Unit,
    onIconClicked: () -> Unit
) {

    Card(
        onClick = { onClick() },
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.Blue, shape = CircleShape),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()) {
            IconButton(onClick = { onIconClicked() }) {
                Icon(
                    imageVector = Icons.Filled.Delete, contentDescription = "delete",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                )
            }
            Text(
                text = user.name, fontWeight = FontWeight.W700, modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.TopCenter)

            )

            Text(
                text = user.age.toString(), modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.Center)

            )
            Text(
                text = user.jobDescription, modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}