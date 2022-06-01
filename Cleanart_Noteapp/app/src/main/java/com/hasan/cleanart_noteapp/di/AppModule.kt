package com.hasan.cleanart_noteapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hasan.cleanart_noteapp.feature_note.data.data_source.NoteDatabase
import com.hasan.cleanart_noteapp.feature_note.data.repository.NoteRepositoryImp
import com.hasan.cleanart_noteapp.feature_note.domain.repository.NoteRepository
import com.hasan.cleanart_noteapp.feature_note.domain.use_case.AddNoteUseCase
import com.hasan.cleanart_noteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.hasan.cleanart_noteapp.feature_note.domain.use_case.GetNotesUseCase
import com.hasan.cleanart_noteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImp(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUsesCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository)
        )
    }
}