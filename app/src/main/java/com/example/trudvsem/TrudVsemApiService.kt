package com.example.trudvsem

import retrofit2.Call
import retrofit2.http.GET

interface TrudVsemApiService {
    @GET("vacancies")
    fun getVacancies(): Call<VacanciesResponse>
}