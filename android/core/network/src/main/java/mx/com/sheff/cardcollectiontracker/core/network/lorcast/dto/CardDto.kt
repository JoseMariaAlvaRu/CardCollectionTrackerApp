package mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Raw card payload returned by the Lorcast v0 API.
 *
 * This DTO mirrors the JSON shape exactly. It is *not* meant to be used
 * outside the network layer. The mapper in
 * [mx.com.sheff.cardcollectiontracker.core.network.mapper] converts this
 * into the domain [mx.com.sheff.cardcollectiontracker.core.domain.model.Card].
 *
 * Reference: https://lorcast.com/docs/api/cards
 */
@Serializable
data class CardDto(
    val id: String,
    val name: String,
    val version: String? = null,
    val layout: String,
    @SerialName("released_at") val releasedAt: String,
    @SerialName("image_uris") val imageUris: ImageUrisDto? = null,
    val cost: Int,
    val inkwell: Boolean,
    val ink: String? = null,
    val type: List<String>,
    val classifications: List<String>? = null,
    val text: String? = null,
    @SerialName("move_cost") val moveCost: Int? = null,
    val strength: Int? = null,
    val willpower: Int? = null,
    val lore: Int? = null,
    val rarity: String,
    val illustrators: List<String>? = null,
    @SerialName("collector_number") val collectorNumber: String,
    val lang: String,
    @SerialName("flavor_text") val flavorText: String? = null,
    @SerialName("tcgplayer_id") val tcgPlayerId: Int? = null,
    val legalities: LegalitiesDto? = null,
    val set: CardSetRefDto,
    val prices: PricesDto? = null
)
