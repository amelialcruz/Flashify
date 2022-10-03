package com.example.flashify

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        val answerOne = findViewById<TextView>(R.id.answer_one)
        val answerTwo = findViewById<TextView>(R.id.answer_two)
        val answerThree = findViewById<TextView>(R.id.answer_three)
        val answerFour= findViewById<TextView>(R.id.answer_four)
        val toggleOff = findViewById<ImageButton>(R.id.invisible)
        val toggleOn = findViewById<ImageButton>(R.id.visibility)
        val resChoices = findViewById<Button>(R.id.reset)
        var showChoices = true
        val newCard = findViewById<ImageButton>(R.id.add)
        val editCard = findViewById<ImageButton>(R.id.edit)

        //data retrieval from AddCardActivity, inputting data into flashcard
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data
            if (data != null) { // Check that we have data returned
                val string1 = data.getStringExtra("string1") // 'string1' needs to match the key we used when we put the string in the Intent
                val string2 = data.getStringExtra("string2")
                val string3 = data.getStringExtra("string3")
                val string4 = data.getStringExtra("string4")
                val string5 = data.getStringExtra("string5")
                val string6 = data.getStringExtra("string6")

                // Log the value of the strings for easier debugging
                Log.i("MainActivity", "string1: $string1")
                Log.i("MainActivity", "string2: $string2")
                Log.i("MainActivity", "string3: $string3")
                Log.i("MainActivity", "string3: $string4")
                Log.i("MainActivity", "string3: $string5")
                Log.i("MainActivity", "string3: $string6")

                flashcardQuestion.text = string1
                flashcardAnswer.text = string2
                answerOne.text = string3
                answerTwo.text = string4
                answerThree.text = string5
                answerFour.text = string6

                Snackbar.make(findViewById(R.id.flashcard_question),
                    "New flashcard successfully created!",
                    Snackbar.LENGTH_SHORT)
                    .show()

            } else {
                Log.i("MainActivity", "Returned null data from AddCardActivity")
            }
        }

        //when add button clicked, change to AddCardActivity
        newCard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }

        editCard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            intent.putExtra("stringKey1", findViewById<TextView>(R.id.flashcard_question).text.toString());
            intent.putExtra("stringKey2", findViewById<TextView>(R.id.flashcard_answer).text.toString());
            intent.putExtra("stringKey3", findViewById<TextView>(R.id.answer_one).text.toString());
            intent.putExtra("stringKey4", findViewById<TextView>(R.id.answer_two).text.toString());
            intent.putExtra("stringKey5", findViewById<TextView>(R.id.answer_three).text.toString());
            intent.putExtra("stringKey6", findViewById<TextView>(R.id.answer_four).text.toString());
            resultLauncher.launch(intent)
        }

        //setting visible icon to not display at beginning
        toggleOn.visibility = View.INVISIBLE

        //setting answer to not display at beginning
        flashcardAnswer.visibility = View.INVISIBLE

        //setting answer to display when question clicked, and hide question
        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
        }

        //setting question to display when answer clicked, and hide answer again
        flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = View.INVISIBLE
        }

        //choosing answer one -- colors
        answerOne.setOnClickListener {
            answerOne.setBackgroundResource(R.drawable.card_bg4)
            answerThree.setBackgroundResource(R.drawable.card_bg3)
        }

        //choosing answer two -- colors
        answerTwo.setOnClickListener {
            answerTwo.setBackgroundResource(R.drawable.card_bg4)
            answerThree.setBackgroundResource(R.drawable.card_bg3)
        }

        //choosing answer three -- colors
        answerThree.setOnClickListener {
            answerThree.setBackgroundResource(R.drawable.card_bg3)
        }

        //choosing answer four -- colors
        answerFour.setOnClickListener {
            answerFour.setBackgroundResource(R.drawable.card_bg4)
            answerThree.setBackgroundResource(R.drawable.card_bg3)
        }

        //hiding choices when invisible icon clicked, changing icon
        toggleOff.setOnClickListener {
            showChoices = false
            answerFour.visibility = View.INVISIBLE
            answerThree.visibility = View.INVISIBLE
            answerTwo.visibility = View.INVISIBLE
            answerOne.visibility = View.INVISIBLE
            toggleOff.visibility = View.INVISIBLE
            toggleOn.visibility = View.VISIBLE
        }

        //showing choices when visible icon clicked, changing icon back
        toggleOn.setOnClickListener {
            showChoices = true
            answerFour.visibility = View.VISIBLE
            answerThree.visibility = View.VISIBLE
            answerTwo.visibility = View.VISIBLE
            answerOne.visibility = View.VISIBLE
            toggleOff.visibility = View.VISIBLE
            toggleOn.visibility = View.INVISIBLE
        }

        //reset button
        resChoices.setOnClickListener {
            answerFour.setBackgroundResource(R.drawable.card_bg2)
            answerThree.setBackgroundResource(R.drawable.card_bg2)
            answerTwo.setBackgroundResource(R.drawable.card_bg2)
            answerOne.setBackgroundResource(R.drawable.card_bg2)
            flashcardQuestion.setText(R.string.questionOriginal)
            flashcardAnswer.setText(R.string.answerOriginal)
            answerOne.setText(R.string.answerOne)
            answerTwo.setText(R.string.answerTwo)
            answerThree.setText(R.string.answerThree)
            answerFour.setText(R.string.answerFour)
        }

    }
}