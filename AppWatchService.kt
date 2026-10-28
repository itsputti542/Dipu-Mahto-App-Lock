package com.dipumahto.applock

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class AppWatchService : AccessibilityService() {
    private var lastPackage: String? = null
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
        val pkg = event.packageName?.toString() ?: return
        if (pkg == packageName || pkg == lastPackage) return
        lastPackage = pkg
        val prefs = getSharedPreferences("lockprefs", MODE_PRIVATE)
        val locked = prefs.getStringSet("locked_apps", emptySet()) ?: emptySet()
        if (pkg in locked) {
            startActivity(android.content.Intent(this, LockActivity::class.java).apply {
                addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK or
                    android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
                putExtra("target_package", pkg)
            })
        }
    }
    override fun onInterrupt() {}
}
