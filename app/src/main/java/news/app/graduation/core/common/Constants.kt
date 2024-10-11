package news.app.graduation.core.common

object Constants {
    object Inject {
        const val DEMO = "DEMO"
        const val API = "API"
    }

    object Preference {
        const val PREF_PROFILE = "PROFILE"
        const val PREF_IS_HOME_QUICK_VIEW = "IS_HOME_QUICK_VIEW"
        const val PREF_CONFIG_APP = "pref_config_app"
        const val PREF_FONT_LEVEL = "pref_font_level"
        const val LOGIN_SUCCESS = "login"
        const val PREF_IS_SHOW_TOOL_TIP = "pref_show_tool_tip"
        const val PREF_AUTH_MODEL = "AUTH_MODEL"
        const val PREF_DARK_MODE_APP = "PREF_DARK_MODE_APP"
        const val PREF_DARK_MODE_SYSTEM = "IS_DARK_MODE_SYSTEM"
        const val PREF_DARK_MODE_IS_DISMISS_PROMPT = "PREF_DARK_MODE_IS_DISMISS_PROMPT"
        const val USER_AGENT_WEBVIEW = "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Mobile Safari/537.36 BgtAppUserAgent"
    }

    object KeyUserInfo {
        //info user when login
        const val ACCESS_TOKEN = "access_token"
        const val ID_TOKEN = "id_token"
        const val SUB_USER_INFO = "sub_user_info"
        const val FULL_NAME_USER_INFO = "name_user_info"
        const val AVATAR_USER_INFO = "avatar_user_info"
        const val LOGIN_SUCCESS = "login_success"
    }

    object TabMain {
        const val TAB_0 = 0
        const val TAB_1 = 1
        const val TAB_2 = 2
        const val TAB_3 = 3
        const val TAB_4 = 4
    }

    object Config {
        const val CONFIG_APP_RESPONSE = "config_app_response"
    }

    const val DataZoneMenu = "{\n" +
            "  \"zones\": [\n" +
            "    {\n" +
            "      \"title\": \"Học Đường\",\n" +
            "      \"zone_url\": \"hoc-duong.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Nhân vật\",\n" +
            "          \"rss_url\": \"rss/hoc-duong/nhan-vat.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Du học\",\n" +
            "          \"rss_url\": \"rss/hoc-duong/du-hoc.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Bản tin 46'\",\n" +
            "          \"rss_url\": \"rss/hoc-duong/ban-tin-46.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Beauty & Fashion\",\n" +
            "      \"zone_url\": \"beauty-fashion.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Star Style\",\n" +
            "          \"rss_url\": \"rss/beauty-fashion/star-style.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Làm Đẹp\",\n" +
            "          \"rss_url\": \"rss/beauty-fashion/lam-dep.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Thời Trang\",\n" +
            "          \"rss_url\": \"rss/beauty-fashion/thoi-trang.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Cine\",\n" +
            "      \"zone_url\": \"cine.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Phim Chiếu Rạp\",\n" +
            "          \"rss_url\": \"rss/cine/phim-chieu-rap.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Phim Việt Nam\",\n" +
            "          \"rss_url\": \"rss/cine/phim-viet-nam.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Series Truyền Hình\",\n" +
            "          \"rss_url\": \"rss/cine/series-truyen-hinh.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Hoa Ngữ - Hàn Quốc\",\n" +
            "          \"rss_url\": \"rss/cine/hoa-ngu-han-quoc.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Musik\",\n" +
            "      \"zone_url\": \"musik.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Âu Mỹ\",\n" +
            "          \"rss_url\": \"rss/musik/au-my.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Châu Á\",\n" +
            "          \"rss_url\": \"rss/musik/chau-a.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Việt Nam\",\n" +
            "          \"rss_url\": \"rss/musik/viet-nam.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Thế Giới Đó Đây\",\n" +
            "      \"zone_url\": \"the-gioi-do-day.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Chùm ảnh\",\n" +
            "          \"rss_url\": \"rss/the-gioi-do-day/chum-anh.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Khám phá\",\n" +
            "          \"rss_url\": \"rss/the-gioi-do-day/kham-pha.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Dị\",\n" +
            "          \"rss_url\": \"rss/the-gioi-do-day/di.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Đời sống\",\n" +
            "      \"zone_url\": \"doi-song.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Mommy-eZ\",\n" +
            "          \"rss_url\": \"rss/doi-song/mommy-ez.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"HOUSE n HOME\",\n" +
            "          \"rss_url\": \"rss/doi-song/house-n-home.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Nhân vật\",\n" +
            "          \"rss_url\": \"rss/doi-song/nhan-vat.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Tek-life\",\n" +
            "      \"zone_url\": \"tek-life.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Metaverse\",\n" +
            "          \"rss_url\": \"rss/tek-life/metaverse.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"HOW-TO\",\n" +
            "          \"rss_url\": \"rss/tek-life/how-to.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Wow!\",\n" +
            "          \"rss_url\": \"rss/tek-life/wow.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"2-Mall\",\n" +
            "          \"rss_url\": \"rss/tek-life/2-mall.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Money-Z\",\n" +
            "      \"zone_url\": \"money-z.rss\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Xem Mua Luôn\",\n" +
            "      \"zone_url\": \"xem-mua-luon.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Mommy Mua Đi\",\n" +
            "          \"rss_url\": \"rss/xem-mua-luon/mommy-mua-di.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Thời trang\",\n" +
            "          \"rss_url\": \"rss/xem-mua-luon/thoi-trang.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Đẹp\",\n" +
            "          \"rss_url\": \"rss/xem-mua-luon/dep.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Ăn - Quẩy - Đi\",\n" +
            "      \"zone_url\": \"an-quay-di.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Ăn\",\n" +
            "          \"rss_url\": \"rss/an-quay-di/an.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Quẩy\",\n" +
            "          \"rss_url\": \"rss/an-quay-di/quay.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Đi\",\n" +
            "          \"rss_url\": \"rss/an-quay-di/di.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Xã hội\",\n" +
            "      \"zone_url\": \"xa-hoi.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Pháp luật\",\n" +
            "          \"rss_url\": \"rss/xa-hoi/phap-luat.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Nóng trên mạng\",\n" +
            "          \"rss_url\": \"rss/xa-hoi/nong-tren-mang.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Sống xanh\",\n" +
            "          \"rss_url\": \"rss/xa-hoi/song-xanh.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Sức khỏe\",\n" +
            "      \"zone_url\": \"suc-khoe.rss\",\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Tin tức\",\n" +
            "          \"rss_url\": \"rss/suc-khoe/tin-tuc.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Khỏe đẹp\",\n" +
            "          \"rss_url\": \"rss/suc-khoe/khoe-dep.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Giới tính\",\n" +
            "          \"rss_url\": \"rss/suc-khoe/gioi-tinh.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Các bệnh\",\n" +
            "          \"rss_url\": \"rss/suc-khoe/cac-benh.rss\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Dinh dưỡng\",\n" +
            "          \"rss_url\": \"rss/suc-khoe/dinh-duong.rss\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Xem Ăn Chơi\",\n" +
            "      \"zone_url\": \"xem-an-choi.rss\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}