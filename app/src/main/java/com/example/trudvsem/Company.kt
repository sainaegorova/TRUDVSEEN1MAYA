package com.example.trudvsem

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("email") val email: String?, // электронная почта
    @SerializedName("phone") val phone: String // телефон
)

data class Company(
    @SerializedName("name") val name: String // название вакансии

)
// Модель для объекта "vacancy"
data class VacancyDetails(
    @SerializedName("company") val company: Company, // Данные о компании
    @SerializedName("email") val email: String?,// электронная почта
    @SerializedName("phone") val phone: String? // телефон
)

// Модель для объекта "vacancy"
data class Vacancy(
    @SerializedName("vacancy") val details: VacancyDetails
)

// Модель данных для ответа API
data class VacanciesResponse(
    val results: Results
)


// Модель для результатов, содержащих вакансии
data class Results(
    val vacancies: List<Vacancy>
)