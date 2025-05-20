package com.npproject.randomhangmangame.pages

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Menu(navigateToGame: (String) -> Unit) {
    var soloOptions by remember {mutableStateOf(false)}
    var friendsOptions by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }
    var categoryPool = CategoryPool()

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF5dc20a))){

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top, modifier = Modifier.padding(20.dp)){
            //Text(text = "Go to Game",modifier = Modifier.clickable { navigateToGame("Text from Menu") })
            Box( modifier = Modifier.height(400.dp).fillMaxWidth()){
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                    Text("Welcome to GOAT Hangman Game!!!", fontSize = 30.sp, color = Color.Black, lineHeight = 40.sp, textAlign = TextAlign.Center )
                    Text("Instructions:", color = Color.Black)
                    Text("Please select Solo or With Friends mode. You will be racing against a real car during the game. Hurry up, or you will run out of chances!", color = Color.Black)
                }

            }
            if(!soloOptions && !friendsOptions){
                Button(onClick = {soloOptions = !soloOptions}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Solo Player", color = Color.White)
                }
            }


            if(soloOptions){
                Text(text = "Please choose a Category", color = Color.Black)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {navigateToGame(categoryPool.randomMovie())}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Movies", color = Color.White)
                }
                Button(onClick = {navigateToGame(categoryPool.randomCountry())}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Countries", color = Color.White)
                }
                Button(onClick = {navigateToGame(categoryPool.randomSoccerTeam())}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Soccer Teams", color = Color.White)
                }
                Button(onClick = {navigateToGame(categoryPool.randomFootballTeam())}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "Football Teams", color = Color.White)
                }

            }

            if(!soloOptions && !friendsOptions){
                Button(onClick = {friendsOptions = !friendsOptions}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(text = "With Friends", color = Color.White)
                }
            }


            if(friendsOptions){
                Text(text = "Please Write the word below")
                Spacer(modifier = Modifier.height(20.dp))
                TextField(value = textFieldValue, onValueChange = {textFieldValue = it.uppercase()})
                Button(onClick = {navigateToGame(textFieldValue)}, colors = ButtonDefaults.buttonColors(Color.Black)){
                    Text(text = "Start", color = Color.White)
                }
            }

        }


    }
}


