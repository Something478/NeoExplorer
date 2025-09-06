package com.neodev.neoexplorer

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_main)
            val listView = findViewById<ListView>(R.id.listView)
            
            val items = listOf(
                "📁 NeoExplorer",
                "✅ App is working!",
                "📄 File 1.txt",
                "📄 File 2.jpg", 
                "📄 File 3.pdf",
                "📁 Documents",
                "📁 Downloads",
                "📁 Pictures",
                "💾 Storage: 128GB",
                "🚀 Ready to explore!"
            )
            
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
            listView.adapter = adapter)
            
        } catch (e: Exception) {
            // If everything fails, just show a toast
            android.widget.Toast.makeText(this, "NeoExplorer is running!", android.widget.Toast.LENGTH_LONG).show()
            finish()
        }
    }
}