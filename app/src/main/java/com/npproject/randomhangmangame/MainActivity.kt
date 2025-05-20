package com.npproject.randomhangmangame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.npproject.randomhangmangame.pages.Game
import com.npproject.randomhangmangame.pages.Menu
import com.npproject.randomhangmangame.ui.theme.GoatHangmanGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoatHangmanGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = "menu", modifier = Modifier.padding(innerPadding)){
                        composable("menu"){ Menu(navigateToGame = {word -> navigationController.navigate(route = "game/$word")}) }
                        composable("game/{word}", arguments = listOf(navArgument("word"){type = NavType.StringType})){
                            backStackEntry -> Game(backStackEntry.arguments?.getString("word")!!, navigateToGame = {navigationController.navigate(route = "menu")})
                             }
                    }
                }
            }
        }
    }
}



