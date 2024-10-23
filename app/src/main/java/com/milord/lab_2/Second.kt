package com.milord.lab_2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Second : AppCompatActivity()
{

    companion object
    {
        var choice: Int = 0
        var certain : Boolean = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val choiceEt : EditText = findViewById(R.id.numForExEt)
        choiceEt.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                choice = s.toString().toInt()
            }
        })
        val everyExBtn: Button = findViewById(R.id.everyExBtn)
        everyExBtn.setOnClickListener {
            val intent = Intent(this@Second, EveryExercise::class.java)
            startActivity(intent)
        }
        val certainExBtn: Button = findViewById(R.id.certainExBtn)
        certainExBtn.setOnClickListener {
            if (choice < 2 || choice > 9)
            {
                Toast.makeText(this, "Введите цифру от 2 до 9", Toast.LENGTH_SHORT).show()
            }
            else
            {
                certain = true
                val intent = Intent(this@Second, EveryExercise::class.java)
                startActivity(intent)
            }
        }
        val switchBtn: Button = findViewById(R.id.switchBtn2)
        switchBtn.setOnClickListener {
            val intent = Intent(this@Second, MainActivity::class.java)
            startActivity(intent)
        }
    }
}