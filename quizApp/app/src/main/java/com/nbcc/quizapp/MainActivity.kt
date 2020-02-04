package com.nbcc.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //list of questions
    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true)
    )

    //question that are currently displayed in a view
    private var questionIndex = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        updateView();

        //assign event listeners to answer buttons
        findViewById<Button>(R.id.false_button)
            .setOnClickListener { evaluateAnswer(false) }
        findViewById<Button>(R.id.true_button)
            .setOnClickListener { evaluateAnswer(true) }


        //iterate through questions
        findViewById<Button>(R.id.next_button).setOnClickListener {
            questionIndex++;
            if(questionIndex % questionBank.size == 0){
                questionIndex = 0;
            }
            updateView();

        }
        findViewById<Button>(R.id.prev_button).setOnClickListener {
            questionIndex--;
            if(questionIndex == -1){
                questionIndex = questionBank.size - 1;
            }
            updateView();

        }
    }

    /**
     * Shows new question in a text label
     */
    private fun updateView() {
        //set text to resource Id(question)
        findViewById<TextView>(R.id.question_label)
            .setText(questionBank[questionIndex].resourceId);
    }

    /**
     * Evaluates user answer and triggers the toast with correct answer
     * @param userAnswer true or false user input
     */
    private fun evaluateAnswer(userAnswer: Boolean) {
        if (userAnswer == questionBank[questionIndex].answer) {
            Toast.makeText(
                applicationContext,
                "Correct!", Toast.LENGTH_SHORT
            ).show();
        } else {
            Toast.makeText(
                applicationContext,
                "Incorrect!", Toast.LENGTH_SHORT
            ).show();
        }
    }
}
