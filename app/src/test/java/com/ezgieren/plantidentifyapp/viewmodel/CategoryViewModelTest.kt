package com.ezgieren.plantidentifyapp.viewmodel

import com.ezgieren.plantidentifyapp.domain.usecase.GetCategoriesUseCase
import com.ezgieren.plantidentifyapp.repository.FakeCategoryRepository
import com.ezgieren.plantidentifyapp.utils.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CategoryViewModelTest {

    private lateinit var viewModel: CategoryViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val fakeRepository = FakeCategoryRepository()
        val useCase = GetCategoriesUseCase(fakeRepository)
        viewModel = CategoryViewModel(useCase)
    }

    @Test
    fun `fetchCategories başarıyla veri döner`() = runTest {
        viewModel.fetchCategories()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.categoryState.value

        // Test datas
        assertTrue(state is Resource.Success)
        assertEquals(2, (state as Resource.Success).data?.size)
        assertEquals("Bitkiler", state.data?.get(0)?.title)
        assertEquals("Mantarlar", state.data?.get(1)?.title)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}