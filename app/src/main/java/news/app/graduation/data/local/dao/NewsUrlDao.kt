package news.app.graduation.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import news.app.graduation.data.local.entity.NewsLocal

@Dao
interface NewsUrlDao {
    @Query("SELECT * FROM newsurllocal")
    suspend fun getAll(): MutableList<NewsLocal>

    @Query("SELECT * FROM newsurllocal WHERE is_save=1")
    suspend fun getAllNewsSave(): MutableList<NewsLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsUrl(newsLocal: NewsLocal): Long

    @Query("UPDATE newsurllocal SET is_save=:isSave")
    suspend fun updateNewsUrl(isSave: Boolean)

    @Query("DELETE FROM newsurllocal WHERE news_url=:newsUrl")
    suspend fun deleteNewsUrl(newsUrl: String): Int
}