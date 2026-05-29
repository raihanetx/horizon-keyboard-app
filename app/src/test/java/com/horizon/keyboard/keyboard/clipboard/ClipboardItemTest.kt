package com.horizon.keyboard.keyboard.clipboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ClipboardItemTest {

    @Test
    fun `ClipboardItem stores text correctly`() {
        val item = ClipboardItem(id = 1, text = "Hello World", isPinned = false)
        assertEquals("Hello World", item.text)
        assertEquals(1L, item.id)
        assertFalse(item.isPinned)
    }

    @Test
    fun `ClipboardItem copy toggles pin`() {
        val item = ClipboardItem(id = 1, text = "test", isPinned = false)
        val pinned = item.copy(isPinned = !item.isPinned)
        assertTrue(pinned.isPinned)
        val unpinned = pinned.copy(isPinned = !pinned.isPinned)
        assertFalse(unpinned.isPinned)
    }

    @Test
    fun `ClipboardItem preserves id on copy`() {
        val item = ClipboardItem(id = 42, text = "test", isPinned = true)
        val copied = item.copy(text = "modified")
        assertEquals(42L, copied.id)
        assertEquals("modified", copied.text)
        assertTrue(copied.isPinned)
    }
}
