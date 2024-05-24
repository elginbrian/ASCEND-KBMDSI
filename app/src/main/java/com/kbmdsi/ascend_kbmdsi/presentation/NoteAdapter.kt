package com.kbmdsi.ascend_kbmdsi.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.local.NoteModel

class NoteAdapter(
    private val notes: List<NoteModel>,
    private val context: Context
): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textView5)
        val description: TextView = itemView.findViewById(R.id.textView6)
        val date: TextView = itemView.findViewById(R.id.textView4)
        val priority: TextView = itemView.findViewById(R.id.textView7)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note = notes[position]

        holder.title.text = note.title
        holder.description.text = note.content
        holder.date.text = note.date.substring(0, 10) + " " + note.date.substring(11, 16)
        holder.priority.text = when(note.priority){
            1 -> "High Priority"
            2 -> "Medium Priority"
            3 -> "Low Priority"
            else -> "Unknown"
        }
        holder.priority.setTextColor(
            when(note.priority){
                1 -> Color.RED
                2 -> Color.YELLOW
                3 -> Color.GREEN
                else -> Color.BLACK
            }
        )

        holder.itemView.setOnClickListener {
            val note = notes[holder.adapterPosition]

            val intent = Intent(context, NoteDetailActivity::class.java)
            intent.putExtra("note_id", note.id)
            intent.putExtra("note_title", note.title)
            intent.putExtra("note_content", note.content)
            intent.putExtra("note_date", note.date)
            intent.putExtra("note_priority", note.priority)

            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}