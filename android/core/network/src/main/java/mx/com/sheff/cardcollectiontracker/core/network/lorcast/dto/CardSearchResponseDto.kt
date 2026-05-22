package mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto

import kotlinx.serialization.Serializable

/**
 * Envelope returned by /v0/cards/search and /v0/sets/:code/cards.
 *
 * The actual cards live inside the `results` array.
 */
@Serializable
data class CardSearchResponseDto(
    val results: List<CardDto>
)
