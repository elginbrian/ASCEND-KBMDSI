package com.kbmdsi.ascend_kbmdsi.data.local

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepository(
    private val db: DBHelper
) {
    suspend fun getNotes(): Flow<List<NoteModel>> {
        return flow {
            try {
                val result = db.getNotes()
                Log.d("NoteRepository", result.toString())
                emit(result)
                return@flow

            } catch (e: Exception) {
                emit(emptyList())
                Log.d("NoteRepository", e.message.toString())
                return@flow
            }
        }
    }
    suspend fun addNote(note: NoteModel): Flow<String> {
        return flow {
            try {
                val result = db.addNote(note)
                Log.d("NoteRepository", result)
                emit(result)
                return@flow

            } catch (e: Exception) {
                emit(e.message.toString())
                Log.d("NoteRepository", e.message.toString())
                return@flow
            }
        }
    }

    suspend fun deleteNote(id: String): Flow<String> {
        return flow {
            try {
                val result = db.deleteNote(id)
                Log.d("NoteRepository", result)
                emit(result)
                return@flow

            } catch (e: Exception) {
                emit(e.message.toString())
                Log.d("NoteRepository", e.message.toString())
                return@flow
            }
        }
    }

    suspend fun updateNote(note: NoteModel): Flow<String> {
        return flow {
            try {
                val result = db.updateNote(note)
                Log.d("NoteRepository", result)
                emit(result)
                return@flow

            } catch (e: Exception){
                emit(e.message.toString())
                Log.d("NoteRepository", e.message.toString())
                return@flow
            }
        }
    }
}