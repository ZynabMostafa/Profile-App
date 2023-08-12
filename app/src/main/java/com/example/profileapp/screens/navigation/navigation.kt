package com.example.profileapp.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.profileapp.database.BuliderDatabase
import com.example.profileapp.screens.homeScreen.HomeScreen
import com.example.profileapp.screens.homeScreen.viewModel.HomeViewModel
import com.example.profileapp.screens.profileScreen.ProfileScreen

@Composable
fun NavigationBetween (
    buliderDatabase: BuliderDatabase,
    homeViewModel: HomeViewModel,

){
    val navController  = rememberNavController()

    NavHost(navController = navController, startDestination = "home" ){
        composable("home") {
            HomeScreen(builderDatabase = buliderDatabase, homeViewModel = homeViewModel , navController = navController)
        }
        composable("profile/{data}" , arguments = listOf(
          navArgument("data"){type = NavType.IntType}
        )){navBackStackEntry->
           val id = navBackStackEntry.arguments?.getInt("data") ?: 0
             ProfileScreen(builderDatabase = buliderDatabase , navController = navController ,id = id)
        }
    }
}
