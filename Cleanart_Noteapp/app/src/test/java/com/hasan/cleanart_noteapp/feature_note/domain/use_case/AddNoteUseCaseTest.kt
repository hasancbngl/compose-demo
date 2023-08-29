package com.hasan.cleanart_noteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.hasan.cleanart_noteapp.feature_note.data.repository.FakeNoteRepository
import com.hasan.cleanart_noteapp.feature_note.domain.model.InvalidNoteException
import com.hasan.cleanart_noteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {

    private lateinit var repository: FakeNoteRepository
    private lateinit var addNoteUseCase: AddNoteUseCase

    @Before
    fun setup() {
        repository = FakeNoteRepository()
        addNoteUseCase = AddNoteUseCase(repository)
    }

    @Test
    fun `insert note test and correct count`() = runBlocking {
        val note = Note(
            title = "test",
            content = "test",
            color = 0,
            timestamp = System.currentTimeMillis()
        )
        addNoteUseCase(note)

        val notes = repository.getNotes()
        assertThat(notes.count()).isEqualTo(1)
    }

    @Test
    fun `insert note with empty title and assert throwing exception`() = runBlocking {
        val note = Note(
            title = "",
            content = "test",
            color = 0,
            timestamp = System.currentTimeMillis()
        )

        try {
            addNoteUseCase(note)
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(InvalidNoteException::class.java)
            assertThat(e.message).isEqualTo("The title of the note can't be empty.")
        }
    }

    @Test
    fun `insert note with empty content and assert throwing exception`() = runBlocking {
        val note = Note(
            title = "test",
            content = "",
            color = 0,
            timestamp = System.currentTimeMillis()
        )

        try {
            addNoteUseCase(note)
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(InvalidNoteException::class.java)
            assertThat(e.message).isEqualTo("The content of the note can't be empty.")
        }
    }
}