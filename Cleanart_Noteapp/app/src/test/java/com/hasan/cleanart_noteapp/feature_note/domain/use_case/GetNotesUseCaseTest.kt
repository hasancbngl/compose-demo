package com.hasan.cleanart_noteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.hasan.cleanart_noteapp.feature_note.data.repository.FakeNoteRepository
import com.hasan.cleanart_noteapp.feature_note.domain.model.Note
import com.hasan.cleanart_noteapp.feature_note.domain.util.NoteOrder
import com.hasan.cleanart_noteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest {

    private lateinit var getNotesUseCase: GetNotesUseCase
    private lateinit var noteRepository: FakeNoteRepository

    @Before
    fun setUp() {
        noteRepository = FakeNoteRepository()
        getNotesUseCase = GetNotesUseCase(noteRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }
        //shuffle the list to make random order
        notesToInsert.apply {
            shuffle()
            runBlocking {
                forEach {
                    noteRepository.insertNote(it)
                }
            }
        }
    }

    @Test
    fun `Order notes by title ascending, correct order`() = runBlocking {
        val notes = getNotesUseCase(NoteOrder.Title(OrderType.Ascending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }
}