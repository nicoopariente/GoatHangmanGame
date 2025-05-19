package com.npproject.randomhangmangame.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/*
@Preview(showBackground = true)
@Composable
fun Greeting( modifier: Modifier = Modifier) {
    Column (modifier.fillMaxSize()){
        Box (modifier = Modifier.weight(1f).fillMaxWidth().background(Color.Green), contentAlignment = Alignment.Center){
            Text("Ejemplo 1")
        }
        Box (modifier = Modifier.weight(1f).fillMaxWidth()){
            Row {
                Box (modifier = Modifier.weight(1f).fillMaxHeight().background(Color.Blue), contentAlignment = Alignment.Center){
                    Text("Ejemplo 2")
                }
                Box (modifier = Modifier.weight(1f).fillMaxHeight().background(Color.Red), contentAlignment = Alignment.Center){
                    Text("Ejemplo 3")
                }
            }
        }
        Box (modifier = Modifier.weight(1f).fillMaxWidth().background(Color.Gray), contentAlignment = Alignment.BottomCenter){
            Text("Ejemplo 4")
        }
    }
}

*/

@Composable
fun Game(word: String){
    Box(modifier = Modifier.fillMaxSize().background(Color.Green)){
        Text(text=word)
    }
}