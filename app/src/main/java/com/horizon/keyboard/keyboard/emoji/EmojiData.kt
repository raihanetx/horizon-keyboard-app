package com.horizon.keyboard.keyboard.emoji

data class EmojiCategory(
    val name: String,
    val icon: String,
    val emojis: List<String>
)

object EmojiData {
    val categories = listOf(
        EmojiCategory(
            name = "Smileys",
            icon = "\uD83D\uDE00",
            emojis = listOf(
                "\uD83D\uDE00", "\uD83D\uDE03", "\uD83D\uDE04", "\uD83D\uDE01", "\uD83D\uDE06",
                "\uD83D\uDE05", "\uD83D\uDE02", "\uD83E\uDD23", "\uD83D\uDE0A", "\uD83D\uDE07",
                "\uD83D\uDE42", "\uD83D\uDE43", "\uD83D\uDE09", "\uD83D\uDE0C", "\uD83D\uDE0D",
                "\uD83E\uDD70", "\uD83D\uDE18", "\uD83D\uDE17", "\uD83D\uDE19", "\uD83D\uDE1A",
                "\uD83D\uDE0B", "\uD83D\uDE1B", "\uD83D\uDE1C", "\uD83E\uDD2A", "\uD83D\uDE1D",
                "\uD83E\uDD11", "\uD83E\uDD17", "\uD83E\uDD2D", "\uD83E\uDD2B", "\uD83E\uDD14",
                "\uD83D\uDE10", "\uD83D\uDE11", "\uD83D\uDE36", "\uD83D\uDE0F", "\uD83D\uDE12",
                "\uD83D\uDE44", "\uD83D\uDE2C", "\uD83E\uDD25", "\uD83D\uDE14",
                "\uD83D\uDE2A", "\uD83E\uDD24", "\uD83D\uDE34", "\uD83D\uDE37", "\uD83E\uDD12",
                "\uD83E\uDD15", "\uD83E\uDD22", "\uD83E\uDD2E", "\uD83E\uDD27", "\uD83D\uDE35",
                "\uD83D\uDE31", "\uD83E\uDD2F", "\uD83D\uDE28", "\uD83D\uDE30", "\uD83D\uDE25",
                "\uD83D\uDE22", "\uD83D\uDE2D", "\uD83D\uDE32", "\uD83D\uDE1E", "\uD83D\uDE13",
                "\uD83D\uDE29", "\uD83D\uDE2B", "\uD83E\uDD71", "\uD83D\uDE24", "\uD83D\uDE21",
                "\uD83D\uDE20", "\uD83E\uDD2C", "\uD83D\uDE08", "\uD83D\uDC7F", "\uD83D\uDC80",
                "\uD83D\uDC7B", "\uD83D\uDC7D"
            )
        ),
        EmojiCategory(
            name = "People",
            icon = "\uD83D\uDC64",
            emojis = listOf(
                "\uD83D\uDC66", "\uD83D\uDC67", "\uD83D\uDC68", "\uD83D\uDC69", "\uD83D\uDC74",
                "\uD83D\uDC75", "\uD83D\uDC76", "\uD83D\uDC71", "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC66",
                "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67", "\uD83D\uDC69\u200D\uD83D\uDC69\u200D\uD83D\uDC66",
                "\uD83D\uDC68\u200D\uD83D\uDC68\u200D\uD83D\uDC67", "\uD83D\uDC5A", "\uD83D\uDC55",
                "\uD83D\uDC56", "\uD83D\uDC57", "\uD83D\uDC58", "\uD83D\uDC59", "\uD83D\uDC60",
                "\uD83D\uDC61", "\uD83D\uDC62", "\uD83D\uDC5E", "\uD83D\uDC5F", "\uD83D\uDC52",
                "\uD83D\uDC51", "\uD83D\uDC5C", "\uD83D\uDC53", "\uD83D\uDC5B", "\uD83D\uDC5D",
                "\uD83D\uDCB0", "\uD83D\uDCB3", "\uD83D\uDCB1", "\uD83D\uDCB4", "\uD83D\uDCB5",
                "\uD83D\uDCB6", "\uD83D\uDCB7", "\uD83D\uDCB8", "\uD83D\uDCB9", "\uD83D\uDCBA"
            )
        ),
        EmojiCategory(
            name = "Animals",
            icon = "\uD83D\uDC36",
            emojis = listOf(
                "\uD83D\uDC36", "\uD83D\uDC31", "\uD83D\uDC2D", "\uD83D\uDC39", "\uD83D\uDC30",
                "\uD83D\uDC3B", "\uD83D\uDC3C", "\uD83E\uDD8A", "\uD83D\uDC2F", "\uD83E\uDD81",
                "\uD83D\uDC2E", "\uD83E\uDD8B", "\uD83D\uDC37", "\uD83D\uDC3A", "\uD83D\uDC35",
                "\uD83D\uDE48", "\uD83D\uDE49", "\uD83D\uDE4A", "\uD83D\uDC12", "\uD83D\uDC14",
                "\uD83D\uDC27", "\uD83D\uDC26", "\uD83D\uDC24", "\uD83E\uDD86", "\uD83E\uDD85",
                "\uD83E\uDD89", "\uD83E\uDD87", "\uD83D\uDC38", "\uD83D\uDC2A", "\uD83D\uDC2B",
                "\uD83E\uDD92", "\uD83D\uDC18", "\uD83E\uDD8F", "\uD83D\uDC2C", "\uD83D\uDC1F",
                "\uD83D\uDC19", "\uD83D\uDC1A", "\uD83D\uDC20", "\uD83D\uDC1B", "\uD83D\uDC1C",
                "\uD83D\uDC1D", "\uD83D\uDC1E", "\uD83D\uDC33", "\uD83E\uDD80",
                "\uD83D\uDC0D", "\uD83D\uDC32", "\uD83D\uDC09", "\uD83D\uDC3E"
            )
        ),
        EmojiCategory(
            name = "Food",
            icon = "\uD83C\uDF54",
            emojis = listOf(
                "\uD83C\uDF4E", "\uD83C\uDF4A", "\uD83C\uDF4B", "\uD83C\uDF4C", "\uD83C\uDF49",
                "\uD83C\uDF47", "\uD83C\uDF53", "\uD83C\uDF52", "\uD83C\uDF51", "\uD83C\uDF4D",
                "\uD83E\uDD65", "\uD83E\uDD51", "\uD83C\uDF45", "\uD83C\uDF46", "\uD83E\uDD54",
                "\uD83E\uDD55", "\uD83C\uDF3D", "\uD83C\uDF36\uFE0F", "\uD83E\uDD52", "\uD83E\uDD5C",
                "\uD83C\uDF5E", "\uD83E\uDD50", "\uD83E\uDD56", "\uD83C\uDF57", "\uD83C\uDF54",
                "\uD83C\uDF5F", "\uD83C\uDF55", "\uD83C\uDF2D", "\uD83E\uDD69", "\uD83C\uDF2E",
                "\uD83C\uDF2F", "\uD83E\uDD59", "\uD83E\uDD5A", "\uD83C\uDF73", "\uD83E\uDD58",
                "\uD83C\uDF72", "\uD83E\uDD63", "\uD83C\uDF5C", "\uD83C\uDF63", "\uD83C\uDF71",
                "\uD83C\uDF5B", "\uD83C\uDF5A", "\uD83C\uDF59", "\uD83C\uDF70", "\uD83C\uDF82",
                "\uD83C\uDF67", "\uD83C\uDF66", "\uD83E\uDD67", "\uD83C\uDF69", "\uD83C\uDF6A"
            )
        ),
        EmojiCategory(
            name = "Travel",
            icon = "\u2708\uFE0F",
            emojis = listOf(
                "\uD83D\uDE97", "\uD83D\uDE95", "\uD83D\uDE99", "\uD83D\uDE8C", "\uD83D\uDE8E",
                "\uD83C\uDFCE\uFE0F", "\uD83D\uDE93", "\uD83D\uDE91", "\uD83D\uDE92", "\uD83D\uDE90",
                "\uD83D\uDE9A", "\uD83D\uDE9B", "\uD83D\uDE9C", "\uD83D\uDEF3\uFE0F", "\uD83D\uDEB2",
                "\uD83D\uDEF4", "\uD83C\uDFCD\uFE0F", "\uD83D\uDEA8", "\uD83D\uDE94", "\uD83D\uDE8D",
                "\u2708\uFE0F", "\uD83D\uDE81", "\uD83D\uDE80", "\uD83D\uDEF0\uFE0F", "\uD83D\uDE82",
                "\uD83D\uDE8B", "\uD83D\uDEA2", "\u26F5", "\uD83D\uDEA3", "\uD83D\uDEF6",
                "\uD83D\uDE89", "\uD83D\uDEA4", "\uD83D\uDE86", "\uD83D\uDE85", "\uD83D\uDE88",
                "\uD83D\uDE87", "\uD83D\uDE9D", "\uD83C\uDFAB", "\uD83D\uDEA5", "\uD83D\uDEA7",
                "\uD83D\uDEA6", "\u26FD", "\uD83C\uDF7C", "\uD83D\uDE8F", "\uD83D\uDEA1"
            )
        ),
        EmojiCategory(
            name = "Objects",
            icon = "\uD83D\uDCA1",
            emojis = listOf(
                "\u231A", "\uD83D\uDCF1", "\uD83D\uDCF2", "\uD83D\uDCBB", "\u2328\uFE0F",
                "\uD83D\uDDA5\uFE0F", "\uD83D\uDDA8\uFE0F", "\uD83D\uDCBD", "\uD83D\uDCFC",
                "\uD83D\uDD09", "\uD83D\uDD0A", "\uD83D\uDD14", "\uD83D\uDD15", "\uD83D\uDECE\uFE0F",
                "\uD83D\uDD6F\uFE0F", "\uD83D\uDD0E", "\uD83D\uDCDA", "\uD83D\uDCD6", "\uD83D\uDCD5",
                "\uD83D\uDCD3", "\uD83D\uDCD4", "\uD83D\uDCD8", "\uD83D\uDCD2", "\uD83D\uDCD7",
                "\uD83D\uDCD1", "\uD83D\uDCF7", "\uD83D\uDCF8", "\uD83D\uDCF9", "\uD83D\uDCFA",
                "\uD83D\uDCFB", "\uD83C\uDFA4", "\uD83C\uDFA7", "\uD83C\uDFB5", "\uD83C\uDFB6",
                "\uD83C\uDFB8", "\uD83C\uDFB9", "\uD83C\uDFBA", "\uD83C\uDFBB", "\uD83C\uDFBC",
                "\uD83C\uDFBD", "\uD83C\uDFA8", "\uD83C\uDFAA", "\uD83C\uDFAC", "\uD83C\uDFAE",
                "\uD83D\uDD26", "\uD83D\uDD2C", "\uD83D\uDD27", "\uD83D\uDD28", "\uD83D\uDD29"
            )
        ),
        EmojiCategory(
            name = "Symbols",
            icon = "\u2764\uFE0F",
            emojis = listOf(
                "\u2764\uFE0F", "\uD83D\uDC94", "\uD83D\uDC95", "\uD83D\uDC96", "\uD83D\uDC97",
                "\uD83D\uDC98", "\uD83D\uDC99", "\uD83D\uDC9A", "\uD83D\uDC9B", "\uD83D\uDC9C",
                "\uD83D\uDDA4", "\uD83D\uDC93", "\uD83D\uDC9E", "\uD83D\uDC9D", "\uD83D\uDC9F",
                "\u271D\uFE0F", "\u2620\uFE0F", "\u262A\uFE0F", "\u262F\uFE0F", "\u2638\uFE0F",
                "\u2626\uFE0F", "\u2721\uFE0F", "\u2622\uFE0F", "\u26CE", "\u2648", "\u2649",
                "\u264A", "\u264B", "\u264C", "\u264D", "\u264E", "\u264F", "\u2650", "\u2651",
                "\u2652", "\u2653", "\uD83D\uDD2F", "\u2694\uFE0F", "\uD83D\uDEE1\uFE0F",
                "\uD83D\uDD30", "\u262E\uFE0F", "\u2695\uFE0F", "\u2696\uFE0F", "\u269B\uFE0F",
                "\u2699\uFE0F", "\u2702\uFE0F", "\u2705", "\u274C", "\u274E", "\u2753",
                "\u2757", "\u2795", "\u2796", "\u2797", "\u27B0", "\u27BF"
            )
        )
    )
}
