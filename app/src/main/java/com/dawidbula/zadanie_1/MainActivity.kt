package com.dawidbula.zadanie_1

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    /*
    pola do odwoływania się do poszczególnych widoków
    lateinit - pozwala by pola miały wartość null na początku, z góry wiem że w metodzie onCreate wszystko zostanie odpowiednio przypisane
     */
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var colorSpinner: Spinner
    private lateinit var languageChooseToggleButton: ToggleButton
    private lateinit var birthdayButton: Button
    private lateinit var birthdayTextView: TextView
    private lateinit var sendButton: Button

    /*
    zmienne do przechowywania danych, jak również do testowania aplikacji przed jej pełnym ukończeniem
     */
    private var name = "Jan (default)"
    private var surname = "Kowalski  (default)"
    private var sex = "Inna"
    private var color = "Niebieski "
    private var colors = arrayOf("Niebieski", "Zielony", "Czerwony", "Biały", "Czarny")
    private var languageToChoose = "Kotlin"
    private var birthdayDate = "01.01.0001"
    private var cal = Calendar.getInstance()
    private var listOfCycleLife = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addTimeStampWithMethod("onCreate()")

        nameEditText = findViewById(R.id.name)
        surnameEditText = findViewById(R.id.lastname)
        colorSpinner = findViewById(R.id.favorite_color)
        colorSpinner.onItemSelectedListener = this

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.adapter = aa

        languageChooseToggleButton = findViewById(R.id.languageToggleButton)
        languageChooseToggleButton.textOff = "Kotlin"
        languageChooseToggleButton.setOnCheckedChangeListener { _, isChecked ->
            languageToChoose = if (isChecked) {
                "Java"
            } else {
                "Kotlin"
            }
        }

        birthdayTextView = findViewById(R.id.birthday_date_text_view)
        birthdayButton = findViewById(R.id.birthday_date_button)

        // android studio pokazywał ostrzeżenie, więc by się go pozbyć posłużyłem się zapisem labda
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, p1, p2, p3 ->
            cal.set(Calendar.YEAR, p1)
            cal.set(Calendar.MONTH, p2)
            cal.set(Calendar.DAY_OF_MONTH, p3)
            updateDateInView()
        }

        // android studio pokazywał ostrzeżenie, więc by się go pozbyć posłużyłem się zapisem labda
        birthdayButton.setOnClickListener {
            DatePickerDialog(
                this@MainActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        sendButton = findViewById(R.id.sendForm)

        sendButton.isEnabled = false

        sendButton.setOnClickListener {

            name = nameEditText.text.toString()
            surname = surnameEditText.text.toString()

            val intent = Intent(this, AkceptacjaFormularza::class.java)
            intent.putExtra(AkceptacjaFormularza.NAME_FORM, name)
            intent.putExtra(AkceptacjaFormularza.SURNAME_FORM, surname)
            intent.putExtra(AkceptacjaFormularza.SEX_FORM, sex)
            intent.putExtra(AkceptacjaFormularza.COLOR_FORM, color)
            intent.putExtra(AkceptacjaFormularza.LANGUAGE_FORM, languageToChoose)
            intent.putExtra(AkceptacjaFormularza.BIRTHDAY_FORM, birthdayDate)
            intent.putStringArrayListExtra(
                CyklZyciaApliakcji.LIST_OF_LIFE_CYCLE_FORM,
                listOfCycleLife
            )
            startActivity(intent)
        }
    }

    fun addTimeStampWithMethod(nameMathod: String) {
        val myFormatDate = "HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(myFormatDate, Locale.getDefault())
        var timestamp = simpleDateFormat.format(android.icu.util.Calendar.getInstance())
        listOfCycleLife.add("$timestamp :: MainActivity :: $nameMathod")
    }

    /*
    uaktualnie daty po wciśnięciu przycisku
     */
    private fun updateDateInView() {
        val myFormatDate = "dd.MM.yyy"
        val simpleDateFormat = SimpleDateFormat(myFormatDate, Locale.getDefault())
        birthdayTextView.text = simpleDateFormat.format(cal.time)
        birthdayDate = simpleDateFormat.format(cal.time).toString()
        sendButton.isEnabled = true
    }

    /*
    gdy nie zostanie wybrany żadny kolor, to zrób to co jest w funkcji
    na początku przypisuje do zmiennej wartość niebieski to nie trzeba tu nic definiować
     */
    override fun onNothingSelected(p0: AdapterView<*>?) {
        color = colors[0]
    }

    /*
    gdy użytkownik zmieni swój ulubiony kolor to włącza się te funkcja która powoduje przypisanie odpowiedniego koloru
     */
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        color = colors[p2]
    }


    /*
    funkcja odpowiadające za wybranie płci
    czyli do radioButton
     */
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            sex = when (view.id) {
                R.id.male -> {
                    "Mężczyzna"
                }
                R.id.female -> {
                    "Kobieta"
                }
                else -> {
                    "Inna"
                }
            }
        }
    }

    /*
     funkcja do sprawdzenia poprawności przesyłanych danych
     w sytuacji gdy tutaj dane zostaną źle wyświetlone w consoli
     łatwiej jest sprawdzić że coś poszło nie tak podczas wpisywania danych
     natomiast gdy tutaj dane są poprawne do błąd przyszły może sugerować
     że podczas przekazywania danych wystąpił błąd

 */
    fun onClickSendForm() {
        name = nameEditText.text.toString()
        surname = surnameEditText.text.toString()

        println("################################")
        println("Imie: $name")
        println("Nazwisko: $surname")
        println("Płeć: $sex")
        println("Kolor: $color")
        println("Język programowania: $languageToChoose")
        println("Data urodzenia: $birthdayDate")
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
