package com.example.trudvsem

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VacancyAdapter(var vacancies: List<Vacancy>) : RecyclerView.Adapter<VacancyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vacancy_item, parent, false)
        return VacancyViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.companyName.text = vacancy.details.company.name
        holder.companyName.textSize = 14f
        holder.companyName.setTypeface(null, Typeface.BOLD)
        holder.phone.text = vacancy.details.phone
        holder.email.text = vacancy.details.email
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }
}

class VacancyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val companyName: TextView = itemView.findViewById(R.id.company_name)
    val phone: TextView = itemView.findViewById(R.id.phone)
    val email: TextView = itemView.findViewById(R.id.email)
}
