package com.ezgieren.plantidentifyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.plantidentifyapp.domain.usecase.GetCategoriesUseCase
import com.ezgieren.plantidentifyapp.domain.model.Category
import com.ezgieren.plantidentifyapp.utils.Resource
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