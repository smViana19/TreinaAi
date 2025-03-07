package com.samuel.treinaiappcompose.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
  tableName = "exercise_sets",
  foreignKeys = [
    ForeignKey(
      entity = ExerciseModel::class,
      parentColumns = ["id"],
      childColumns = ["exercise_id"],
      onDelete = ForeignKey.CASCADE,
    )
  ]
)
data class ExerciseSetModel(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Int = 0,
  @ColumnInfo(name = "exercise_id")
  var exerciseId: Int,
  @ColumnInfo(name = "reps")
  var reps: Int = 0,
  @ColumnInfo(name = "weight")
  var weight: Double = 0.0
)
