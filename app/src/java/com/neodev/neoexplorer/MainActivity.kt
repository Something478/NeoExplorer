package com.neodev.neoexplorer // <- MUST match your package name

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

class MainActivity : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE = 101
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // This connects to your XML UI

        // Find the ListView from the layout
        listView = findViewById(R.id.listView)

        // Check if we already have permission
        if (checkPermission()) {
            // We have permission, show the files
            listFiles()
        } else {
            // We don't have permission, ask for it
            requestPermission()
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_CODE
        )
    }

    // This function is called after the user accepts or denies the permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, show the files!
                listFiles()
            } else {
                // Permission was denied. Show a message and maybe close the app.
                Toast.makeText(this, "Permission denied! App cannot work.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    // THE MOST IMPORTANT FUNCTION: This actually lists the files
    private fun listFiles() {
        // Get the root storage directory
        val root = Environment.getExternalStorageDirectory()
        // List all files and folders in it
        val items = root.list() ?: emptyArray()

        // Create a simple list adapter to show the items
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, // This is a built-in simple layout
            items
        )
        // Connect the adapter to the ListView
        listView.adapter = adapter
    }
}
