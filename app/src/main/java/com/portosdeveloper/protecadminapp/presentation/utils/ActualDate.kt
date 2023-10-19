package com.portosdeveloper.protecadminapp.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

class ActualDate {
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd:MM:yyyy", Locale.getDefault())
    val date = dateFormat.format(calendar.time)
    private val hour = calendar.get(Calendar.HOUR_OF_DAY)
    private val minute = calendar.get(Calendar.MINUTE)
    private val second = calendar.get(Calendar.SECOND)
    val actualYear = calendar.get(Calendar.YEAR)
    val actualHourSecond = "$hour:$minute:$second"
    val actualHour = "$hour:$minute"
    val actualDate = "$date,$actualHour"
}