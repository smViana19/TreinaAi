package com.samuel.treinaiappcompose.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
  tableName = "exercises",
  foreignKeys = [
    ForeignKey(
      entity = WorkoutModel::class,
      parentColumns = ["id"],
      childColumns = ["workout_id"],
      onDelete = ForeignKey.CASCADE,
    )
  ]
)
data class ExerciseModel(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Int = 0,
  @ColumnInfo(name = "name")
  val name: String? = null,
  @ColumnInfo(name = "image_url")
  val imageUrl: String? = null,
  @ColumnInfo(name = "description")
  val description: String? = null,
  @ColumnInfo(name = "workout_id")
  val workoutId: Int = 0,
)
