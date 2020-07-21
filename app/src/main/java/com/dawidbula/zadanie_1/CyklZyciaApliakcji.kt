package com.dawidbula.zadanie_1

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.*

class CyklZyciaApliakcji : AppCompatActivity() {
    companion object {
        const val LIST_OF_LIFE_CYCLE_FORM = "com.dawidbula.zadanie1.life_cycle"
    }

    private lateinit var list: ListView
    private var listOfCycleLife = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cykl_zycia_apliakcji)

        val actionBar = supportActionBar
        actionBar!!.title = "Cykl życia aplikacji"
        actionBar.setDisplayHomeAsUpEnabled(true)

        listOfCycleLife = intent.getStringArrayListExtra(LIST_OF_LIFE_CYCLE_FORM)

        addTimeStampWithMethod("onCreate()")

        list = findViewById(R.id.list_view)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfCycleLife)
        list.adapter = adapter

        for (element in listOfCycleLife) {
            println(element)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun addTimeStampWithMethod(nameMathod: String) {
        val myFormatDate = "HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(myFormatDate, Locale.getDefault())
        var timestamp = simpleDateFormat.format(android.icu.util.Calendar.getInstance())
        listOfCycleLife.add("$timestamp :: CyklZyciaAplikacji :: $nameMathod")
    }

    override fun onStart() {
        super.onStart()
        addTimeStampWithMethod("onStart()")
    }

    override fun onResume() {
        super.onResume()
        addTimeStampWithMethod("onResume()")
    }

    override fun onPause() {
        super.onPause()
        addTimeStampWithMethod("onPause()")
    }

    override fun onStop() {
        super.onStop()
        addTimeStampWithMethod("onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        addTimeStampWithMethod("onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        addTimeStampWithMethod("onDestroy()")
    }
}
