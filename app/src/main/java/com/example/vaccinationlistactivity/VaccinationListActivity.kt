package com.example.vaccinationlistactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaccinationlistactivity.databinding.ActivityVaccinationListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VaccinationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVaccinationListBinding
    lateinit var adapter: VaccinationAdapter

    companion object {
        val TAG = "VaccinationListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var vaccineList = mutableListOf<Vaccination>()
        vaccineList.add(
            Vaccination(
                "Fake 1", sortedMapOf<String, Int>(
                    Pair("1/23/22", 100),
                    Pair("1/24/22", 105),
                    Pair("1/25/22", 110)
                )
            )
        )
        vaccineList.add(
            Vaccination(
                "Fake 2", sortedMapOf<String, Int>(
                    Pair("1/23/22", 500000),
                    Pair("1/24/22", 600000),
                    Pair("1/25/22", 700000),
                    Pair("1/20/22", 600000),
                )
            )
        )

        val lastDay = vaccineList[0].timeline.lastKey()
        val vaccineApi = RetrofitHelper.getInstance().create(Covid19Service::class.java)
        val vaccineCall =vaccineApi.getVaccinations(10)

        vaccineCall.enqueue(object: Callback<List<Vaccination>> {
            override fun onResponse(
                call: Call<List<Vaccination>>,
                response: Response<List<Vaccination>>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                vaccineList = (response.body()?: mutableListOf<vaccinationdetail>()) as MutableList<Vaccination>
                adapter = VaccinationAdapter(vaccineList)
                binding.recyclerViewVaccinationList.adapter = adapter
                binding.recyclerViewVaccinationList.layoutManager = LinearLayoutManager(this@VaccinationListActivity)
            }

            override fun onFailure(call: Call<List<Vaccination>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        }
        )

        val inputStream = resources.openRawResource(R.raw.vaccination)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<Vaccination>>() {}.type
        val vaccinationList = gson.fromJson<List<vaccination>>(jsonString, type)
        Log.d(TAG, "onCreate: \n$vaccinationList")

        // Create our adapter and fill the recycler view

    }
}