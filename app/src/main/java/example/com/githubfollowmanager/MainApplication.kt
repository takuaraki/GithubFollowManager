package example.com.githubfollowmanager

import android.app.Application
import example.com.githubfollowmanager.di.AppComponent
import example.com.githubfollowmanager.di.AppModule
import example.com.githubfollowmanager.di.DaggerAppComponent

/**
 * アプリケーションの継承クラス
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun getComponent(): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule())
                    .build()

}