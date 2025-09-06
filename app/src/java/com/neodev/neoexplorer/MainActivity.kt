package com.neodev.neoexplorer

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        listView = findViewById(R.id.listView)
        createNeoExplorerFolder()
        showFiles()
    }

    private fun createNeoExplorerFolder() {
        try {
            // Create NeoExplorer folder in app's internal storage
            val neoFolder = File(filesDir, "NeoExplorer")
            if (!neoFolder.exists()) {
                neoFolder.mkdirs()
                
                // Create some sample files
                File(neoFolder, "readme.txt").writeText("Welcome to NeoExplorer!")
                File(neoFolder, "example.jpg").createNewFile()
                File(neoFolder, "data.db").createNewFile()
                
                Toast.makeText(this, "NeoExplorer folder created!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error creating folder: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showFiles() {
        try {
            val neoFolder = File(filesDir, "NeoExplorer")
            val items = mutableListOf("📁 NeoExplorer Folder")
            
            if (neoFolder.exists() && neoFolder.isDirectory) {
                neoFolder.list()?.forEach { file -> 
                    items.add("📄 $file")
                }
                
                if (items.size == 1) {
                    items.add("No files yet")
                }
            } else {
                items.add("Folder not accessible")
            }
            
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
            listView.adapter = adapter)
            
        } catch (e: Exception) {
            Toast.makeText(this, "Error reading files", Toast.LENGTH_SHORT).show()
        }
    }
}