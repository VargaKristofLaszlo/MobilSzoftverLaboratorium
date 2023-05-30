package com.example.animalfacts.feature.fact_list

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.domain.model.FactFilter
import com.example.animalfacts.network.FactService
import com.example.animalfacts.network.FactServiceMockImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.Q])
class NetworkTest {
    private lateinit var service: FactService
    private val fact1: Fact = Fact("fact1",1,"teszt1",false,0,"cat")
    private val fact2: Fact = Fact("fact2",1,"teszt2",false,0,"cat")

    @Before
    fun setup() {
        service = FactServiceMockImpl()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun should_load_facts_from_service() = runTest  {
        // Arrange
        val filter = FactFilter()

        // Act
        val response = service.getFacts(filter.animalType, filter.amount)

        // Assert
        Assert.assertEquals(2, response.size)
        Assert.assertEquals(fact1, response[0])
        Assert.assertEquals(fact2, response[1])
    }

    @Test
    @ExperimentalCoroutinesApi
    fun should_get_fact_by_id_from_service() = runTest  {
        // Arrange
        val factId = fact1._id

        // Act
        val response = service.getFactById(factId)

        // Assert
        Assert.assertEquals(fact1, response)
        Assert.assertNotEquals(fact2, response)
    }
}