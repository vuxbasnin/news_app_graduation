package news.app.graduation.core.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import news.app.graduation.presentation.MyApplication
import javax.inject.Singleton

/**
 * Here are the dependencies which will be injected by hilt
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //Gson for converting JSON String to Java Objects
    @Provides
    fun providerGson(): Gson = GsonBuilder().setLenient().create()

    //Context
    @Provides
    @Singleton
    fun providerContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun providerApp(): MyApplication {
        return MyApplication()
    }

    @Provides
    fun providerDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
}