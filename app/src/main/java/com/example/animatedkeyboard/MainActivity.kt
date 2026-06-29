package com.example.animatedkeyboard

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.animatedkeyboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        binding.btnEnable.setOnClickListener {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
            Toast.makeText(this, "Enable KeyAura in the list and turn it ON", Toast.LENGTH_LONG).show()
        }

        binding.btnChoose.setOnClickListener {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.showInputMethodPicker()
            Toast.makeText(this, "Select KeyAura from the picker", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAboutDialog() {
        val aboutText = """
KeyAura - Your Beautiful Colored Animated Keyboard

✨ Per-key unique radial gradient animations
✨ Smart Enter key (newline vs Send/Search)
✨ Auto return to letters from numbers/symbols
✨ Roman Urdu friendly
✨ Beautiful dark neon UI
✨ Landscape friendly compact size

Made with ❤️ 
Version 1.0
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.about_title))
            .setMessage(aboutText)
            .setPositiveButton("Got it") { d, _ -> d.dismiss() }
            .show()
    }
}