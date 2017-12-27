package example.com.githubfollowmanager.di

import dagger.Component
import javax.inject.Singleton

/**
 * Component of Application
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun plus(): ActivityComponent

}