package com.example.animalfacts

import android.content.Context
import androidx.room.Room
import com.example.animalfacts.data.AnimalFactDatabase
import com.example.animalfacts.data.dao.FactDao
import com.example.animalfacts.data.repository.FactRepository
import com.example.animalfacts.data.repository.FactRepositoryImpl
import com.example.animalfacts.domain.usecases.Favourites.FavouriteFactUseCases
import com.example.animalfacts.domain.usecases.facts.FactUseCases
import com.example.animalfacts.network.FactService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object  AnimalFactsModule {
    @Provides
    fun providesFactUseCases(factService: FactService): FactUseCases {
        return FactUseCases(factService)
    }

    @Provides
    fun providesFavouriteFactUseCases(repository: FactRepository): FavouriteFactUseCases {
        return FavouriteFactUseCases(repository)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object AnimalSingletonModule {

    @Provides
    fun provideFactRepository(dao: FactDao): FactRepository {
        return FactRepositoryImpl(dao)
    }



    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AnimalFactDatabase {
        return Room.databaseBuilder(
            context,
            AnimalFactDatabase::class.java,
            "fact_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(factDatabase: AnimalFactDatabase): FactDao {
        return factDatabase.dao
    }
}