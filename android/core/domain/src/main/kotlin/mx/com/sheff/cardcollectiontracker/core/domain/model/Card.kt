package mx.com.sheff.cardcollectiontracker.core.domain.model

/**
 * A Lorcana card.
 *
 * This is the central domain model. All other features and screens
 * work with this type. Persistence (Room entities) and remote
 * representations (API DTOs) map to/from this class via dedicated
 * mappers in their respective layers.
 *
 * @property id Unique identifier across all sets (e.g. "tfc-001").
 * @property name Card name as printed (e.g. "Mickey Mouse").
 * @property title Optional subtitle/version (e.g. "Brave Little Tailor").
 * @property setCode Reference to the [CardSet] this card belongs to.
 * @property collectorNumber Number within the set (e.g. 1 of 204).
 * @property cost Ink cost to play the card (0..n).
 * @property inkColors One or two ink colors (dual-ink cards have two).
 * @property rarity The card's rarity tier.
 * @property type The card type (Character, Action, Item, Location).
 * @property lore Lore value gained when questing (only for Characters).
 * @property strength Strength stat (only for Characters).
 * @property willpower Willpower stat (only for Characters).
 * @property inkable Whether the card can be placed face-down as ink.
 * @property bodyText Rules text printed on the card.
 * @property flavorText Italicized flavor text below the rules.
 * @property imageUrl URL of the card's official artwork.
 */
data class Card(
    val id: String,
    val name: String,
    val title: String? = null,
    val setCode: String,
    val collectorNumber: Int,
    val cost: Int,
    val inkColors: List<InkColor>,
    val rarity: Rarity,
    val type: CardType,
    val lore: Int? = null,
    val strength: Int? = null,
    val willpower: Int? = null,
    val inkable: Boolean = true,
    val bodyText: String? = null,
    val flavorText: String? = null,
    val imageUrl: String? = null
) {
    /** True if the card has two ink colors. */
    val isDualInk: Boolean get() = inkColors.size == 2

    /** The card's full display name including the title. */
    val displayName: String get() = if (title != null) "$name — $title" else name
}
