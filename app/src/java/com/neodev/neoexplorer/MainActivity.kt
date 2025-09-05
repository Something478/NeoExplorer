package com.neodev.neoexplorer

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        listView = findViewById(R.id.listView)
        showFiles()
    }

    private fun showFiles() {
        val items = listOf(
            "📁 Downloads",
            "  📄 document.pdf",
            "  📄 image.jpg",
            "📁 Documents",
            "  📄 notes.txt",
            "  📄 project.zip",
            "📁 Music",
            "  📄 song.mp3",
            "📁 Videos",
            "  📄 video.mp4",
            "NeoExplorer - Ready"
        )
        
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter
    }
}