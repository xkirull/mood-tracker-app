package com.example.moodtrackerapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_table")
data class Mood(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val state: String
)
