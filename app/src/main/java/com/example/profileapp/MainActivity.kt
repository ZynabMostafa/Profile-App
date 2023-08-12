package com.example.profileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.profileapp.database.BuliderDatabase
import com.example.profileapp.screens.homeScreen.HomeScreen
import com.example.profileapp.screens.homeScreen.viewModel.HomeViewModel
import com.example.profileapp.screens.navigation.NavigationBetween
import com.example.profileapp.ui.theme.ProfileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ProfileAppTheme {


                val homeViewModel: HomeViewModel by viewModels()
                val buliderDatabase = BuliderDatabase(this@MainActivity)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationBetween(

                        buliderDatabase = buliderDatabase,
                        homeViewModel = homeViewModel ,

                    )
                     }
            }
        }
    }
}

