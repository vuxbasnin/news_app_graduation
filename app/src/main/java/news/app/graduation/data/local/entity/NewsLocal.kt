package news.app.graduation.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "newsurllocal",
    indices = [Index(value = ["news_url"], unique = true)]
)        //unique -> không được có 2 url giống nhau
data class NewsLocal(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "news_url") val newsUrl: String? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "imageUrl") val imageUrl: String? = null,
    @ColumnInfo(name = "is_save") val isSave: Boolean = false
)
