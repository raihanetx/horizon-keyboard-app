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

    fun getAutoCorrect(word: String): String? {
        if (word.length < 2) return null
        val lower = word.lowercase()
        if (Dictionary.words.contains(lower)) return null

        var bestMatch: String? = null
        var bestScore = Int.MAX_VALUE

        for (dictWord in Dictionary.words) {
            if (dictWord.length < lower.length - 2 || dictWord.length > lower.length + 2) continue

            val score = if (isTransposition(lower, dictWord)) 1
            else levenshteinDistance(lower, dictWord)

            if (score < bestScore && score <= 2) {
                bestScore = score
                bestMatch = dictWord
            }
        }

        return if (bestMatch != null && bestScore <= 2) {
            if (word[0].isUpperCase()) bestMatch.replaceFirstChar { it.uppercase() }
            else bestMatch
        } else null
    }

    private fun isTransposition(a: String, b: String): Boolean {
        if (a.length != b.length) return false
        val diffs = mutableListOf<Int>()
        for (i in a.indices) {
            if (a[i] != b[i]) diffs.add(i)
        }
        if (diffs.size != 2) return false
        val (i, j) = diffs
        return j == i + 1 && a[i] == b[j] && a[j] == b[i]
    }

    private fun levenshteinDistance(a: String, b: String): Int {
        val m = a.length
        val n = b.length
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in 0..m) dp[i][0] = i
        for (j in 0..n) dp[0][j] = j

        for (i in 1..m) {
            for (j in 1..n) {
                val cost = if (a[i - 1] == b[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1,
                    dp[i - 1][j - 1] + cost
                )
            }
        }

        return dp[m][n]
    }
}
