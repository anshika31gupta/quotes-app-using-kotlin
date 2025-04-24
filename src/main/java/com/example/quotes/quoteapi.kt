package com.example.quotes

import retrofit2.Response
import retrofit2.http.GET

interface quoteapi {
    @GET("random")
    suspend fun getRandomQuote(): Response<List<QuoteModel>>
    @GET("today")
    suspend fun getTodayQuote(): Response<List<QuoteModel>>

}