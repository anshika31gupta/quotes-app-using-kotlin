package com.example.quotes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val  BASE_URL="https://zenquotes.io/api/"
    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl( BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    var QuoteApi=getInstance().create(quoteapi ::class.java)

}