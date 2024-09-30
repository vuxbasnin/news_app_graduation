package news.app.graduation.core.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import news.app.graduation.BuildConfig
import news.app.graduation.core.common.Constants

object PreferenceHelper {
    const val SAVE_READ_POST = "SAVE_READ_POST"
    const val IS_QUICK_VIEW = "IS_QUICK_VIEW"

    var preferences: SharedPreferences? = null
    private val gson = Gson()
    fun init(context: Context) {
        preferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    fun getUserId() = get(Constants.KeyUserInfo.SUB_USER_INFO , "-1")
    fun getFullNameId() = get(Constants.KeyUserInfo.FULL_NAME_USER_INFO , "")
    fun getAvatarId() = get(Constants.KeyUserInfo.AVATAR_USER_INFO , "")


    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    fun SharedPreferences.clean() {
        val editor = this.edit()
        editor.clear()
        editor.apply()
    }


    fun setValue(key: String, value: Any?) {
        preferences?.apply {
            when (value) {
                is String -> edit { it.putString(key, value) }
                is Int -> edit { it.putInt(key, value) }
                is Boolean -> edit { it.putBoolean(key, value) }
                is Float -> edit { it.putFloat(key, value) }
                is Long -> edit { it.putLong(key, value) }
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }
    }

    inline fun <reified T : Any> get(key: String, defaultValue: T): T {
        preferences?.apply {
            return when (T::class) {
                String::class -> getString(key, defaultValue as String) as T
                Int::class -> getInt(key, defaultValue as Int) as T
                Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
                Float::class -> getFloat(key, defaultValue as Float) as T
                Long::class -> getLong(key, defaultValue as Long) as T
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }
        return defaultValue
    }

    fun remove(key: String) {
        preferences?.edit()?.remove(key)?.apply()
    }

    fun setIsQuickView(checked: Boolean) {
        setValue(IS_QUICK_VIEW, checked)
    }

    fun getIsHomeQuickView() = get(IS_QUICK_VIEW, false)
}