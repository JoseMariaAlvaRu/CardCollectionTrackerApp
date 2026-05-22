package mx.com.sheff.cardcollectiontracker.core.domain.model

/**
 * Card rarity in Lorcana, ordered from most common to rarest.
 *
 * Epic and Iconic were introduced in the Fabled set (August 2025).
 */
enum class Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    SUPER_RARE,
    LEGENDARY,
    EPIC,
    ENCHANTED,
    ICONIC,
    PROMO
}
