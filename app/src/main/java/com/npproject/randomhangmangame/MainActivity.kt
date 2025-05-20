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

//Main class that controls transitions between app pages using Jetpack Compose
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //Composable that will add basic styling following android specifications
            GoatHangmanGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Creating instance of rememberNavController which will work as a state keeping track of the current displayed page
                    val navigationController = rememberNavController()
                    //Creating instance of NavHost, that will control the pages structure, transitions, and state
                    NavHost(navController = navigationController, startDestination = "menu", modifier = Modifier.padding(innerPadding)){
                        //Creating first route to our "menu" page. Menu is a composable that will work as our home page.
                        //Menu will receive a lambda function as an argument, which will contain the command to change to the game page
                        //Game page requires an argument word
                        composable("menu"){ Menu(navigateToGame = {word -> navigationController.navigate(route = "game/$word")}) }
                        //Creating second route to our "game" page. Game is a composable that will work as our game page.
                        //As Game receives an argument "word", we need to specify the type, in this case it will be String.
                        //Using a lambda function, we pass the "word" argument to Game.
                        //We also pass a lambda function as argument, which will contain the command to change back to the menu page
                        composable("game/{word}", arguments = listOf(navArgument("word"){type = NavType.StringType})){
                            backStackEntry -> Game(backStackEntry.arguments?.getString("word")!!, navigateToGame = {navigationController.navigate(route = "menu")})
                             }
                    }
                }
            }
        }
    }
}



