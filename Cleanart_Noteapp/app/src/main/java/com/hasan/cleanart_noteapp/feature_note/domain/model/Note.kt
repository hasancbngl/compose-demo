package com.hasan.cleanart_noteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hasan.cleanart_noteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(Purple40, Pink40, Yellow, Red, Gray, LightBlue, DarkBlue)
    }
}