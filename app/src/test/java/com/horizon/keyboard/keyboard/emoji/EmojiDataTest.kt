package com.horizon.keyboard.keyboard.emoji

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmojiDataTest {

    @Test
    fun `all categories have emojis`() {
        EmojiData.categories.forEach { category ->
            assertTrue(
                "Category '${category.name}' should have emojis",
                category.emojis.isNotEmpty()
            )
        }
    }

    @Test
    fun `all categories have unique names`() {
        val names = EmojiData.categories.map { it.name }
        assertEquals(names.size, names.toSet().size)
    }

    @Test
    fun `all categories have icons`() {
        EmojiData.categories.forEach { category ->
            assertTrue(
                "Category '${category.name}' should have an icon",
                category.icon.isNotEmpty()
            )
        }
    }

    @Test
    fun `no duplicate emojis within category`() {
        EmojiData.categories.forEach { category ->
            val unique = category.emojis.toSet()
            assertEquals(
                "Category '${category.name}' has duplicate emojis",
                category.emojis.size,
                unique.size
            )
        }
    }

    @Test
    fun `has expected categories`() {
        val names = EmojiData.categories.map { it.name }
        assertTrue(names.contains("Smileys"))
        assertTrue(names.contains("People"))
        assertTrue(names.contains("Animals"))
        assertTrue(names.contains("Food"))
        assertTrue(names.contains("Travel"))
        assertTrue(names.contains("Objects"))
        assertTrue(names.contains("Symbols"))
    }

    @Test
    fun `total emoji count is reasonable`() {
        val total = EmojiData.categories.sumOf { it.emojis.size }
        assertTrue("Should have at least 200 emojis, got $total", total >= 200)
    }
}
