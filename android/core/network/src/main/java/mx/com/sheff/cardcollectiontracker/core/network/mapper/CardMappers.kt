package mx.com.sheff.cardcollectiontracker.core.network.mapper

import mx.com.sheff.cardcollectiontracker.core.domain.model.Card
import mx.com.sheff.cardcollectiontracker.core.domain.model.CardType
import mx.com.sheff.cardcollectiontracker.core.domain.model.InkColor
import mx.com.sheff.cardcollectiontracker.core.domain.model.Rarity
import mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto.CardDto

/**
 * Maps a Lorcast [CardDto] (raw API payload) to a domain [Card].
 *
 * Returns null if the DTO contains values that cannot be mapped to a
 * valid domain card (e.g. an unknown card type, or a missing ink color).
 * Callers should filter these out rather than crash.
 */
fun CardDto.toDomain(): Card? {
    val ink = ink?.toInkColorOrNull() ?: return null
    val cardType = type.firstOrNull()?.toCardTypeOrNull() ?: return null
    val mappedRarity = rarity.toRarityOrNull() ?: return null
    val collectorNumberInt = collectorNumber.toIntOrNull() ?: return null

    return Card(
        id = id,
        name = name,
        title = version,
        setCode = set.code,
        collectorNumber = collectorNumberInt,
        cost = cost,
        inkColors = listOf(ink),
        rarity = mappedRarity,
        type = cardType,
        lore = lore,
        strength = strength,
        willpower = willpower,
        inkable = inkwell,
        bodyText = text,
        flavorText = flavorText,
        imageUrl = imageUris?.digital?.normal ?: imageUris?.digital?.large
    )
}

/**
 * Maps Lorcast's ink string (e.g. "Amber") to the domain [InkColor].
 * Returns null for unrecognized values.
 */
private fun String.toInkColorOrNull(): InkColor? = when (this) {
    "Amber" -> InkColor.AMBER
    "Amethyst" -> InkColor.AMETHYST
    "Emerald" -> InkColor.EMERALD
    "Ruby" -> InkColor.RUBY
    "Sapphire" -> InkColor.SAPPHIRE
    "Steel" -> InkColor.STEEL
    else -> null
}

/**
 * Maps Lorcast's card type string to the domain [CardType].
 * Returns null for unrecognized values.
 */
private fun String.toCardTypeOrNull(): CardType? = when (this) {
    "Character" -> CardType.CHARACTER
    "Action" -> CardType.ACTION
    "Item" -> CardType.ITEM
    "Location" -> CardType.LOCATION
    else -> null
}

/**
 * Maps Lorcast's rarity string to the domain [Rarity].
 * Returns null for unrecognized values.
 */
private fun String.toRarityOrNull(): Rarity? = when (this) {
    "Common" -> Rarity.COMMON
    "Uncommon" -> Rarity.UNCOMMON
    "Rare" -> Rarity.RARE
    "Super_rare" -> Rarity.SUPER_RARE
    "Legendary" -> Rarity.LEGENDARY
    "Enchanted" -> Rarity.ENCHANTED
    "Epic" -> Rarity.EPIC
    "Iconic" -> Rarity.ICONIC
    "Promo" -> Rarity.PROMO
    else -> null
}
