package mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageUrisDto(
    val digital: DigitalImagesDto
)

@Serializable
data class DigitalImagesDto(
    val small: String? = null,
    val normal: String? = null,
    val large: String? = null
)
