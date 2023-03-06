package com.example.quoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private val quoteText : TextView
         get() = findViewById(R.id.tv_quote)

    private val quoteAuthor : TextView
        get() = findViewById(R.id.tv_author)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())

        Log.d("Quote", "onCreate: "+mainViewModel.getQuote())
    }


    fun  setQuote(quote: Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote1())
    }
    fun onPrevious(view: View) {
        setQuote(mainViewModel.prevQuote1())
    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}