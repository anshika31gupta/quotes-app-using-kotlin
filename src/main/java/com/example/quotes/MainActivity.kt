package com.example.quotes

import android.os.Bundle
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quotes.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getQuote()
        binding.nextBtn.setOnClickListener{
            getQuote()
        }

    }
    private fun getQuote(){
        setInProgress(true)
        GlobalScope.launch {
            try {
                val response = RetrofitInstance.QuoteApi.getRandomQuote()
                runOnUiThread {
                    setInProgress(false)
                    response.body()?.first()?.let {
                        setUI(it)
                    }
                }
            }
            catch (e: Exception) {
             runOnUiThread{
                 setInProgress(false)
                 Toast.makeText(applicationContext,"something gets wrong",Toast.LENGTH_SHORT).show()
             }
        }
        }

    }
    private fun setUI(quote: QuoteModel){
        binding.quotesTv.text=quote.q
        binding.authorTv.text=quote.a
    }
    private fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.nextBtn.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.nextBtn.visibility = View.VISIBLE
        }
    }
}