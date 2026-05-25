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

        val sorted = matches.sortedWith(
            compareByDescending<String> { it.length }
                .thenBy { it }
        )

        return sorted.take(maxResults).map { Suggestion(word = it, displayText = it) }
    }

    fun getNextWordSuggestions(currentWord: String, maxResults: Int = 3): List<Suggestion> {
        if (currentWord.length < 1) return emptyList()

        val lower = currentWord.lowercase()
        val matches = Dictionary.words.filter { it.startsWith(lower) }

        return matches.filter { it.length > lower.length }
            .sorted()
            .take(maxResults)
            .map { Suggestion(word = it, displayText = it.removePrefix(lower)) }
    }
}
