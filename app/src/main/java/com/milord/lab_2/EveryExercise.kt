package com.milord.lab_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.security.Timestamp
import java.sql.Time
import kotlin.random.Random

class EveryExercise : AppCompatActivity()
{
    private var correctAnswer : Int = 0
    private var counter : Int = 0
    private var total : Int = 0
    private var cap : Int = 20
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_every_exercise)
        updateExercise()
        val answer: EditText = findViewById(R.id.editTextNumber)
        val checkAnsBtn: Button = findViewById(R.id.checkAnsBtn)
        if (Second.certain)
        {
            cap = 8
        }

        checkAnsBtn.setOnClickListener {
            if (total == cap - 1)
            {
                counter++
                total++
                Toast.makeText(
                    this,
                    "Упражнение закончено, всего правильных ответов $counter из $total возможных",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this@EveryExercise, Second::class.java)
                startActivity(intent)
            }
            else
            {
                if (answer.text.toString().isEmpty())
                {
                    Toast.makeText(this, "Введите свой ответ!", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    checkAnswer(answer.text.toString().toInt())
                    total++
                }
            }
        }
    }

    private fun checkAnswer(answer : Int)
    {
        if (answer == correctAnswer)
        {
            counter++
            Toast.makeText(this, "Правильный ответ!", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this, "Неправильный ответ!", Toast.LENGTH_SHORT).show()

        }
        val correctTotal : TextView = findViewById(R.id.correctTv)
        correctTotal.text = buildString {
            append("Правильных ответов: ")
            append(counter)
        }
        updateExercise()
    }

    private fun generateNum() : Int
    {
        return Random.nextInt(2, 10)
    }

    private fun updateExercise()
    {
        val a = if(Second.certain) Second.choice else generateNum()
        val b = generateNum()
        val leftNumber : TextView = findViewById(R.id.leftNumTv)
        val rightNumber : TextView = findViewById(R.id.rightNumTv)
        val answer : EditText = findViewById(R.id.editTextNumber)
        answer.text.clear()
        leftNumber.text = a.toString()
        rightNumber.text = b.toString()
        correctAnswer = a * b
    }
}