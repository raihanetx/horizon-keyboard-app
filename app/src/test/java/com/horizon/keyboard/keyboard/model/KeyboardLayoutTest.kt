package com.horizon.keyboard.keyboard.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardLayoutTest {

    @Test
    fun `QWERTY layout has 3 rows`() {
        assertEquals(3, KeyboardLayouts.QWERTY.rows.size)
    }

    @Test
    fun `QWERTY first row has 10 keys`() {
        assertEquals(10, KeyboardLayouts.QWERTY.rows[0].size)
    }

    @Test
    fun `QWERTY second row has 9 keys`() {
        assertEquals(9, KeyboardLayouts.QWERTY.rows[1].size)
    }

    @Test
    fun `QWERTY third row has 7 keys`() {
        assertEquals(7, KeyboardLayouts.QWERTY.rows[2].size)
    }

    @Test
    fun `QWERTY is not RTL`() {
        assertFalse(KeyboardLayouts.QWERTY.isRtl)
    }

    @Test
    fun `QWERTY has characters`() {
        assertTrue(KeyboardLayouts.QWERTY.hasCharacters)
    }

    @Test
    fun `ARABIC layout is RTL`() {
        assertTrue(KeyboardLayouts.ARABIC.isRtl)
    }

    @Test
    fun `ARABIC has shiftRows`() {
        assertNotNull(KeyboardLayouts.ARABIC.shiftRows)
        assertEquals(3, KeyboardLayouts.ARABIC.shiftRows!!.size)
    }

    @Test
    fun `HEBREW layout is RTL`() {
        assertTrue(KeyboardLayouts.HEBREW.isRtl)
    }

    @Test
    fun `SYMBOLS layout has no characters`() {
        assertFalse(KeyboardLayouts.SYMBOLS.hasCharacters)
    }

    @Test
    fun `SYMBOLS first row has numbers 1-0`() {
        val expected = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        assertEquals(expected, KeyboardLayouts.SYMBOLS.rows[0])
    }

    @Test
    fun `characterLayouts contains QWERTY, ARABIC, HEBREW`() {
        val layouts = KeyboardLayouts.characterLayouts
        assertEquals(3, layouts.size)
        assertTrue(layouts.contains(KeyboardLayouts.QWERTY))
        assertTrue(layouts.contains(KeyboardLayouts.ARABIC))
        assertTrue(layouts.contains(KeyboardLayouts.HEBREW))
    }

    @Test
    fun `QWERTY has longPressAlternates for e`() {
        val alternates = KeyboardLayouts.QWERTY.longPressAlternates["e"]
        assertNotNull(alternates)
        assertTrue(alternates!!.contains("é"))
    }

    @Test
    fun `SYMBOLS has longPressAlternates for dollar sign`() {
        val alternates = KeyboardLayouts.SYMBOLS.longPressAlternates["$"]
        assertNotNull(alternates)
        assertTrue(alternates!!.contains("€"))
    }

    @Test
    fun `ARABIC shiftRows matches row count`() {
        val layout = KeyboardLayouts.ARABIC
        assertEquals(layout.rows.size, layout.shiftRows!!.size)
    }
}
