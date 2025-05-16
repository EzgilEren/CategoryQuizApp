package com.ezgieren.categoryquizapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.categoryquizapp.domain.usecase.GetCategoriesUseCase
import com.ezgieren.categoryquizapp.domain.model.Category
import com.ezgieren.categoryquizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categoryState = MutableStateFlow<Resource<List<Category>>>(Resource.Loading())
    val categoryState: StateFlow<Resource<List<Category>>> = _categoryState

    fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                _categoryState.value = result
            }
        }
    }
}