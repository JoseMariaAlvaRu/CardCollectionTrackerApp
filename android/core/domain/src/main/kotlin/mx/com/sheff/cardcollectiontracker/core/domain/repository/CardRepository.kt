package mx.com.sheff.cardcollectiontracker.core.domain.repository

import kotlinx.coroutines.flow.Flow
import mx.com.sheff.cardcollectiontracker.core.common.result.Result
import mx.com.sheff.cardcollectiontracker.core.domain.model.Card
import mx.com.sheff.cardcollectiontracker.core.domain.model.InkColor
import mx.com.sheff.cardcollectiontracker.core.domain.model.Rarity

/**
 * Repository contract for [Card] data.
 *
 * Implementations live in the data layer (e.g. inside :feature:collection
 * or a dedicated :data:card module) and combine local persistence (Room)
 * with remote sources (Lorcast API). The domain layer only depends on
 * this contract, never on concrete implementations.
 *
 * Observers (Flow-returning methods) emit the current state and continue
 * emitting on every change. Imperative methods (suspend functions)
 * trigger one-shot operations.
 */
interface CardRepository {

    /**
     * Observes all cards currently in the local catalog.
     *
     * Emits a new value whenever the catalog changes (e.g. after a sync).
     */
    fun observeAllCards(): Flow<Result<List<Card>>>

    /**
     * Observes cards filtered by set code.
     */
    fun observeCardsBySet(setCode: String): Flow<Result<List<Card>>>

    /**
     * Observes cards filtered by one or more ink colors.
     *
     * A card matches if any of its [InkColor]s is in [inkColors].
     */
    fun observeCardsByInkColors(inkColors: Set<InkColor>): Flow<Result<List<Card>>>

    /**
     * Observes cards filtered by one or more rarities.
     */
    fun observeCardsByRarities(rarities: Set<Rarity>): Flow<Result<List<Card>>>

    /**
     * Fetches a single card by id, or [Result.Failure] with
     * [mx.com.sheff.cardcollectiontracker.core.common.result.AppError.NotFound]
     * if not found.
     */
    suspend fun getCardById(id: String): Result<Card>

    /**
     * Triggers a full sync of the card catalog from the remote source.
     *
     * This is typically called once on first launch or when the user
     * pulls to refresh.
     */
    suspend fun syncCatalog(): Result<Unit>
}
