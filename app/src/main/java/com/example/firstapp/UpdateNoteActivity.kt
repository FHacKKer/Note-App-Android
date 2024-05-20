package com.example.firstapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db : NotesDatabaseHelper
    private var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1);

        if (noteId == -1) {
            // Handle error: invalid note ID
            Toast.makeText(this, "Invalid note ID : $noteId", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if the note ID is invalid
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updatedTitleEditText.setText(note.title)
        binding.updatedContentEditText.setText(note.content)

        binding.updateNoteButton.setOnClickListener {
            val newTitle = binding.updatedTitleEditText.text.toString()
            val newContent = binding.updatedContentEditText.text.toString()

            if (newTitle.isNotEmpty() && newContent.isNotEmpty()) {
                val updatedNote = Note(noteId, newTitle, newContent)
                db.updateNote(updatedNote)
                Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Please enter both title and content", Toast.LENGTH_SHORT).show()

            }

        }

    }
}