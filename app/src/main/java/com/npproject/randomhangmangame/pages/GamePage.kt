package com.npproject.randomhangmangame.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.npproject.randomhangmangame.R

@Composable
fun Game(word: String, navigateToGame: () -> Unit){
    val wordToGuess = word.toList()
    var wordGuessed by remember { mutableStateOf(word.toList().map { if(it != ' ') '_' else ' '}) }
    var letterGuess by remember { mutableStateOf("") }
    var carPosition by remember { mutableIntStateOf(0) }
    var win by remember {mutableStateOf(false)}
    var lose by remember {mutableStateOf(false)}
    val carPositionUpdate by animateDpAsState(targetValue = when (carPosition){
        0 -> 0.dp
        1 -> 50.dp
        2 -> 100.dp
        3 -> 150.dp
        else -> 200.dp
    }, animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
    )


    fun checkLetter (letter: String){
        val wordGuessedUpdate = wordGuessed.toMutableList()
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
        if(carPosition > 4){
            lose = true
        }
        wordGuessed = wordGuessedUpdate
        if(!wordGuessed.contains('_')){
            win = true
        }
    }




    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF44EF3D)).padding(top = 50.dp)){
        Column (modifier = Modifier.fillMaxWidth()) {
            if (win && carPosition < 5){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "You reached the end... As the champion.", fontSize = 35.sp, color = Color.Black, lineHeight = 40.sp, textAlign = TextAlign.Center )
                        Button(onClick = {navigateToGame()}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                            Text(text = "Play Again", color = Color.White)
                        }
                    }


                }
            }
            if(lose){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "The end of the road... For now.", fontSize = 35.sp, color = Color.Black, lineHeight = 40.sp, textAlign = TextAlign.Center   )
                        Button(onClick = {navigateToGame()}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                            Text(text = "Play Again", color = Color.White)
                        }
                    }


                }
            }
            Box(modifier = Modifier.fillMaxWidth()){
                Column (modifier = Modifier.fillMaxWidth()){
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(painter = painterResource(R.drawable.car), contentDescription = "car",
                            modifier = Modifier.width(100.dp).height(100.dp).offset(x = carPositionUpdate, y = 30.dp))
                        Image(painter = painterResource(R.drawable.flags), contentDescription = "flags",
                            modifier = Modifier.size(100.dp).offset(x = 150.dp, y = 30.dp))

                    }
                    Image(painter = painterResource(R.drawable.road), contentDescription = "road",
                        modifier = Modifier.width(400.dp).height(50.dp).padding(start = 5.dp))
                }



            }
            Spacer(modifier = Modifier.height(80.dp))
            Box(modifier = Modifier.fillMaxWidth()){
                Text(wordGuessed.joinToString(" "), modifier = Modifier.align(Alignment.Center), fontSize = 30.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(100.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(letterGuess, {letterGuess = it.uppercase()}, modifier = Modifier.width(50.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    Button({
                        if(letterGuess.length == 1){
                            checkLetter(letterGuess)
                            letterGuess = ""
                        }
                    }, colors = ButtonDefaults.buttonColors(Color.Black)) {
                        Text("Try", color = Color.White)
                    }
                }


            }
        }
    }
}




@Preview
@Composable
fun PreviewGame(){
    var word = "Nicolas"
    var navigateToGame = {}
    val wordToGuess = word.toList()
    var wordGuessed by remember { mutableStateOf(word.toList().map { if(it != ' ') '_' else ' '}) }
    var letterGuess by remember { mutableStateOf("") }
    var carPosition by remember { mutableIntStateOf(0) }
    var win by remember {mutableStateOf(false)}
    var lose by remember {mutableStateOf(false)}
    val carPositionUpdate by animateDpAsState(targetValue = when (carPosition){
        0 -> 0.dp
        1 -> 50.dp
        2 -> 100.dp
        3 -> 150.dp
        else -> 200.dp
    }, animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
    )


    fun checkLetter (letter: String){
        val wordGuessedUpdate = wordGuessed.toMutableList()
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
        if(carPosition > 4){
            lose = true
        }
        wordGuessed = wordGuessedUpdate
        if(!wordGuessed.contains('_')){
            win = true
        }
    }




    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF44EF3D)).padding(top = 50.dp)){
        Column (modifier = Modifier.fillMaxWidth()) {
            if (win && carPosition < 5){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "You Won!!!", fontSize = 50.sp, color = Color.Black )
                        Button(onClick = {navigateToGame()}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                            Text(text = "Play Again", color = Color.White)
                        }
                    }


                }
            }
            if(lose){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "You Lost =(", fontSize = 50.sp, color = Color.Black )
                        Button(onClick = {navigateToGame()}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                            Text(text = "Play Again", color = Color.White)
                        }
                    }


                }
            }
            Box(modifier = Modifier.fillMaxWidth()){
                Column (modifier = Modifier.fillMaxWidth()){
                    Row(modifier = Modifier.fillMaxWidth()) {
                        //Image(painter = painterResource(R.drawable.car), contentDescription = "car",
                            //contentScale = ContentScale.Crop,
                            //modifier = Modifier.width(100.dp).height(80.dp).clip(RectangleShape).offset(x = carPositionUpdate, y = 30.dp).background(Color.Gray))
                        Image(painter = painterResource(R.drawable.car), contentDescription = "car",
                            modifier = Modifier.width(100.dp).height(100.dp).offset(x = carPositionUpdate, y = 30.dp))
                        Image(painter = painterResource(R.drawable.flags), contentDescription = "flags",
                            modifier = Modifier.size(100.dp).offset(x = 150.dp, y = 30.dp))

                    }
                    Image(painter = painterResource(R.drawable.road), contentDescription = "road",
                        modifier = Modifier.width(410.dp).height(40.dp))
                }



            }
            Spacer(modifier = Modifier.height(80.dp))
            Box(modifier = Modifier.fillMaxWidth()){
                Text(wordGuessed.joinToString(" "), modifier = Modifier.align(Alignment.Center), fontSize = 30.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(100.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(letterGuess, {letterGuess = it.uppercase()}, modifier = Modifier.width(50.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    Button({
                        if(letterGuess.length == 1){
                            checkLetter(letterGuess)
                            letterGuess = ""
                        }
                    }, colors = ButtonDefaults.buttonColors(Color.Black)) {
                        Text("Try", color = Color.White)
                    }
                }


            }
        }
    }
}










