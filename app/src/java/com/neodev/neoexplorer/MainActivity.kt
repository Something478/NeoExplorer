package com.neodev.neoexplorer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_main)
            listView = findViewById(R.id.listView)

            if (Environment.isExternalStorageManager()) {
                listFiles()
            } else {
                requestAllFilesAccess()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "App crashed: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun requestAllFilesAccess() {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = Uri.parse("package:$packageName")
            startActivityForResult(intent, REQUEST_CODE)
        } catch (e: Exception) {
            Toast.makeText(this, "Cannot request permission", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (Environment.isExternalStorageManager()) {
                listFiles()
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun listFiles() {
        try {
            // SAFE way to get root directory
            val root = getExternalFilesDir(null)?.parentFile ?: return
            val items = root.list() ?: emptyArray()
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
            listView.adapter = adapter
        } catch (e: Exception) {
            Toast.makeText(this, "Cannot read files", Toast.LENGTH_LONG).show()
        }
    }
}