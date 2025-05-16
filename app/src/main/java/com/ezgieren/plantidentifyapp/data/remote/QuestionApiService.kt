package com.ezgieren.plantidentifyapp.data.remote

import com.ezgieren.plantidentifyapp.data.model.question.QuestionDto
import com.ezgieren.plantidentifyapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface QuestionApiService {

    @GET(Constants.ENDPOINT_GET_QUESTIONS)
    suspend fun getQuestions(): Response<List<QuestionDto>>
}