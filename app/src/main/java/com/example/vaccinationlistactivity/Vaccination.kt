package com.example.vaccinationlistactivity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Vaccination(
    val country: String,
    val timeline: SortedMap<String, Long>
    ): Parcelable, Comparable<Vaccination> {
    override fun compareTo(other: Vaccination): Int {
        TODO("Not yet implemented")
    }
}