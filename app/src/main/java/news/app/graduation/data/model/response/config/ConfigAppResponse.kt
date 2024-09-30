package news.app.graduation.data.model.response.config

data class ConfigAppResponse(
    val allow_share_fb: String,
    val allow_show_breaking_news: String,
    val desGift: String,
    val force_clear_cache: String,
    val isGift: String,
    val live_darkmode: String,
    val logo: String,
    val msg: Any,
    val objectAds: String,
    val result: List<Any>,
    val status: Int,
    val time_thumnail: String,
    val update: Update
)