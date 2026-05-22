package mx.com.sheff.cardcollectiontracker.core.domain.model

/**
 * The four card types in Lorcana.
 *
 *  - CHARACTER (Glimmer): the main type, represents Disney characters
 *    summoned to quest, challenge, and gain lore.
 *  - ACTION: one-time use card that triggers an effect when played.
 *  - ITEM: persistent object with ongoing effects.
 *  - LOCATION: persistent place where characters can be assigned to
 *    generate lore or trigger effects.
 */
enum class CardType {
    CHARACTER,
    ACTION,
    ITEM,
    LOCATION
}
