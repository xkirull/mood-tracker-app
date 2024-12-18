package com.example.moodtrackerapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMood(mood: Mood)

    @Query("SELECT * FROM mood_table WHERE date = :date LIMIT 1")
    fun getMoodByDate(date: String): LiveData<Mood?>

    @Query("SELECT * FROM mood_table ORDER BY date DESC")
    fun getAllMoods(): LiveData<List<Mood>>
}
