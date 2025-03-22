package ali.projects.challengeuala.di

import ali.projects.challengeuala.data.database.dao.CityDao
import ali.projects.challengeuala.data.implementation.DatabaseRepositoryImp
import ali.projects.challengeuala.data.implementation.NetworkRepositoryImp
import ali.projects.challengeuala.data.network.ApiClient
import ali.projects.challengeuala.domain.repository.DatabaseRepository
import ali.projects.challengeuala.domain.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(apiClient: ApiClient): NetworkRepository {
        return NetworkRepositoryImp(apiClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(cityDao: CityDao): DatabaseRepository {
        return DatabaseRepositoryImp(cityDao)
    }
}