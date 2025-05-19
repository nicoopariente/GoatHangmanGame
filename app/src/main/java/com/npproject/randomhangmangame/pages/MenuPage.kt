package com.npproject.randomhangmangame.pages

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Menu(navigateToGame: (String) -> Unit) {
    var soloOptions by remember {mutableStateOf(false)}
    var friendsOptions by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }
    var categoryPool = CategoryPool()

    Box(modifier = Modifier.fillMaxSize().background(Color.Gray), contentAlignment = Alignment.Center){

        Column(){
            //Text(text = "Go to Game",modifier = Modifier.clickable { navigateToGame("Text from Menu") })
            if(!soloOptions && !friendsOptions){
                Button(onClick = {soloOptions = !soloOptions}, modifier = Modifier) {
                    Text(text = "Solo Player")
                }
            }


            if(soloOptions){
                Text(text = "Please choose a Category")
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {navigateToGame(categoryPool.randomMovie())}) {
                    Text(text = "Movies")
                }
                Button(onClick = {navigateToGame(categoryPool.randomCountry())}) {
                    Text(text = "Countries")
                }
                Button(onClick = {navigateToGame(categoryPool.randomSoccerTeam())}) {
                    Text(text = "Soccer Teams")
                }
                Button(onClick = {navigateToGame(categoryPool.randomFootballTeam())}) {
                    Text(text = "Football Teams")
                }

            }

            if(!soloOptions && !friendsOptions){
                Button(onClick = {friendsOptions = !friendsOptions}, modifier = Modifier) {
                    Text(text = "With Friends")
                }
            }


            if(friendsOptions){
                Text(text = "Please Write the word below")
                Spacer(modifier = Modifier.height(20.dp))
                TextField(value = textFieldValue, onValueChange = {textFieldValue = it.uppercase()})
                Button(onClick = {navigateToGame(textFieldValue)}){
                    Text(text = "Start")
                }
            }

        }


    }
}


