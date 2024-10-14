package news.app.graduation.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import news.app.graduation.data.local.dao.NewsUrlDao
import news.app.graduation.data.local.entity.NewsLocal

@Database(entities = [NewsLocal::class], version = 1, exportSchema = false)
abstract class AppLocalDatabase : RoomDatabase() {
    abstract fun newsUrlDao(): NewsUrlDao

    companion object {
        @Volatile
        private var INSTANCE: AppLocalDatabase? = null

        fun getDatabase(context: Context): AppLocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppLocalDatabase::class.java,
                    "app_database"
                ).addMigrations(MIGRATION).build()
                INSTANCE = instance
                instance
            }
        }

        // Migration từ version n lên version n+1
        private val MIGRATION = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}