package com.horizon.keyboard.suggestion

data class Suggestion(
    val word: String,
    val displayText: String
)

object SuggestionEngine {

    fun getSuggestions(prefix: String, maxResults: Int = 3): List<Suggestion> {
        if (prefix.length < 2) return emptyList()

        val lowerPrefix = prefix.lowercase()
        val matches = Dictionary.words.filter { it.startsWith(lowerPrefix) && it != lowerPrefix }

        return matches
            .sortedWith(compareByDescending<String> { it.length }.thenBy { it })
            .take(maxResults)
            .map { Suggestion(word = it, displayText = it) }
    }
}
