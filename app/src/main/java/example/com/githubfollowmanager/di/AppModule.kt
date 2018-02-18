package example.com.githubfollowmanager.di

import dagger.Module
import dagger.Provides
import example.com.githubfollowmanager.repositories.clients.AuthClient
import example.com.githubfollowmanager.repositories.clients.GithubClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Application内で共通して使われるクラスのProviderを管理するクラス
 */
@Module
class AppModule constructor() {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideGithubClient(okHttpClient: OkHttpClient) =
            Retrofit.Builder().client(okHttpClient)
                    .baseUrl("https://api.github.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GithubClient::class.java)

    @Singleton
    @Provides
    fun provideAuthClient(okHttpClient: OkHttpClient) =
            Retrofit.Builder().client(okHttpClient)
                    .baseUrl("https://github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AuthClient::class.java)
}