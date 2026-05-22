package mx.com.sheff.cardcollectiontracker.core.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CardTest {

    private fun sampleCard(
        name: String = "Mickey Mouse",
        title: String? = "Brave Little Tailor",
        inkColors: List<InkColor> = listOf(InkColor.AMBER)
    ) = Card(
        id = "tfc-001",
        name = name,
        title = title,
        setCode = "TFC",
        collectorNumber = 1,
        cost = 8,
        inkColors = inkColors,
        rarity = Rarity.LEGENDARY,
        type = CardType.CHARACTER,
        lore = 4,
        strength = 5,
        willpower = 5
    )

    @Test
    fun `displayName concatenates name and title when title is present`() {
        val card = sampleCard()
        assertEquals("Mickey Mouse — Brave Little Tailor", card.displayName)
    }

    @Test
    fun `displayName uses only name when title is absent`() {
        val card = sampleCard(title = null)
        assertEquals("Mickey Mouse", card.displayName)
    }

    @Test
    fun `isDualInk is true when card has two ink colors`() {
        val card = sampleCard(inkColors = listOf(InkColor.AMBER, InkColor.STEEL))
        assertTrue(card.isDualInk)
    }

    @Test
    fun `isDualInk is false when card has single ink color`() {
        val card = sampleCard(inkColors = listOf(InkColor.AMBER))
        assertFalse(card.isDualInk)
    }
}
