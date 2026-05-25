package com.horizon.keyboard.suggestion

data class Suggestion(
    val word: String,
    val displayText: String
)

object SuggestionEngine {

    fun getSuggestions(prefix: String, maxResults: Int = 4): List<Suggestion> {
        if (prefix.isEmpty()) return emptyList()

        val lowerPrefix = prefix.lowercase()
        val matches = Dictionary.words.filter { it.startsWith(lowerPrefix) && it != lowerPrefix }

        val sorted = matches.sortedWith(
            compareByDescending<String> { it.length }
                .thenBy { it }
        )

        val fullWords = sorted.take(maxResults).map { Suggestion(word = it, displayText = it) }

        val nextWordSuggestions = getNextWordSuggestions(prefix, maxResults)
            .filter { next -> fullWords.none { it.word == next.word } }
            .take(maxResults - fullWords.size)

        return (fullWords + nextWordSuggestions).take(maxResults)
    }

    fun getNextWordSuggestions(currentWord: String, maxResults: Int = 2): List<Suggestion> {
        if (currentWord.length < 1) return emptyList()

        val lower = currentWord.lowercase()
        val matches = Dictionary.words.filter { it.startsWith(lower) }

        return matches.filter { it.length > lower.length }
            .sortedBy { it.length }
            .take(maxResults)
            .map { Suggestion(word = it, displayText = it.removePrefix(lower)) }
    }

    fun getNextWordPredictions(lastWord: String, maxResults: Int = 3): List<Suggestion> {
        if (lastWord.length < 1) return emptyList()
        val common = listOf("the", "to", "a", "in", "is", "it", "you", "that", "was", "for")
        return common.take(maxResults).map { Suggestion(word = it, displayText = it) }
    }
}
