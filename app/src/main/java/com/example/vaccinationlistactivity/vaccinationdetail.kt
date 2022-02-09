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
        binding.textViewDetailDay1.text = vaccination?.day1
        binding.textViewDetailDay2.text = vaccination?.day2
        binding.textViewDetailDay3.text = vaccination?.day3
        binding.textViewDetailDay4.text = vaccination?.day4
        binding.textViewDetailDay5.text = vaccination?.day5
        binding.textViewDetailDay6.text = vaccination?.day6
        binding.textViewDetailDay7.text = vaccination?.day7
        binding.textViewDetailDay8.text = vaccination?.day8
        binding.textViewDetailDay9.text = vaccination?.day9
        binding.textViewDetailDay10.text = vaccination?.day10
    }
}