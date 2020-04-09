package com.example.go.githubcontributors.data

import com.example.go.githubcontributors.data.model.Contributor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitBase @Inject constructor() {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    private val api: Api

    init {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

        val gson =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getContributors(callback: Callback<List<Contributor>>) {
        val call = api.getContributors()
        call.enqueue(callback)
    }
}