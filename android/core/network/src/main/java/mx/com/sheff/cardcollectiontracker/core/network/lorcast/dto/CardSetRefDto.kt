package mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto

import kotlinx.serialization.Serializable

@Serializable
data class CardSetRefDto(
    val id: String,
    val code: String,
    val name: String
)
