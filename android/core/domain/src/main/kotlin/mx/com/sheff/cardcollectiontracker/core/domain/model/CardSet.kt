package mx.com.sheff.cardcollectiontracker.core.domain.model

/**
 * A Lorcana card set (also called expansion or chapter).
 *
 * Each set has a unique code (e.g. "TFC" for The First Chapter)
 * and a total card count used to compute collection completion.
 */
data class CardSet(
    val code: String,
    val name: String,
    val number: Int,
    val totalCards: Int,
    val releaseDate: String? = null
)
