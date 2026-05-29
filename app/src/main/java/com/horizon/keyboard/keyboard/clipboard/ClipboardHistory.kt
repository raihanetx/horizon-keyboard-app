package com.horizon.keyboard.keyboard.clipboard

import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject

class ClipboardHistory(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "clipboard_history", Context.MODE_PRIVATE
    )
    private val listeners = mutableListOf<(List<ClipboardItem>) -> Unit>()

    var items: List<ClipboardItem> = loadItems()
        private set

    private val clipboardListener = ClipboardManager.OnPrimaryClipChangedListener {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        val clip = clipboard?.primaryClip
        if (clip != null && clip.itemCount > 0) {
            val text = clip.getItemAt(0).text?.toString()
            if (!text.isNullOrBlank()) {
                addEntry(text)
            }
        }
    }

    init {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        clipboard?.addPrimaryClipChangedListener(clipboardListener)
    }

    fun addEntry(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return

        val existing = items.toMutableList()
        existing.removeAll { it.text == trimmed }
        val newItem = ClipboardItem(
            id = System.currentTimeMillis(),
            text = trimmed,
            isPinned = false
        )
        existing.add(0, newItem)

        val unpinned = existing.filter { !it.isPinned }
        val pinned = existing.filter { it.isPinned }
        items = (pinned + unpinned).take(MAX_ITEMS)

        saveItems()
        notifyListeners()
    }

    fun deleteEntry(item: ClipboardItem) {
        items = items.filter { it.id != item.id }
        saveItems()
        notifyListeners()
    }

    fun togglePin(item: ClipboardItem) {
        items = items.map {
            if (it.id == item.id) it.copy(isPinned = !it.isPinned) else it
        }
        saveItems()
        notifyListeners()
    }

    fun addListener(listener: (List<ClipboardItem>) -> Unit) {
        listeners.add(listener)
    }

    fun removeListener(listener: (List<ClipboardItem>) -> Unit) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        listeners.forEach { it(items) }
    }

    private fun saveItems() {
        val jsonArray = JSONArray()
        items.forEach { item ->
            val obj = JSONObject().apply {
                put("id", item.id)
                put("text", item.text)
                put("isPinned", item.isPinned)
            }
            jsonArray.put(obj)
        }
        prefs.edit().putString("items", jsonArray.toString()).apply()
    }

    private fun loadItems(): List<ClipboardItem> {
        val json = prefs.getString("items", null) ?: return emptyList()
        return try {
            val array = JSONArray(json)
            (0 until array.length()).mapNotNull { i ->
                val obj = array.getJSONObject(i)
                ClipboardItem(
                    id = obj.getLong("id"),
                    text = obj.getString("text"),
                    isPinned = obj.optBoolean("isPinned", false)
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    companion object {
        private const val MAX_ITEMS = 50
    }
}
