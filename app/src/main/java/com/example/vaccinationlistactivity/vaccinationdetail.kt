package com.example.vaccinationlistactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vaccinationlistactivity.databinding.ActivityVaccinationdetailBinding
import kotlinx.parcelize.Parcelize

private lateinit var binding: ActivityVaccinationdetailBinding

class vaccinationdetail : AppCompatActivity() {

    companion object{
        val EXTRA_vaccination = "Mars"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vaccination = intent.getParcelableExtra<Vaccination>(EXTRA_vaccination)
        binding.textViewDetailCountryName.text = vaccination?.country
        binding.textViewDetailTimeline.text = vaccination?.timeline.toString()
    }
}