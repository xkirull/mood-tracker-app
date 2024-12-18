package com.example.moodtrackerapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MoodViewModel(application: Application) : AndroidViewModel(application) {
    private val moodDao = MoodDatabase.getDatabase(application).moodDao()

    val allMoods: LiveData<List<Mood>> = moodDao.getAllMoods()

    fun saveMood(state: String) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val mood = Mood(date = today, state = state)
                moodDao.insertMood(mood)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun isTodayMoodSet(): Boolean {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        return withContext(Dispatchers.IO) {
            moodDao.getMoodByDate(today) != null
        }
    }
}