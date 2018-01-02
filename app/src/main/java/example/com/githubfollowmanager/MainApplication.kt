package example.com.githubfollowmanager

import android.app.Application
import example.com.githubfollowmanager.di.AppComponent
import example.com.githubfollowmanager.di.AppModule
import example.com.githubfollowmanager.di.DaggerAppComponent

/**
 * アプリケーションの継承クラス
 */
class MainApplication : Application() {

    private lateinit var appComponent: AppComponent
    fun getComponent() = appComponent

    override fun onCreate() {
        super.onCreate()

        appComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule())
                        .build()
    }

}