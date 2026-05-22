package mx.com.sheff.cardcollectiontracker.core.common.result

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultTest {

    @Test
    fun `Success carries its data`() {
        val result: Result<Int> = Result.Success(42)
        assertEquals(42, (result as Result.Success).data)
    }

    @Test
    fun `Failure carries its error`() {
        val error = AppError.Network("offline")
        val result: Result<Int> = Result.Failure(error)
        assertEquals(error, (result as Result.Failure).error)
    }

    @Test
    fun `getOrNull returns data on Success`() {
        val result: Result<String> = Result.Success("hello")
        assertEquals("hello", result.getOrNull())
    }

    @Test
    fun `getOrNull returns null on Failure`() {
        val result: Result<String> = Result.Failure(AppError.Unknown())
        assertNull(result.getOrNull())
    }

    @Test
    fun `getOrDefault returns data on Success`() {
        val result: Result<Int> = Result.Success(42)
        assertEquals(42, result.getOrDefault(0))
    }

    @Test
    fun `getOrDefault returns default on Failure`() {
        val result: Result<Int> = Result.Failure(AppError.Unknown())
        assertEquals(-1, result.getOrDefault(-1))
    }

    @Test
    fun `errorOrNull returns error on Failure`() {
        val error = AppError.Http(code = 404, message = "Not found")
        val result: Result<Int> = Result.Failure(error)
        assertEquals(error, result.errorOrNull())
    }

    @Test
    fun `errorOrNull returns null on Success`() {
        val result: Result<Int> = Result.Success(1)
        assertNull(result.errorOrNull())
    }

    @Test
    fun `isSuccess and isFailure are mutually exclusive`() {
        val success: Result<Int> = Result.Success(1)
        val failure: Result<Int> = Result.Failure(AppError.Unknown())

        assertTrue(success.isSuccess)
        assertFalse(success.isFailure)
        assertFalse(failure.isSuccess)
        assertTrue(failure.isFailure)
    }
}
