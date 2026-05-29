package com.horizon.keyboard.keyboard.model

data class KeyboardLayout(
    val name: String,
    val rows: List<List<String>>,
    val shiftRows: List<List<String>>? = null,
    val isRtl: Boolean = false,
    val hasCharacters: Boolean = true,
    val longPressAlternates: Map<String, List<String>> = emptyMap()
)

object KeyboardLayouts {
    val QWERTY = KeyboardLayout(
        name = "English",
        rows = listOf(
            listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"),
            listOf("a", "s", "d", "f", "g", "h", "j", "k", "l"),
            listOf("z", "x", "c", "v", "b", "n", "m")
        ),
        isRtl = false,
        longPressAlternates = mapOf(
            "q" to listOf("1"),
            "w" to listOf("2"),
            "e" to listOf("3", "é", "è", "ê", "ë"),
            "r" to listOf("4"),
            "t" to listOf("5"),
            "y" to listOf("6", "ý", "ÿ"),
            "u" to listOf("7", "ú", "ù", "û", "ü"),
            "i" to listOf("8", "í", "ì", "î", "ï"),
            "o" to listOf("9", "ó", "ò", "ô", "õ", "ö"),
            "p" to listOf("0"),
            "a" to listOf("á", "à", "â", "ä", "ã", "å", "æ"),
            "s" to listOf("ß", "ś", "š"),
            "d" to listOf("ð", "ď"),
            "f" to listOf("ƒ"),
            "g" to listOf("ğ", "ġ"),
            "h" to listOf("ħ"),
            "j" to listOf("ĵ"),
            "k" to listOf("ķ"),
            "l" to listOf("ł", "ľ", "ĺ"),
            "z" to listOf("ž", "ź", "ż"),
            "c" to listOf("ç", "ć", "č"),
            "n" to listOf("ñ", "ń", "ň"),
            "m" to listOf("µ")
        )
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
        ),
        longPressAlternates = mapOf(
            "1" to listOf("½", "⅓", "¼"),
            "2" to listOf("⅔"),
            "3" to listOf("¾", "⅜"),
            "-" to listOf("–", "—"),
            "/" to listOf("÷"),
            "." to listOf("…"),
            "?" to listOf("¿"),
            "!" to listOf("¡"),
            "'" to listOf("\u2018", "\u2019"),
            "\"" to listOf("\u201C", "\u201D"),
            "$" to listOf("€", "£", "¥", "¢", "₹"),
            "&" to listOf("§"),
            "@" to listOf("©", "®", "™")
        )
    )

    val characterLayouts = listOf(QWERTY, ARABIC, HEBREW)
}
