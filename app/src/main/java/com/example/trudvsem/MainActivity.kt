package com.example.trudvsem

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trudvsem.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: VacancyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = VacancyAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter


        binding.button.setOnClickListener{
            fetchVacancies()
        }
    }

    object RetrofitInstance {
        val api: TrudVsemApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://opendata.trudvsem.ru/api/v1/") // Базовый URL для API
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrudVsemApiService::class.java)
        }
    }

    private fun fetchVacancies() {
        val call = RetrofitInstance.api.getVacancies()

        call.enqueue(object : Callback<VacanciesResponse> {
            override fun onResponse(call: Call<VacanciesResponse>, response: Response<VacanciesResponse>) {
                if (response.isSuccessful) {
                    // Получаем список вакансий
                    response.body()?.let { vacanciesResponse ->
                        adapter.vacancies = vacanciesResponse.results.vacancies
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<VacanciesResponse>, t: Throwable) {
                Log.e("Error", "Failed to fetch vacancies: ${t.message}")
            }
        })
    }
}