package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.repository.DatabaseRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class CheckDatabaseIfEmptyUCTest {

    private val databaseRepository = mock<DatabaseRepository>()
    private val checkDatabaseIfEmptyUC = CheckDatabaseIfEmptyUC(databaseRepository)

    @Test
    fun `when the database return values then returns true`() {
        runBlocking {
            whenever(databaseRepository.hasLocalCities ()).thenReturn(true)

            val result = checkDatabaseIfEmptyUC()

            assertEquals(true, result)
        }
    }

    @Test
    fun `when the database doesNot return anything then returns false` () {
        runBlocking {
            whenever(databaseRepository.hasLocalCities()).thenReturn(false)

            val result = checkDatabaseIfEmptyUC()

            assertEquals(false, result)
        }
    }
}