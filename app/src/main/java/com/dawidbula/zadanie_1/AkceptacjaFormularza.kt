package com.dawidbula.zadanie_1

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_akceptacja_formularza.*
import java.util.*
import kotlin.collections.ArrayList

class AkceptacjaFormularza : AppCompatActivity() {

    companion object {
        const val NAME_FORM = "com.dawidbula.zadanie1.name"
        const val SURNAME_FORM = "com.dawidbula.zadanie1.surname"
        const val SEX_FORM = "com.dawidbula.zadanie1.sex"
        const val COLOR_FORM = "com.dawidbula.zadanie1.color"
        const val LANGUAGE_FORM = "com.dawidbula.zadanie1.language"
        const val BIRTHDAY_FORM = "com.dawidbula.zadanie1.birthday"
    }

    private var listOfCycleLife = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akceptacja_formularza)

        if (intent != null) {
            listOfCycleLife = intent.getStringArrayListExtra(CyklZyciaApliakcji.LIST_OF_LIFE_CYCLE_FORM)
            name_accept_form.text = intent.getStringExtra(NAME_FORM)
            surname_accept_form.text = intent.getStringExtra(SURNAME_FORM)
            sex_accept_form.text = intent.getStringExtra(SEX_FORM)
            color_accept_form.text = intent.getStringExtra(COLOR_FORM)
            language_accept_form.text = intent.getStringExtra(LANGUAGE_FORM)
            birthday_accept_form.text = intent.getStringExtra(BIRTHDAY_FORM)

        }
        addTimeStampWithMethod("onCreate()")

        val actionBar = supportActionBar
        actionBar!!.title = "Sprwadzenie danych"

        actionBar.setDisplayHomeAsUpEnabled(true)

        show_time_log.setOnClickListener {
            val intent = Intent(this, CyklZyciaApliakcji::class.java)
            intent.putStringArrayListExtra(CyklZyciaApliakcji.LIST_OF_LIFE_CYCLE_FORM, listOfCycleLife)
            startActivity(intent)
        }

    }

    /*
    przycisk nawigacyjny na górnym pasku
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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

    private fun addTimeStampWithMethod(nameMethod : String) {
        val myFormatDate = "HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(myFormatDate, Locale.getDefault())
        val timestamp = simpleDateFormat.format(android.icu.util.Calendar.getInstance())
        listOfCycleLife.add("$timestamp :: AkceptacjaFormularza :: $nameMethod")
    }


}
