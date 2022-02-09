package com.example.vaccinationlistactivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class VaccinationAdapter(var dataSet: List<Vaccination>) :
    RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName: TextView
        val day1: TextView
        val day2 : TextView
        val day3 : TextView
        val day4 : TextView
        val day5 : TextView
        val day6 : TextView
        val day7 : TextView
        val day8 : TextView
        val day9 : TextView
        val day10 : TextView

        val layout : ConstraintLayout

        init {
            countryName= view.findViewById(R.id.textView_vaccinationItem_countryName)
            day1 = view.findViewById(R.id.textView_vaccinationItem_day1)
            day2 = view.findViewById(R.id.textView_vaccinationItem_day2)
            day3 = view.findViewById(R.id.textView_vaccinationItem_day3)
            day4 = view.findViewById(R.id.textView_vaccinationItem_day4)
            day5 = view.findViewById(R.id.textView_vaccinationItem_day5)
            day6 = view.findViewById(R.id.textView_vaccinationItem_day6)
            day7 = view.findViewById(R.id.textView_vaccinationItem_day7)
            day8 = view.findViewById(R.id.textView_vaccinationItem_day8)
            day9 = view.findViewById(R.id.textView_vaccinationItem_day9)
            day10 = view.findViewById(R.id.textView_vaccinationItem_day10)
            layout = view.findViewById(R.id.layout_vaccinationItem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_vaccination, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val vaccination = dataSet[position]
        viewHolder.countryName.text = vaccination.country
        viewHolder.day1.text = vaccination.day1
        viewHolder.day2.text = vaccination.day2
        viewHolder.day3.text = vaccination.day3
        viewHolder.day4.text = vaccination.day4
        viewHolder.day5.text = vaccination.day5
        viewHolder.day6.text = vaccination.day6
        viewHolder.day7.text = vaccination.day7
        viewHolder.day8.text = vaccination.day8
        viewHolder.day9.text = vaccination.day9
        viewHolder.day10.text = vaccination.day10

        viewHolder.layout.setOnClickListener {
            //Toast.makeText(it.context, "Hi, you clicked on ${vaccination.name}", Toast.LENGTH_SHORT).show()
            val context = viewHolder.layout.context
            val vaccinationDetailIntent = Intent(context, vaccinationdetail::class.java).apply{
                putExtra(vaccinationdetail.EXTRA_vaccination, vaccination)
            }
            context.startActivity(vaccinationDetailIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}