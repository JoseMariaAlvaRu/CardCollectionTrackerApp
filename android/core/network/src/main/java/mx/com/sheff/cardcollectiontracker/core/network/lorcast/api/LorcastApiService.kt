package mx.com.sheff.cardcollectiontracker.core.network.lorcast.api

import mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto.CardDto
import mx.com.sheff.cardcollectiontracker.core.network.lorcast.dto.CardSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface for the Lorcast v0 API.
 *
 * Base URL is configured in the Hilt module:
 * https://api.lorcast.com/v0/
 *
 * Per Lorcast docs we should insert 50–100ms delay between requests,
 * but this is the caller's responsibility, not enforced here.
 *
 * Reference: https://lorcast.com/docs/api
 */
interface LorcastApiService {

    /**
     * Returns a single card by set code and collector number.
     *
     * Example: GET /v0/cards/1/207 → Elsa, Spirit of Winter.
     */
    @GET("cards/{set}/{number}")
    suspend fun getCard(
        @Path("set") setCode: String,
        @Path("number") collectorNumber: String
    ): CardDto

    /**
     * Full-text search across the card catalog.
     *
     * @param query Lorcast query syntax (e.g. "elsa set:1 rarity:enchanted").
     * @param unique "cards" (default) or "prints".
     */
    @GET("cards/search")
    suspend fun searchCards(
        @Query("q") query: String,
        @Query("unique") unique: String? = null
    ): CardSearchResponseDto
}
