package com.example.vaccinationlistactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
        //vaccineList.add(
        //                Vaccination(
        //                    "Fake 1", sortedMapOf<String, Int>(
        //                        Pair("1/23/22", 100),
        //                        Pair("1/24/22", 105),
        //            Pair("1/25/22", 110)
        //        )
        //    )
        //)
        //vaccineList.add(
        //    Vaccination(
        //        "Fake 2", sortedMapOf<String, Int>(
        //            Pair("1/23/22", 500000),
        //            Pair("1/24/22", 600000),
        //            Pair("1/25/22", 700000),
        //            Pair("1/20/22", 600000),
        //        )
        //    )
        //)

        val vaccineApi = RetrofitHelper.getInstance().create(Covid19Service::class.java)
        val vaccineCall = vaccineApi.getVaccinations(10)

        vaccineCall.enqueue(object : Callback<List<Vaccination>> {
            override fun onResponse(
                call: Call<List<Vaccination>>,
                response: Response<List<Vaccination>>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                vaccineList = (response.body()
                    ?: mutableListOf<vaccinationdetail>()) as MutableList<Vaccination>
                adapter = VaccinationAdapter(vaccineList)
                binding.recyclerViewVaccinationList.adapter = adapter
                binding.recyclerViewVaccinationList.layoutManager =
                    LinearLayoutManager(this@VaccinationListActivity)
            }

            override fun onFailure(call: Call<List<Vaccination>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        }
        )
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.sortby_name -> {
                adapter.dataSet = adapter.dataSet.sortedBy {
                    it.country.compareTo("test")
                }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.sortby_lastTen -> {
                adapter.dataSet = adapter.dataSet.sortedBy {
                    (it.timeline.get(it.timeline.lastKey()?:0L))?.minus(it.timeline.get(it.timeline.firstKey())?: 0L)
                }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.sortby_total -> {
                adapter.dataSet = adapter.dataSet.sortedByDescending {
                    it.timeline.get(it.timeline.lastKey()?: 0L)
                }
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
