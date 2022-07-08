package com.example.dobcalctwo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView?= null
    private var tvAgeInMinutes: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDateClicker : Button = findViewById(R.id.buttonDateClicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        buttonDateClicker.setOnClickListener{
            clickDatePicker()

        }
    }
    private fun clickDatePicker(){

        val myCalendar= Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val pickedDate = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth->
            Toast.makeText(this,
                "Year was $selectedYear, month was ${selectedMonth+1}, day of month was $selectedDayOfMonth"
                , Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate?.setText(selectedDate)

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
            val theDate = simpleDateFormat.parse(selectedDate)
            theDate?.let{
                val selectedDateInMinutes = theDate.time / 60000
                val currentDate=simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                currentDate?.let{
                val currentDateInMinutes=currentDate.time/60000
                val differenceInMinutes = currentDateInMinutes-selectedDateInMinutes
                tvAgeInMinutes?.text = differenceInMinutes.toString()
                }
            }
        },
            year,
            month,
            day
        )
        pickedDate.datePicker.maxDate= System.currentTimeMillis() - 86400000
        pickedDate.show()
    }
}
