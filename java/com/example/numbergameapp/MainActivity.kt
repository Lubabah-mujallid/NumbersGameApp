package com.example.numbergameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var myCL: ConstraintLayout
    private lateinit var myET: EditText
    private lateinit var myButton: Button
    private lateinit var mylist: ArrayList<String>
    private val randomNumber: Int = Random.nextInt(11)
    private var numberOfGuesses = 3
    //private var guess:Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myCL = findViewById(R.id.clRoot)
        mylist = ArrayList()

        rvList.adapter = MessageAdapter(this, mylist)
        rvList.layoutManager = LinearLayoutManager(this)

        myET = findViewById(R.id.etEntry)
        myButton = findViewById(R.id.okButton)

        myButton.setOnClickListener { Guess() }
    }

    private fun Guess() {
        var guess = myET.text.toString()
        if (guess.isNotEmpty() && guess.isDigitsOnly()) {
            checkGuess(guess.toInt())
            myET.text.clear()
            myET.clearFocus()
        }
        else {
            Snackbar.make(clRoot, "Please enter a number only!", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun checkGuess(guess:Int){
        mylist.add("You have guessed $guess")
        if (guess == randomNumber){
            mylist.add("CORRECT!! AMAZING!!")
        }
        else{
            numberOfGuesses--
            mylist.add("WRONG!! You have $numberOfGuesses guesses left!!")
            if(numberOfGuesses == 0) {mylist.add("GAME OVER!!")}
        }
    }
}