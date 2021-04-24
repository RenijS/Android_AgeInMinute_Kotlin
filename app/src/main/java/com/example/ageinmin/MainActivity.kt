package com.example.ageinmin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    /* lateinit means late initialization*/
    lateinit var selectButton: Button
    lateinit var dateSelected: TextView
    lateinit var ageMin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectButton = findViewById(R.id.selectButton)
        dateSelected = findViewById(R.id.selectedDate)
        ageMin = findViewById(R.id.ageMin)

        selectButton.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    /* Here Dialog is used to select date and age in minute is calculated*/
    fun clickDatePicker(view: View){
        /*Calender returns current time*/
        val myCalender = Calendar.getInstance()
        val currentYear = myCalender.get(Calendar.YEAR)
        val currentMonth = myCalender.get(Calendar.MONTH)
        val currentDay = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view,selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "Date Selected $selectedYear $selectedMonth $selectedDayOfMonth", Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            dateSelected.text = selectedDate

            /* sdf is a formatter which formats when applied to date*/
            val sdf = SimpleDateFormat("DD/MM/YY")
            // The formatter will parse the selected date in to Date object
            val theDate = sdf.parse(selectedDate)

            //.time gives date in millisecond which is then changed to minute be dividing
            val selectedDateToMinute = theDate!!.time/60000

            val currentDate = "$currentDay/${currentMonth}/$currentYear"
            val theCurrentDate = sdf.parse(currentDate)
            val currentDateToMin = theCurrentDate!!.time/60000

            val differenceMinute = currentDateToMin-selectedDateToMinute
            ageMin.text = differenceMinute.toString()

        }
            ,currentYear,currentMonth,currentDay ).show()
    }
}