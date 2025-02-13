package com.khrlanamm.suitmediamd.ui.first

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.khrlanamm.suitmediamd.R
import com.khrlanamm.suitmediamd.databinding.ActivityMainBinding
import com.khrlanamm.suitmediamd.ui.second.SecondScreenActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnCheck.setOnClickListener {
                val inputText = edPalindrome.text.toString()
                if (inputText.isNotEmpty()) {
                    val isPalindrome = checkPalindrome(inputText)
                    showAlertDialog(inputText, isPalindrome)
                } else {
                    showAlertDialog("", false, "Please enter text", isError = true)
                }
            }

            btnNext.setOnClickListener {
                val name = edName.text.toString()
                if (name.isNotEmpty()) {
                    val intent = Intent(this@MainActivity, SecondScreenActivity::class.java)
                    startActivity(intent)
                } else {
                    showAlertDialog("", false, "Please enter your name", isError = true)
                }
            }
        }
    }

    private fun checkPalindrome(text: String): Boolean {
        val processedText = text.lowercase().replace(" ", "")
        return processedText == processedText.reversed()
    }

    private fun showAlertDialog(text: String, isPalindrome: Boolean, customMessage: String? = null, isError: Boolean = false) {
        val title = when {
            isError -> "Error"
            isPalindrome -> "isPalindrome"
            else -> "notPalindrome"
        }

        val message = customMessage ?: "\"$text\" ${if (isPalindrome) "is a Palindrome" else "is NOT a palindrome"}"

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setIcon(R.drawable.icon)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}