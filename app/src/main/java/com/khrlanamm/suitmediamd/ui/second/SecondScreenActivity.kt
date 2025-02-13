package com.khrlanamm.suitmediamd.ui.second

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.khrlanamm.suitmediamd.R
import com.khrlanamm.suitmediamd.databinding.ActivitySecondScreenBinding
import com.khrlanamm.suitmediamd.helper.UserAdapter
import com.khrlanamm.suitmediamd.ui.third.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTransparentStatusBar()

        val name = intent.getStringExtra(NAME)

        binding.apply {
            btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            btnChoose.setOnClickListener {
                val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
            tvName.text = name
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == UserAdapter.RESULT_CODE) {
            val fullname = data?.getStringExtra(UserAdapter.FULLNAME)
            binding.tvUsername.text = fullname
        }
    }

    private fun setTransparentStatusBar() {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = ContextCompat.getColor(this@SecondScreenActivity, R.color.white)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                insetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                @Suppress("DEPRECATION")
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    companion object {
        const val REQUEST_CODE = 110
        const val NAME = "Your Name"
    }
}
