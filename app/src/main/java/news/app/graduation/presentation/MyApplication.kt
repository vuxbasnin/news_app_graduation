package news.app.graduation.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import news.app.graduation.BuildConfig
import news.app.graduation.core.utils.LogsUtil

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //config, init sdk in layer application
        if (BuildConfig.DEBUG) {
            Timber.plant(LogsUtil())
        }
    }
}