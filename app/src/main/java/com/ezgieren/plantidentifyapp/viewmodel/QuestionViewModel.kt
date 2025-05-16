package com.ezgieren.plantidentifyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.plantidentifyapp.domain.model.Question
import com.ezgieren.plantidentifyapp.domain.usecase.GetQuestionsUseCase
import com.ezgieren.plantidentifyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {

    private val _questionState = MutableStateFlow<Resource<List<Question>>>(Resource.Loading())
    val questionState: StateFlow<Resource<List<Question>>> = _questionState

    fun fetchQuestions() {
        viewModelScope.launch {
            getQuestionsUseCase().collect { result ->
                _questionState.value = result
            }
        }
    }
}