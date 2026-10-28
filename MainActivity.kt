package com.dipumahto.applock

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.widget.*
import android.content.pm.PackageManager

class MainActivity : Activity() {
    private val prefs by lazy { getSharedPreferences("lockprefs", MODE_PRIVATE) }
    private val selected = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selected.addAll(prefs.getStringSet("locked_apps", emptySet()) ?: emptySet())
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 28, 24, 24)
        }
        root.addView(TextView(this).apply {
            text = "DIPU MAHTO APP LOCK\nहिंदी + English"
            textSize = 24f
            gravity = Gravity.CENTER
        })
        val pin = EditText(this).apply {
            hint = "Set 4-digit PIN / 4 अंकों का PIN"
            inputType = 2
            setText(prefs.getString("pin", ""))
        }
        root.addView(pin)
        root.addView(Button(this).apply {
            text = "Save PIN / PIN सेव करें"
            setOnClickListener {
                val p = pin.text.toString()
                if (p.length == 4 && p.all { it.isDigit() }) {
                    prefs.edit().putString("pin", p).apply()
                    Toast.makeText(this@MainActivity, "PIN saved", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(this@MainActivity, "Enter exactly 4 digits", Toast.LENGTH_SHORT).show()
            }
        })
        root.addView(Button(this).apply {
            text = "Enable App Lock Service / सेवा चालू करें"
            setOnClickListener { startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) }
        })
        root.addView(TextView(this).apply {
            text = "Choose apps to lock / लॉक करने वाले ऐप चुनें"
            textSize = 18f
            setPadding(0, 22, 0, 8)
        })
        val scroll = ScrollView(this)
        val list = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        val pm = packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { pm.getLaunchIntentForPackage(it.packageName) != null }
            .sortedBy { pm.getApplicationLabel(it).toString().lowercase() }
        for (app in apps) {
            val cb = CheckBox(this).apply {
                text = pm.getApplicationLabel(app)
                isChecked = selected.contains(app.packageName)
                setOnCheckedChangeListener { _, checked ->
                    if (checked) selected.add(app.packageName) else selected.remove(app.packageName)
                    prefs.edit().putStringSet("locked_apps", selected.toSet()).apply()
                }
            }
            list.addView(cb)
        }
        scroll.addView(list)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        setContentView(root)
    }
}
