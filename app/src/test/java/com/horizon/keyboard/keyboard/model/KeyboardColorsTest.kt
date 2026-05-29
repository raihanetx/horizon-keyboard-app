package com.horizon.keyboard.keyboard.model

import org.junit.Assert.assertNotEquals
import org.junit.Test

class KeyboardColorsTest {

    @Test
    fun `light and dark colors are different for panelBg`() {
        assertNotEquals(LightKeyboardColors.panelBg, DarkKeyboardColors.panelBg)
    }

    @Test
    fun `light and dark colors are different for keyBg`() {
        assertNotEquals(LightKeyboardColors.keyBg, DarkKeyboardColors.keyBg)
    }

    @Test
    fun `light and dark colors are different for textColor`() {
        assertNotEquals(LightKeyboardColors.textColor, DarkKeyboardColors.textColor)
    }

    @Test
    fun `light and dark colors are different for iconColor`() {
        assertNotEquals(LightKeyboardColors.iconColor, DarkKeyboardColors.iconColor)
    }

    @Test
    fun `light and dark colors are different for borderColor`() {
        assertNotEquals(LightKeyboardColors.borderColor, DarkKeyboardColors.borderColor)
    }

    @Test
    fun `light and dark have different shiftActiveBg`() {
        assertNotEquals(LightKeyboardColors.shiftActiveBg, DarkKeyboardColors.shiftActiveBg)
    }

    @Test
    fun `light and dark have different keyBorderBottom`() {
        assertNotEquals(LightKeyboardColors.keyBorderBottom, DarkKeyboardColors.keyBorderBottom)
    }

    @Test
    fun `data class equality works`() {
        val copy = LightKeyboardColors.copy()
        assert(copy == LightKeyboardColors)
    }

    @Test
    fun `data class copy changes field`() {
        val modified = LightKeyboardColors.copy(panelBg = DarkKeyboardColors.panelBg)
        assert(modified.panelBg == DarkKeyboardColors.panelBg)
        assert(modified.keyBg == LightKeyboardColors.keyBg)
    }
}
