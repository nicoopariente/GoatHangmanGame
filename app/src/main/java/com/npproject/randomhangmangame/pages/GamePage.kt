package com.npproject.randomhangmangame.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.npproject.randomhangmangame.R

//Declaring the Composable Game. Receives a word String, and a lambda that will allow to navigate to the Menu page
@Composable
fun Game(word: String, navigateToGame: () -> Unit){

    //Creating list with each character of the word argument provided
    val wordToGuess = word.toList()

    //Mutable State variables
    //wordGuessed will keep track of the guessed letters by the user during the game
    //At first, wordGuessed will replace the character list to a list with the same length but with only char '_'
    var wordGuessed by remember { mutableStateOf(word.toList().map { if(it != ' ') '_' else ' '}) }
    var letterGuess by remember { mutableStateOf("") }
    var carPosition by remember { mutableIntStateOf(0) }
    var win by remember {mutableStateOf(false)}
    var lose by remember {mutableStateOf(false)}
    //Variable that will keep track of racing car position during the game match
    // Using "when" keyword to relate the different car position value to the actual expected movement
    //animationSpec will add an animation effect of movement to the car
    val carPositionUpdate by animateDpAsState(targetValue = when (carPosition){
        0 -> 0.dp
        1 -> 50.dp
        2 -> 100.dp
        3 -> 150.dp
        else -> 200.dp
    }, animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
    )

    // Function that will receive a letter as a String and update State variables depending if the letter was a good guess or not
    fun checkLetter (letter: String){
        //Creating a variable that will store a Mutable version of the wordGuessed List
        val wordGuessedUpdate = wordGuessed.toMutableList()
        var failed = true
        //Checking if the wordToGuess list contains letter. If true, we replace the '_' with the letter in list wordGuessedUpdate
        wordToGuess.forEachIndexed{index, it ->
            if(it.toString() == letter){
                wordGuessedUpdate [index] = letter.single()
                failed = false
            }

        }
        //If the letter was not found in the word list, the car will move one position
        if(failed){
            carPosition ++
        }
        //If the car position is greater than 4, the lose state variable will change to true
        if(carPosition > 4){
            lose = true
        }
        //if all '_' characters have been replaced, the user has won, and the win state variable will change to true
        wordGuessed = wordGuessedUpdate
        if(!wordGuessed.contains('_')){
            win = true
        }
    }

    //Composable that will work as the body of the Game page
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF44EF3D)).padding(top = 50.dp)){
        Column (modifier = Modifier.fillMaxWidth()) {
            //if win state variable is true, and car position is less than 5 ( did not get to the finish line), the block of code is executed
            if (win && carPosition < 5){

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){

                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        //Text with the winning message
                        Text(text = "You reached the end... As the champion.", fontSize = 35.sp, color = Color.Black, lineHeight = 40.sp, textAlign = TextAlign.Center )
                        //Button that will allow the user to navigate to the game menu and play again
                        Button(onClick = {navigateToGame()}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                            Text(text = "Play Again", color = Color.White)
                        }
                    }


                }
            }

            //if lose state variable is true, the block of code is executed
            if(lose){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        //Text composable that will display the loosing message to the user
                        Text(text = "The end of the road... For now.", fontSize = 35.sp, color = Color.Black, lineHeight = 40.sp, textAlign = TextAlign.Center   )
                        //Button that will allow the user to navigate to the game menu and play again
                        Button(onClick = {navigateToGame()}, colors = ButtonDefaults.buttonColors(Color.Black)) {
                            Text(text = "Play Again", color = Color.White)
                        }
                    }


                }
            }
            //Box composable that contains the code for the visual car racing against the user
            Box(modifier = Modifier.fillMaxWidth()){
                Column (modifier = Modifier.fillMaxWidth()){
                    Row(modifier = Modifier.fillMaxWidth()) {
                        //Car image. Offset Position x will depend on carPositionUpdate state variable
                        Image(painter = painterResource(R.drawable.car), contentDescription = "car",
                            modifier = Modifier.width(100.dp).height(100.dp).offset(x = carPositionUpdate, y = 30.dp))
                        //Flags image
                        Image(painter = painterResource(R.drawable.flags), contentDescription = "flags",
                            modifier = Modifier.size(100.dp).offset(x = 150.dp, y = 30.dp))

                    }
                    //Rode Image
                    Image(painter = painterResource(R.drawable.road), contentDescription = "road",
                        modifier = Modifier.width(400.dp).height(50.dp).padding(start = 5.dp))
                }



            }

            Spacer(modifier = Modifier.height(80.dp))

            //Box composable that will contain the code to display the guessed letters to the user
            Box(modifier = Modifier.fillMaxWidth()){
                //Text composable which value will depend on wordGuessed state variable
                Text(wordGuessed.joinToString(" "), modifier = Modifier.align(Alignment.Center), fontSize = 30.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(100.dp))

            //Box composable that will contain the text field and the button to allow the user to guess letters
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    //TextField composable. Will update the letterGuess state variable with a lambda function
                    TextField(letterGuess, {letterGuess = it.uppercase()}, modifier = Modifier.width(50.dp))

                    Spacer(modifier = Modifier.height(20.dp))

                    //Button that will call the letterGuess function to update the state variables accordingly
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













