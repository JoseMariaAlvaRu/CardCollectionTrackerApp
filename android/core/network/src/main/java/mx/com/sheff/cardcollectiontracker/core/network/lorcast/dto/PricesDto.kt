package mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto

import kotlinx.serialization.Serializable

@Serializable
data class PricesDto(
    val usd: String? = null,
    @kotlinx.serialization.SerialName("usd_foil") val usdFoil: String? = null
)
