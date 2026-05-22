package mx.com.sheff.cardcollectiontracker.core.network.mapper

import mx.com.sheff.cardcollectiontracker.core.domain.model.CardType
import mx.com.sheff.cardcollectiontracker.core.domain.model.InkColor
import mx.com.sheff.cardcollectiontracker.core.domain.model.Rarity
import mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto.CardDto
import mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto.CardSetRefDto
import mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto.DigitalImagesDto
import mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto.ImageUrisDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CardMappersTest {

    /** Realistic Elsa card from Lorcast docs example. */
    private val elsaDto = CardDto(
        id = "crd_cbc18e77d7ec4d50bf19650a9a559686",
        name = "Elsa",
        version = "Spirit of Winter",
        layout = "normal",
        releasedAt = "2023-08-18",
        imageUris = ImageUrisDto(
            digital = DigitalImagesDto(
                small = "https://cards.lorcast.io/.../small.avif",
                normal = "https://cards.lorcast.io/.../normal.avif",
                large = "https://cards.lorcast.io/.../large.avif"
            )
        ),
        cost = 8,
        inkwell = false,
        ink = "Amethyst",
        type = listOf("Character"),
        classifications = listOf("Floodborn", "Hero", "Queen", "Sorcerer"),
        text = "Shift 6. DEEP FREEZE ...",
        moveCost = null,
        strength = 4,
        willpower = 6,
        lore = 3,
        rarity = "Enchanted",
        illustrators = listOf("Matthew Robert Davies"),
        collectorNumber = "207",
        lang = "en",
        flavorText = null,
        tcgPlayerId = 510153,
        legalities = null,
        set = CardSetRefDto(
            id = "set_7ecb0e0c71af496a9e0110e23824e0a5",
            code = "1",
            name = "The First Chapter"
        ),
        prices = null
    )

    @Test
    fun `maps a complete card correctly`() {
        val card = elsaDto.toDomain()!!

        assertEquals("crd_cbc18e77d7ec4d50bf19650a9a559686", card.id)
        assertEquals("Elsa", card.name)
        assertEquals("Spirit of Winter", card.title)
        assertEquals("1", card.setCode)
        assertEquals(207, card.collectorNumber)
        assertEquals(8, card.cost)
        assertEquals(listOf(InkColor.AMETHYST), card.inkColors)
        assertEquals(Rarity.ENCHANTED, card.rarity)
        assertEquals(CardType.CHARACTER, card.type)
        assertEquals(3, card.lore)
        assertEquals(4, card.strength)
        assertEquals(6, card.willpower)
        assertEquals(false, card.inkable)
    }

    @Test
    fun `prefers normal image over large`() {
        val card = elsaDto.toDomain()!!
        assertEquals("https://cards.lorcast.io/.../normal.avif", card.imageUrl)
    }

    @Test
    fun `falls back to large image when normal is missing`() {
        val dto = elsaDto.copy(
            imageUris = ImageUrisDto(
                digital = DigitalImagesDto(
                    small = null,
                    normal = null,
                    large = "https://cards.lorcast.io/.../large.avif"
                )
            )
        )
        assertEquals("https://cards.lorcast.io/.../large.avif", dto.toDomain()?.imageUrl)
    }

    @Test
    fun `imageUrl is null when no images at all`() {
        val dto = elsaDto.copy(imageUris = null)
        assertNull(dto.toDomain()?.imageUrl)
    }

    @Test
    fun `maps all six ink colors`() {
        val inks = mapOf(
            "Amber" to InkColor.AMBER,
            "Amethyst" to InkColor.AMETHYST,
            "Emerald" to InkColor.EMERALD,
            "Ruby" to InkColor.RUBY,
            "Sapphire" to InkColor.SAPPHIRE,
            "Steel" to InkColor.STEEL
        )
        inks.forEach { (api, domain) ->
            val card = elsaDto.copy(ink = api).toDomain()!!
            assertEquals(listOf(domain), card.inkColors)
        }
    }

    @Test
    fun `maps Super_rare rarity correctly`() {
        val card = elsaDto.copy(rarity = "Super_rare").toDomain()!!
        assertEquals(Rarity.SUPER_RARE, card.rarity)
    }

    @Test
    fun `returns null when ink is null`() {
        val dto = elsaDto.copy(ink = null)
        assertNull(dto.toDomain())
    }

    @Test
    fun `returns null for unknown ink color`() {
        val dto = elsaDto.copy(ink = "Diamond")
        assertNull(dto.toDomain())
    }

    @Test
    fun `returns null for unknown rarity`() {
        val dto = elsaDto.copy(rarity = "Mythic")
        assertNull(dto.toDomain())
    }

    @Test
    fun `returns null when collectorNumber is non numeric`() {
        val dto = elsaDto.copy(collectorNumber = "207a")
        assertNull(dto.toDomain())
    }

    @Test
    fun `maps title as null when version is null`() {
        val dto = elsaDto.copy(version = null)
        val card = dto.toDomain()!!
        assertNull(card.title)
        assertTrue(card.displayName == "Elsa")
    }
}
