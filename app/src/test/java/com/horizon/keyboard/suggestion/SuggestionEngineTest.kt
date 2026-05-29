package com.horizon.keyboard.suggestion

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SuggestionEngineTest {

    @Test
    fun `getSuggestions returns empty for short prefix`() {
        val result = SuggestionEngine.getSuggestions("a")
        assertTrue(result.isEmpty())
    }

    @Test
    fun `getSuggestions returns matches for valid prefix`() {
        val result = SuggestionEngine.getSuggestions("th")
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.word.startsWith("th") })
    }

    @Test
    fun `getSuggestions excludes exact match`() {
        val result = SuggestionEngine.getSuggestions("the")
        assertTrue(result.none { it.word == "the" })
    }

    @Test
    fun `getSuggestions returns at most maxResults`() {
        val result = SuggestionEngine.getSuggestions("th", maxResults = 2)
        assertTrue(result.size <= 2)
    }

    @Test
    fun `getSuggestions is case insensitive`() {
        val lower = SuggestionEngine.getSuggestions("th")
        val upper = SuggestionEngine.getSuggestions("TH")
        assertEquals(lower.map { it.word }, upper.map { it.word })
    }

    @Test
    fun `getAutoCorrect returns null for empty word`() {
        assertNull(SuggestionEngine.getAutoCorrect(""))
    }

    @Test
    fun `getAutoCorrect returns null for single char`() {
        assertNull(SuggestionEngine.getAutoCorrect("a"))
    }

    @Test
    fun `getAutoCorrect returns null for correct word`() {
        assertNull(SuggestionEngine.getAutoCorrect("help"))
    }

    @Test
    fun `getAutoCorrect returns correction for typo`() {
        val result = SuggestionEngine.getAutoCorrect("hlep")
        assertEquals("help", result)
    }

    @Test
    fun `getAutoCorrect preserves capitalization`() {
        val result = SuggestionEngine.getAutoCorrect("Hlep")
        assertEquals("Help", result)
    }

    @Test
    fun `getAutoCorrect returns null for distant word`() {
        assertNull(SuggestionEngine.getAutoCorrect("xyzqwerty"))
    }

    @Test
    fun `getSuggestions returns empty for empty prefix`() {
        val result = SuggestionEngine.getSuggestions("")
        assertTrue(result.isEmpty())
    }
}
