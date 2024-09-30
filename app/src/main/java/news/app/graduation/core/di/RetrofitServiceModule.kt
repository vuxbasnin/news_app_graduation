package news.app.graduation.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import news.app.graduation.BuildConfig
import news.app.graduation.core.common.Constants
import news.app.graduation.core.common.HeaderRetrofitEnum
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.network.service.NewsServiceRetrofit
import news.app.graduation.data.network.service.DemoService
import java.util.Collections
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitServiceModule {
    private fun getHttpClient(context: Context, headerRetrofitEnum: HeaderRetrofitEnum = HeaderRetrofitEnum.NONE): OkHttpClient {
        val deviceId = Utility.getDeviceId(context)
        return OkHttpClient.Builder().also { client ->
            client.retryOnConnectionFailure(true)
            client.addInterceptor{
                val newRequest = it.request().newBuilder().apply {
                    //check enum header in here to set header
                }.build()
                it.proceed(newRequest)
            }
            if (BuildConfig.DEBUG) {
                val loggingContent = HttpLoggingInterceptor()
                loggingContent.setLevel(HttpLoggingInterceptor.Level.BODY)
                val collector = ChuckerCollector(context)
                val logging = ChuckerInterceptor.Builder(context).alwaysReadResponseBody(true).collector(collector).build()
                client.interceptors().add(logging)
                client.interceptors().add(loggingContent)
            }
            client.connectTimeout(30, TimeUnit.SECONDS)
            client.readTimeout(30, TimeUnit.SECONDS)
            client.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        }.build()
    }

    //demo
    @Provides
    @Singleton
    @Named(Constants.Inject.DEMO)
    fun provideDemoRetrofit(gson: Gson, context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getHttpClient(context))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @Named(Constants.Inject.API)
    fun provideNewsRetrofit(gson: Gson, context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getHttpClient(context))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    //demo
    @Provides
    @Singleton
    fun provideAppService(@Named(Constants.Inject.API) retrofit: Retrofit): DemoService = retrofit.create(DemoService::class.java)

    @Provides
    @Singleton
    fun provideNewsService(@Named(Constants.Inject.API) retrofit: Retrofit): NewsServiceRetrofit = retrofit.create(
        NewsServiceRetrofit::class.java)
}