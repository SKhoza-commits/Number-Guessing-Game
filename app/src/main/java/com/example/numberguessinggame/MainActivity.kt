package com.example.numberguessinggame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var secretNumber = 0
    private var attempts = 0
    private lateinit var guessInput: EditText
    private lateinit var guessButton: Button
    private lateinit var resultText: TextView
    private lateinit var attemptsText: TextView
    private var gameWon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessInput = findViewById(R.id.guessInput)
        guessButton = findViewById(R.id.guessButton)
        resultText = findViewById(R.id.resultText)
        attemptsText = findViewById(R.id.attemptsText)

        startNewGame()

        guessButton.setOnClickListener {
            if (gameWon) {
                startNewGame()
            } else {
                checkGuess()
            }
        }
    }

    private fun startNewGame() {
        secretNumber = Random.nextInt(1, 101)
        attempts = 0
        gameWon = false
        resultText.text = "Guess a number between 1 and 100"
        attemptsText.text = "Attempts: 0"
        guessInput.text.clear()
        guessButton.text = "Guess"
        guessInput.requestFocus()
    }

    private fun checkGuess() {
        val guessText = guessInput.text.toString()

        if (guessText.isBlank()) {
            resultText.text = "Please enter a number"
            return
        }

        val guess = guessText.toIntOrNull() ?: run {
            resultText.text = "That's not a valid number!"
            return
        }

        if (guess !in 1..100) {
            resultText.text = "Please enter a number between 1 and 100"
            return
        }

        attempts++
        attemptsText.text = "Attempts: $attempts"

        when {
            guess < secretNumber -> resultText.text = "Too low! Try again."
            guess > secretNumber -> resultText.text = "Too high! Try again."
            else -> {
                resultText.text = "Congratulations! You guessed it in $attempts attempts."
                gameWon = true
                guessButton.text = "Play Again"
            }
        }

        guessInput.text.clear()
    }
}