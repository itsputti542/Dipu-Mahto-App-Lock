package com.dipumahto.applock

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

class LockActivity : Activity() {
    private val prefs by lazy { getSharedPreferences("lockprefs", MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }
        box.addView(TextView(this).apply {
            text = "DIPU MAHTO APP LOCK\nऐप खोलने के लिए PIN डालें"
            textSize = 22f
            gravity = Gravity.CENTER
        })
        val entry = EditText(this).apply {
            hint = "4-digit PIN"
            inputType = 2
        }
        box.addView(entry)
        box.addView(Button(this).apply {
            text = "Unlock / खोलें"
            setOnClickListener {
                if (entry.text.toString() == prefs.getString("pin", "")) finish()
                else Toast.makeText(this@LockActivity, "Wrong PIN / गलत PIN", Toast.LENGTH_SHORT).show()
            }
        })
        box.addView(Button(this).apply {
            text = "Fingerprint / Biometric"
            setOnClickListener { biometricUnlock() }
        })
        setContentView(box)
    }

    private fun biometricUnlock() {
        val executor = ContextCompat.getMainExecutor(this)
        val prompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                finish()
            }
        })
        val info = BiometricPrompt.PromptInfo.Builder()
            .setTitle("DIPU MAHTO APP LOCK")
            .setSubtitle("Verify your identity / पहचान सत्यापित करें")
            .setNegativeButtonText("Use PIN")
            .build()
        prompt.authenticate(info)
    }
}
