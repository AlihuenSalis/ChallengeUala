package ali.projects.challengeuala.data.database

import ali.projects.challengeuala.data.database.dao.CityDao
import ali.projects.challengeuala.data.database.entities.CityEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
}