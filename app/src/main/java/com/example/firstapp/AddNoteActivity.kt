package com.example.firstapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddNoteBinding
    private lateinit var db:NotesDatabaseHelper
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddNoteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db = NotesDatabaseHelper(this);

        binding.addNoteButton.setOnClickListener {
            val title = binding.addTitleEditText.text.toString()
            val content = binding.addContentEditText.text.toString()

            if(title.isEmpty() || content.isEmpty()){
                Toast.makeText(this,"Title or Content cannot be empty!",Toast.LENGTH_SHORT).show()
            }else{
                val note = Note(0,title,content)
                db.insertNote(note)
                finish()
                Toast.makeText(this,"Note Saved Successfully!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}