package com.kbmdsi.ascend_kbmdsi.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "notes.db"
        private const val DATABASE_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_NOTES = """
            CREATE TABLE notes (
                id TEXT PRIMARY KEY,
                title TEXT,
                priority INTEGER,
                content TEXT,
                date TEXT
            )
        """.trimIndent()
        db.execSQL(CREATE_TABLE_NOTES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

    fun getNotes(): List<NoteModel> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM notes", null)
        val notes = mutableListOf<NoteModel>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndexOrThrow("id"))
                val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                val priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"))
                val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
                val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                val note = NoteModel(id, title, priority, content, date)
                notes.add(note)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return notes
    }

    fun addNote(note: NoteModel): String{
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("id", note.id)
            put("title", note.title)
            put("priority", note.priority)
            put("content", note.content)
            put("date", note.date)
        }

        db.insert("notes", null, values)
        db.close()
        return "Note added successfully"
    }

    fun deleteNote(id: String): String{
        val db = this.writableDatabase
        db.delete("notes", "id = ?", arrayOf(id.toString()))
        db.close()
        return "Note deleted successfully"
    }
}
