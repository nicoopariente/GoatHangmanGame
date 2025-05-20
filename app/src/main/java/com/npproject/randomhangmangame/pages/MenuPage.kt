package com.npproject.randomhangmangame.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Declaring the Composable Menu. Receives a lambda that will allow to navigate to the Game page
@Composable
fun Menu(navigateToGame: (String) -> Unit) {
    //Mutable State variables
    var soloOptions by remember {mutableStateOf(false)}
    var friendsOptions by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }
    //Creating instance of Category Pool. This class contains lists for each category and methods to choose one element randomly
    val categoryPool = CategoryPool()

    //Composable that contains the body of the Menu
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF5dc20a))){

        //Composable that will align vertically the inner composable
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top, modifier = Modifier.padding(20.dp)){

            Box( modifier = Modifier.height(400.dp).fillMaxWidth()){

                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                    //Three Text Composable that will provide text information about the game
                    Text("Welcome to GOAT Hangman Game!!!", fontSize = 30.sp, color = Color.Black, lineHeight = 40.sp, textAlign = TextAlign.Center )
                    Text("Instructions:", color = Color.Black)
                    Text("Please select Solo or With Friends mode. You will be racing against a real car during the game. Hurry up, or you will run out of chances!", color = Color.Black)
                }

            }
            //Button that will be displayed only if neither Solo or With Friends buttons have been clicked
            if(!soloOptions && !friendsOptions){
                //If clicked, button will change soloOption state variable to true, displaying the category options
                Button(onClick = {soloOptions = !soloOptions}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Solo Player", color = Color.White)
                }
            }

            // If soloOptions was clicked, a button for each category will be displayed to the user
            if(soloOptions){

                Text(text = "Please choose a Category", color = Color.Black)
                Spacer(modifier = Modifier.height(20.dp))
                //Button that will generate a random Movie word, and call the lambda function to navigate to the Game page
                Button(onClick = {navigateToGame(categoryPool.randomMovie())}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Movies", color = Color.White)
                }
                //Button that will generate a random Country word, and call the lambda function to navigate to the Game page
                Button(onClick = {navigateToGame(categoryPool.randomCountry())}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Countries", color = Color.White)
                }
                //Button that will generate a random Soccer Team word, and call the lambda function to navigate to the Game page
                Button(onClick = {navigateToGame(categoryPool.randomSoccerTeam())}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Soccer Teams", color = Color.White)
                }
                //Button that will generate a random Football Team word, and call the lambda function to navigate to the Game page
                Button(onClick = {navigateToGame(categoryPool.randomFootballTeam())}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Football Teams", color = Color.White)
                }

            }

            //Button that will be displayed only if neither Solo or With Friends buttons have been clicked
            if(!soloOptions && !friendsOptions){
                //If clicked, button will change friendsOptions state variable to true, displaying the text field for user input
                Button(onClick = {friendsOptions = !friendsOptions}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "With Friends", color = Color.White)
                }
            }

            // If friendsOptions was clicked, a text field be displayed to the user to receive input word
            if(friendsOptions){
                Text(text = "Please Write the word below")
                Spacer(modifier = Modifier.height(20.dp))
                TextField(value = textFieldValue, onValueChange = {textFieldValue = it.uppercase()})
                //Button that will take the word received from the user, and call the lambda function to navigate to the Game page
                Button(onClick = {navigateToGame(textFieldValue)}, colors = ButtonDefaults.buttonColors(Color.Black)){
                    Text(text = "Start", color = Color.White)
                }
            }

        }


    }
}


