package com.milord.lab_2

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.ui.AppBarConfiguration
import org.w3c.dom.Text
import kotlin.math.sqrt

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val solveBtn: Button = findViewById(R.id.solveBtn)
        solveBtn.setOnClickListener { Solve(); }
        val switchBtn: Button = findViewById(R.id.switchBtn)
        switchBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, Second::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun Solve()
    {
        val A: EditText = findViewById(R.id.aTe)
        val B: EditText = findViewById(R.id.bTe)
        val C: EditText = findViewById(R.id.cTe)
        if (A.text.isEmpty() || B.text.isEmpty() || C.text.isEmpty())
        {
            Toast.makeText(this, "Enter all 3 arguments!", Toast.LENGTH_SHORT).show()
            return
        }
        val a = A.text.toString().toDouble()
        val b = B.text.toString().toDouble()
        val c = C.text.toString().toDouble()
        val x1: Double?
        val x2: Double?
        if (a == 0.0 && b != 0.0)
        {
            x1 = -c / b
            x2 = x1
        }
        else if (a == 0.0)
        {
            x1 = null
            x2 = null
        }
        else if (b == 0.0)
        {
            x1 = sqrt(-c / a)
            x2 = -sqrt(-c / a)
        }
        else if (c == 0.0)
        {
            x1 = 0.0
            x2 = -b / a
        }
        else
        {
            val D = b * b - 4 * a * c;
            if (D > 0)
            {
                x1 = (-b + sqrt(D)) / (2 * a)
                x2 = (-b - sqrt(D)) / (2 * a)
            }
            else if (D == 0.0)
            {
                Toast.makeText(this, "Discriminant is 0!", Toast.LENGTH_SHORT).show()
                x1 = -b / (2 * a)
                x2 = x1
            }
            else
            {
                Toast.makeText(this, "Discriminant is less than 0!", Toast.LENGTH_SHORT).show()
                x1 = null
                x2 = null
            }
        }
        val ResX1: TextView = findViewById(R.id.resultX1Tv)
        val ResX2: TextView = findViewById(R.id.resultX2Tv)
        ResX1.text = buildString {
            append("X1 = ")
            append(x1.toString())
        }
        ResX2.text = buildString {
            append("X2 = ")
            append(x2.toString())
        }
    }
}
