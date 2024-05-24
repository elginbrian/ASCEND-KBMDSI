package com.kbmdsi.ascend_kbmdsi.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbmdsi.ascend_kbmdsi.data.local.NoteModel
import com.kbmdsi.ascend_kbmdsi.data.local.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: NoteRepository
): ViewModel() {
    private val _noteState = MutableStateFlow<List<NoteModel>>(emptyList())
    val note = _noteState.asStateFlow()

    init {
        getNotes(){

        }
    }

    fun getNotes(
        onFinished: (List<NoteModel>) -> Unit
    ){
        viewModelScope.launch {
            repository.getNotes().collect{
                _noteState.value = it
                Log.d("ViewModel", it.toString())
                onFinished(it)
            }
        }
    }

    fun addNote(
        note: NoteModel,
        onFinished: (String) -> Unit
    ){
        viewModelScope.launch {
            repository.addNote(note).collect{
                onFinished(it)
            }
        }
    }

    fun deleteNote(
        id: String,
        onFinished: (String) -> Unit
    ){
        viewModelScope.launch {
            repository.deleteNote(id).collect{
                onFinished(it)
            }
        }
    }

    fun updateNote(
        note: NoteModel,
        onFinished: (String) -> Unit
    ){
        viewModelScope.launch {
            repository.updateNote(note).collect{
                onFinished(it)
            }
        }
    }
}