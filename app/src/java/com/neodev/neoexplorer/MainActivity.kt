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
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        listView = findViewById(R.id.listView)

        if (Environment.isExternalStorageManager()) {
            listFiles()
        } else {
            requestAllFilesAccess()
        }
    }

    private fun requestAllFilesAccess() {
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        intent.data = Uri.parse("package:${applicationContext.packageName}")
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (Environment.isExternalStorageManager()) {
                listFiles()
            } else {
                Toast.makeText(this, "Permission denied! App cannot work.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun listFiles() {
        try {
            // USE THE PUBLIC DIRECTORIES INSTEAD - WORKS ON ANDROID 11+
            val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val items = root.list() ?: emptyArray()
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
            listView.adapter = adapter
        } catch (e: Exception) {
            Toast.makeText(this, "Error accessing files: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}