package com.portosdeveloper.protecadminapp.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class UserWorkShop(
    var id: String = "",
    var image : String = "",
    var name: String = "",
    var surName: String = "",
    var email: String = "",
    var birthday: String = "",
    var identificationType: String = "",
    var identificationNumber: String = "",
    var password: String = "",
    var job: String = "",
    var workListInProgress: List<String> = listOf(),
    var workListFinish: List<String> = listOf(),
    var workListPaid: List<String> = listOf(),
    var workDay: String = "",
    var workWeek: String = "",
    var workMonth: String = "",
    var workLife: String = "",
    var dateStart: String = "",
    var actualDate: String = ""
){
    fun toJson(): String = Gson().toJson(UserWorkShop(
        id,
        if(image != "") URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else "",
        name,
        surName,
        email,
        birthday,
        identificationType,
        identificationNumber,
        password,
        job,
        workListInProgress,
        workListFinish,
        workListPaid,
        workDay,
        workWeek,
        workMonth,
        workLife,
        dateStart,
        actualDate
    ))
    companion object {
        fun fromJson(data: String): UserWorkShop = Gson().fromJson(data, UserWorkShop::class.java)
    }
}
