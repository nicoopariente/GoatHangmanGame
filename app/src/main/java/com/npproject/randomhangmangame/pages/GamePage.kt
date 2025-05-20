package com.npproject.randomhangmangame.pages

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.npproject.randomhangmangame.R

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
    val wordToGuess = word.toList()
    var wordGuessed by remember { mutableStateOf(word.toList().map { if(it != ' ') '_' else ' '}) }
    var letterGuess by remember { mutableStateOf("") }
    var carPosition by remember { mutableIntStateOf(0) }
    val carPositionUpdate by animateDpAsState(targetValue = when (carPosition){
        0 -> 0.dp
        1 -> 50.dp
        2 -> 100.dp
        3 -> 150.dp
        else -> 200.dp
    })


    fun checkLetter (letter: String){
        var wordGuessedUpdate = wordGuessed.toMutableList()
        var failed = true
        var winner = true
        wordToGuess.forEachIndexed{index, it ->
            if(it.toString() == letter){
                wordGuessedUpdate [index] = letter.single()
                failed = false
            }

        }
        if(failed){
            carPosition ++
        }
        wordGuessed = wordGuessedUpdate
        wordGuessed.forEach{it ->
            if(it == '_'){
                winner = false
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Green).padding(top = 50.dp)){
        Column (modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()){
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(painter = painterResource(R.drawable.car), contentDescription = "car",
                        modifier = Modifier.size(150.dp).offset(x = carPositionUpdate))
                    Image(painter = painterResource(R.drawable.flags), contentDescription = "flags",
                        modifier = Modifier.size(100.dp).offset(x = 150.dp))

                }


            }
            Spacer(modifier = Modifier.height(100.dp))
            Box(modifier = Modifier.fillMaxWidth()){
                Text(wordGuessed.joinToString(" "), modifier = Modifier.align(Alignment.Center), fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.height(200.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(letterGuess, {letterGuess = it.uppercase()})
                    Spacer(modifier = Modifier.height(20.dp))
                    Button({
                        if(letterGuess.length == 1){
                            checkLetter(letterGuess)
                            letterGuess = ""
                        }
                    }) {
                        Text("Try")
                    }
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGame(){
    val word = "Spiderman"
    val wordToGuess = word.toList()
    var wordGuessed by remember { mutableStateOf(word.toList().map { if(it != ' ') '_' else ' '}) }
    var letterGuess by remember { mutableStateOf("") }
    var carPosition by remember { mutableIntStateOf(0) }
    val carPositionUpdate by animateDpAsState(targetValue = when (carPosition){
        0 -> 0.dp
        1 -> 50.dp
        2 -> 100.dp
        3 -> 150.dp
        else -> 200.dp
    })


    fun checkLetter (letter: String){
        var wordGuessedUpdate = wordGuessed.toMutableList()
        var failed = true
        wordToGuess.forEachIndexed{index, it ->
            if(it.toString() == letter){
                wordGuessedUpdate [index] = letter.single()
                failed = false
            }

        }
        if(failed){
            carPosition ++
        }
        wordGuessed = wordGuessedUpdate
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Green).padding(top = 50.dp)){
        Column (modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()){
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(painter = painterResource(R.drawable.car), contentDescription = "car",
                        modifier = Modifier.size(150.dp).offset(x = carPositionUpdate))
                    Image(painter = painterResource(R.drawable.flags), contentDescription = "flags",
                        modifier = Modifier.size(100.dp).offset(x = 150.dp))

                }


            }
            Spacer(modifier = Modifier.height(100.dp))
            Box(modifier = Modifier.fillMaxWidth()){
                Text(wordGuessed.joinToString(" "), modifier = Modifier.align(Alignment.Center), fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.height(200.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(letterGuess, {letterGuess = it.uppercase()})
                    Spacer(modifier = Modifier.height(20.dp))
                    Button({
                        if(letterGuess.length == 1){
                            checkLetter(letterGuess)
                            letterGuess = ""
                        }
                    }) {
                        Text("Try")
                    }
                }


            }
        }
    }
}


