package com.horizon.keyboard.keyboard.model

data class KeyboardLayout(
    val name: String,
    val rows: List<List<String>>,
    val shiftRows: List<List<String>>? = null,
    val isRtl: Boolean = false,
    val hasCharacters: Boolean = true
)

object KeyboardLayouts {
    val QWERTY = KeyboardLayout(
        name = "English",
        rows = listOf(
            listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"),
            listOf("a", "s", "d", "f", "g", "h", "j", "k", "l"),
            listOf("z", "x", "c", "v", "b", "n", "m")
        ),
        isRtl = false
    )

    val ARABIC = KeyboardLayout(
        name = "العربية",
        rows = listOf(
            listOf("ض", "ص", "ث", "ق", "ف", "غ", "ع", "ه", "خ", "ح"),
            listOf("ش", "س", "ي", "ب", "ل", "ا", "ت", "ن", "م"),
            listOf("ظ", "ط", "ذ", "د", "ز", "ر", "و", "ى", "ة")
        ),
        shiftRows = listOf(
            listOf("ً", "ٌ", "ٍ", "َ", "ُ", "ِ", "ّ", "ْ", "ٰ", "ٔ"),
            listOf("لش", "لس", "لي", "لب", "لل", "لا", "لت", "لن", "لم"),
            listOf("لظ", "لط", "لذ", "لد", "لز", "لر", "لو", "لى", "لة")
        ),
        isRtl = true
    )

    val HEBREW = KeyboardLayout(
        name = "עברית",
        rows = listOf(
            listOf("ק", "ר", "א", "ט", "ו", "ן", "ם", "פ", "ש"),
            listOf("ד", "ג", "כ", "ע", "י", "ח", "ל", "ך", "פ"),
            listOf("ז", "ס", "ב", "ה", "נ", "מ", "צ", "ת", "ץ")
        ),
        isRtl = true
    )

    val SYMBOLS = KeyboardLayout(
        name = "Symbols",
        hasCharacters = false,
        rows = listOf(
            listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
            listOf("-", "/", ":", ";", "(", ")", "$", "&", "@", "\""),
            listOf(".", ",", "?", "!", "'", "`", "~", "%", "^", "*")
        )
    )

    val characterLayouts = listOf(QWERTY, ARABIC, HEBREW)
}
