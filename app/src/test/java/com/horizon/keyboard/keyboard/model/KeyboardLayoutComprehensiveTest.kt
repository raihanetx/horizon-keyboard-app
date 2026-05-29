package com.horizon.keyboard.keyboard.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardLayoutComprehensiveTest {

    @Test
    fun `all character layouts have 3 rows`() {
        KeyboardLayouts.characterLayouts.forEach { layout ->
            assertEquals(
                "${layout.name} should have 3 rows",
                3, layout.rows.size
            )
        }
    }

    @Test
    fun `QWERTY row keys are lowercase letters`() {
        KeyboardLayouts.QWERTY.rows.flatten().forEach { key ->
            assertTrue(
                "Key '$key' should be a single lowercase letter",
                key.length == 1 && key[0].isLowerCase()
            )
        }
    }

    @Test
    fun `SYMBOLS row 1 is digits 0-9`() {
        val digits = KeyboardLayouts.SYMBOLS.rows[0]
        assertEquals(10, digits.size)
        digits.forEach { d ->
            assertTrue(d.length == 1 && d[0].isDigit())
        }
    }

    @Test
    fun `ARABIC shiftRows same row count as rows`() {
        val layout = KeyboardLayouts.ARABIC
        assertNotNull(layout.shiftRows)
        assertEquals(layout.rows.size, layout.shiftRows!!.size)
        layout.rows.indices.forEach { i ->
            assertEquals(
                "ARABIC shiftRows[$i] should match rows[$i] length",
                layout.rows[i].size,
                layout.shiftRows!![i].size
            )
        }
    }

    @Test
    fun `HEBREW has no shiftRows`() {
        val layout = KeyboardLayouts.HEBREW
        assertTrue(layout.shiftRows == null)
    }

    @Test
    fun `SYMBOLS has no characters flag`() {
        assertFalse(KeyboardLayouts.SYMBOLS.hasCharacters)
    }

    @Test
    fun `character layouts have characters flag`() {
        KeyboardLayouts.characterLayouts.forEach { layout ->
            assertTrue("${layout.name} should have hasCharacters=true", layout.hasCharacters)
        }
    }

    @Test
    fun `QWERTY longPressAlternates has entries for common keys`() {
        val alternates = KeyboardLayouts.QWERTY.longPressAlternates
        assertTrue(alternates.containsKey("a"))
        assertTrue(alternates.containsKey("e"))
        assertTrue(alternates.containsKey("o"))
        assertTrue(alternates.containsKey("u"))
        assertTrue(alternates.containsKey("s"))
        assertTrue(alternates.containsKey("c"))
        assertTrue(alternates.containsKey("n"))
    }

    @Test
    fun `QWERTY longPressAlternates for a contains accented variants`() {
        val aAlts = KeyboardLayouts.QWERTY.longPressAlternates["a"]!!
        assertTrue(aAlts.contains("á"))
        assertTrue(aAlts.contains("à"))
        assertTrue(aAlts.contains("ä"))
        assertTrue(aAlts.contains("â"))
    }

    @Test
    fun `SYMBOLS longPressAlternates for dollar contains currency symbols`() {
        val dollarAlts = KeyboardLayouts.SYMBOLS.longPressAlternates["$"]!!
        assertTrue(dollarAlts.contains("€"))
        assertTrue(dollarAlts.contains("£"))
        assertTrue(dollarAlts.contains("¥"))
    }

    @Test
    fun `SYMBOLS longPressAlternates for quote contains smart quotes`() {
        val quoteAlts = KeyboardLayouts.SYMBOLS.longPressAlternates["'"]!!
        assertTrue(quoteAlts.contains("\u2018"))
        assertTrue(quoteAlts.contains("\u2019"))
    }

    @Test
    fun `switching from QWERTY to ARABIC to HEBREW cycles correctly`() {
        val layouts = KeyboardLayouts.characterLayouts
        assertEquals(3, layouts.size)
        assertEquals("English", layouts[0].name)
        assertEquals("العربية", layouts[1].name)
        assertEquals("עברית", layouts[2].name)
    }

    @Test
    fun `layout switch cycle wraps around`() {
        val layouts = KeyboardLayouts.characterLayouts
        val lastIndex = layouts.lastIndex
        val nextAfterLast = (lastIndex + 1) % layouts.size
        assertEquals(0, nextAfterLast)
    }

    @Test
    fun `all layout names are non-empty`() {
        val allLayouts = KeyboardLayouts.characterLayouts + KeyboardLayouts.SYMBOLS
        allLayouts.forEach { layout ->
            assertTrue(
                "Layout name should not be empty",
                layout.name.isNotBlank()
            )
        }
    }

    @Test
    fun `no empty keys in any layout`() {
        val allLayouts = KeyboardLayouts.characterLayouts + KeyboardLayouts.SYMBOLS
        allLayouts.forEach { layout ->
            layout.rows.forEach { row ->
                row.forEach { key ->
                    assertTrue(
                        "Key should not be empty in ${layout.name}",
                        key.isNotBlank()
                    )
                }
            }
        }
    }
}
