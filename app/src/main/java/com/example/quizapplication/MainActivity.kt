package com.example.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSubmit.setOnClickListener {
            //open an alert dialog to show scores
            if(radioGroup.checkedRadioButtonId < 0) {
                //no radio button is checked
                Toast.makeText(this, "Please select an answer for question 1", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!checkBox1.isChecked && !checkBox2.isChecked && !checkBox3.isChecked) {
                //you have to select an answer for question 2
                Toast.makeText(this, "Please select at an answer for question 2", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val date = SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault()).format(Date())

            val score = checkAnswers()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Your Score")
            builder.setMessage("Congratulations! You submitted on $date, your score is $score%")
            builder.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }

        btnReset.setOnClickListener {
            radioGroup.clearCheck()
            checkBox1.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false
        }
    }

    fun checkAnswers(): Double{
        var score: Double = 0.0
        score += when(radioGroup.checkedRadioButtonId){
            R.id.radioBtn3 -> 50
            else -> 0
        }

        score += if (checkBox1.isChecked && checkBox2.isChecked) 50 else 0
        return score
    }
}