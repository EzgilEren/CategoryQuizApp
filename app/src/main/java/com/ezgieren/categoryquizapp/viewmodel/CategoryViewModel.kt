package com.ezgieren.categoryquizapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.categoryquizapp.data.model.CategoryDto
import com.ezgieren.categoryquizapp.repository.CategoryRepository
import com.ezgieren.categoryquizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    private val _categoryState = MutableStateFlow<Resource<List<CategoryDto>>>(Resource.Loading())
    val categoryState: StateFlow<Resource<List<CategoryDto>>> = _categoryState

    fun fetchCategories() {
        viewModelScope.launch {
            _categoryState.value = Resource.Loading()
            _categoryState.value = repository.getCategories()
        }
    }
}