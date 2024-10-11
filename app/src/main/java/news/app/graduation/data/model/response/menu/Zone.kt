package news.app.graduation.data.model.response.menu

import java.io.Serializable

data class Zone(
    val categories: ArrayList<Category> = arrayListOf(),
    val title: String? = null,
    val zone_url: String? = null,
    var isShowCategoryChild: Boolean = false,
): Serializable {

}