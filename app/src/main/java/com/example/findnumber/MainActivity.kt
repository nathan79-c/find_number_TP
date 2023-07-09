package com.example.findnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private lateinit var secretNumberInput: EditText
    private lateinit var guessInput: EditText
    private lateinit var messageText: TextView
    private lateinit var submitSecretButton: Button
    private lateinit var submitGuessButton: Button

    private var secretNumber: Int = 0
    private var attempts: Int = 0
    private val maxAttempts: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        secretNumberInput = findViewById(R.id.label)
        guessInput = findViewById(R.id.label2)
        guessInput.visibility = View.INVISIBLE
        messageText = findViewById(R.id.msg_text)

        submitSecretButton = findViewById(R.id.button)
        submitGuessButton = findViewById(R.id.button2)
        submitGuessButton.visibility = View.INVISIBLE

        submitSecretButton.setOnClickListener {
            secretNumber = secretNumberInput.text.toString().toInt()
            secretNumberInput.visibility = View.GONE
            submitSecretButton.visibility = View.GONE
            guessInput.visibility = View.VISIBLE
            submitGuessButton.visibility = View.VISIBLE

            messageText.text = "Trouver le nombre"
        }

        submitGuessButton.setOnClickListener {
            val guess = guessInput.text.toString().toInt()
            attempts++
            when {
                guess < secretNumber -> messageText.text = "Le nombre est petit"
                guess > secretNumber -> messageText.text = "Le nombre est grand"
                else -> {
                    messageText.text = "Félicitations, nombre trouvé en $attempts tentatives"
                    guessInput.visibility = View.GONE
                    submitGuessButton.text = "Revenir au jeu"
                    // submitGuessButton.setOnClickListener {
                    // resetGame()
                    // }
                }
            }

            if (attempts >= maxAttempts) {
                messageText.text =
                    "Désolé, vous avez atteint le nombre maximum de tentatives. Le nombre mystère était $secretNumber"
                guessInput.visibility = View.INVISIBLE
                //  submitGuessButton.text = "Revenir au jeu"
                // submitGuessButton.setOnClickListener {
                // resetGame()
                // }
            }
        }
    }

    private fun resetGame() {
        secretNumberInput.visibility = View.VISIBLE
        submitSecretButton.visibility = View.VISIBLE

        submitGuessButton.visibility = View.INVISIBLE
        guessInput.visibility = View.INVISIBLE

        secretNumberInput.text.clear()
        guessInput.text.clear()
        secretNumber = 0
        attempts = 0

    }
}