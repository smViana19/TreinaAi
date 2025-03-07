package com.samuel.treinaiappcompose.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutModel(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Int = 0,
  @ColumnInfo(name = "name")
  val name: String,
  @ColumnInfo(name = "description")
  val description: String? = null,
)