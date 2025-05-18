package com.ezgieren.plantidentifyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezgieren.plantidentifyapp.data.local.dao.CategoryDao
import com.ezgieren.plantidentifyapp.data.local.dao.QuestionDao
import com.ezgieren.plantidentifyapp.data.local.entity.CategoryEntity
import com.ezgieren.plantidentifyapp.data.local.entity.QuestionEntity

@Database(
    entities = [CategoryEntity::class, QuestionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun questionDao(): QuestionDao
}