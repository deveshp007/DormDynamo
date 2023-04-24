package dex.alpha.dormdynamo.student.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dex.alpha.dormdynamo.R
import dex.alpha.dormdynamo.student.StudentDashboard
import dex.alpha.dormdynamo.student.StudentSelectedDetails


class Bed : Fragment() {

    lateinit var viewBed: View
    lateinit var rgrp : RadioGroup
    lateinit var nextButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBed =  inflater.inflate(R.layout.fragment_bed, container, false)

        rgrp = viewBed.findViewById(R.id.rgrp)

        // radioButtons

        val rb2 = viewBed.findViewById<RadioButton>(R.id.rb2)
        val rb3 = viewBed.findViewById<RadioButton>(R.id.rb3)
        val rb4 = viewBed.findViewById<RadioButton>(R.id.rb4)

        when (StudentDashboard.seater) {
            "1 Seater" -> {
                rb2.isEnabled = false
                rb3.isEnabled = false
                rb4.isEnabled = false
            }
            "2 Seater" -> {
                rb3.isEnabled = false
                rb4.isEnabled = false
            }
            "3 Seater" -> {
                rb4.isEnabled = false
            }
        }

        nextButton = viewBed.findViewById(R.id.next_button)
        nextButton.setOnClickListener() {
            var selectId = rgrp.checkedRadioButtonId
            if (selectId == -1) {
                Toast.makeText(requireContext(), "Please select any one!!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val rB = rgrp.findViewById<RadioButton>(selectId)
                StudentDashboard.bed = rB.text.toString()
                // change fragment
                //val bed = Bed()
                //requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container,bed).commit()

                val intent = Intent(requireContext(), StudentSelectedDetails::class.java)
                intent.putExtra("roomType", StudentDashboard.roomType)
                intent.putExtra("seater", StudentDashboard.seater)
                intent.putExtra("gender", StudentDashboard.gender)
                intent.putExtra("name", StudentDashboard.nameOfStudent)
                intent.putExtra("email", StudentDashboard.emailOfStudent)
                intent.putExtra("reg", StudentDashboard.registrationNumber)
                intent.putExtra("hostel", StudentDashboard.hostel)
                intent.putExtra("room", StudentDashboard.room)
                intent.putExtra("bed", StudentDashboard.bed)
                startActivity(intent)
                requireActivity().finish()

            }
        }

        return viewBed
    }

}