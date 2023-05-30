package com.example.animalfacts.feature.fact_list

import android.os.Build.VERSION_CODES.Q
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animalfacts.data.AnimalFactDatabase
import com.example.animalfacts.data.dao.FactDao
import com.example.animalfacts.data.repository.FactRepositoryImpl
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.domain.model.asFactEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Q])
class RepositoryTest {
    private lateinit var repository: FactRepositoryImpl
    private lateinit var dao: FactDao
    private val fact1: Fact = Fact("fact1",1,"teszt1",false,0,"cat")
    private val fact2: Fact = Fact("fact2",1,"teszt2",false,0,"cat")

    @Before
    fun setupDatabase() {
        val database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AnimalFactDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.dao

        repository = FactRepositoryImpl(dao)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun should_insert_fact_to_database() = runTest {
        // Arrange
        val factentity = fact1.asFactEntity()
        var facts = repository.getAllFacts().first()
        val sizeBefore = facts.size

        // Act
        repository.insertFact(factentity)

        // Assert
        facts = repository.getAllFacts().first()
        val sizeAfter = facts.size
        Assert.assertNotEquals(sizeBefore,sizeAfter)
        Assert.assertEquals(fact1.asFactEntity(),facts.get(0))
    }

    @Test
    @ExperimentalCoroutinesApi
    fun should_load_facts_from_database() = runTest  {
        // Arrange
        val list: List<Fact> = mutableListOf(fact1)
        dao.insertFact(fact1.asFactEntity())

        // Act
        val result = repository.getAllFacts().first()

        // Assert
        Assert.assertEquals(result.size, list.size)
        Assert.assertEquals(result.contains(fact1.asFactEntity()), list.contains(fact1))
    }

    @Test
    @ExperimentalCoroutinesApi
    fun should_load_facts_by_id_from_database() = runTest  {
        // Arrange
        dao.insertFact(fact1.asFactEntity())
        dao.insertFact(fact2.asFactEntity())

        // Act
        val result = repository.getFactById(fact1._id).first()

        // Assert
        Assert.assertNotNull(result)
        Assert.assertNotEquals(fact2.asFactEntity(), result)
        Assert.assertEquals(fact1.asFactEntity(), result)

    }

    @Test
    @ExperimentalCoroutinesApi
    fun should_delete_fact_by_id_from_database() = runTest  {
        // Arrange
        dao.insertFact(fact1.asFactEntity())
        dao.insertFact(fact2.asFactEntity())
        val sizeBefore = dao.getAllFacts().first().size

        // Act
        repository.deleteFact(fact1._id)
        val sizeAfter = dao.getAllFacts().first().size
        val result = dao.getFactById(fact1._id).first()

        // Assert
        Assert.assertNotEquals(sizeBefore, sizeAfter)
        Assert.assertNull(result)

    }
}