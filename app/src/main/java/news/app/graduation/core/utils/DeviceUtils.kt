package news.app.graduation.core.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

class DeviceUtil {
    companion object {
        private var instance: DeviceUtil? = null
        private var displayMetrics: DisplayMetrics? = null

        @JvmStatic
        fun getInstance(): DeviceUtil {
            if (instance == null) {
                instance = DeviceUtil()
            }
            return instance ?: DeviceUtil()
        }

        @JvmStatic
        fun getDisplayMetrics(context: Context?): DisplayMetrics? {
            if (displayMetrics == null) {
                displayMetrics = DisplayMetrics()
                (context as? Activity)?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            }
            return displayMetrics
        }
    }

    fun initSizeScreen(activity: Activity) {
        displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    }

    fun getWidthScreen(context: Context?): Int {
        return if (context != null || getDisplayMetrics(context) != null) {
            getDisplayMetrics(context)?.widthPixels ?: 0
        } else 0
    }

    fun getHeightScreen(context: Context?): Int {
        return if (context != null || getDisplayMetrics(context) != null) {
            getDisplayMetrics(context)?.heightPixels ?: 0
        } else 0
    }

    fun getScreenWidthInDp(context: Context): Float {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = context.resources.displayMetrics
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidthPixels = displayMetrics.widthPixels
        return screenWidthPixels / displayMetrics.density
    }
}