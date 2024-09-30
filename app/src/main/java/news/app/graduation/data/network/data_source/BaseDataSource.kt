package news.app.graduation.data.network.data_source

import android.content.Context
import news.app.graduation.core.utils.PreferenceHelper
import news.app.graduation.core.utils.Utility

abstract class BaseDataSource(private val mContext: Context) {
    /**
     * Map data sang resource
     */
    /**
     * Try catch trong này
     * map data sang Resource
     */
    protected var deviceId: String = Utility.getDeviceId(mContext)
    protected val userId: String
        get() = PreferenceHelper.getUserId()
}