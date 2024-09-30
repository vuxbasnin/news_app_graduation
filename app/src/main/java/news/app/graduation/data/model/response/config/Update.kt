package news.app.graduation.data.model.response.config

data class Update(
    val contentShopUpdate: String,
    val contentUpdate: String,
    val is_forced_update: String,
    val remind_day: String,
    val titleShop: String,
    val titleUpdate: String,
    val update_url: String,
    val version: String,
    val versionShop: String
)