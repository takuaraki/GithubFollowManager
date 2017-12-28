package example.com.githubfollowmanager.di

import dagger.Subcomponent
import example.com.githubfollowmanager.MainActivity

/**
 * Component of Activity
 */
@Subcomponent
interface ActivityComponent {

    fun inject(activity: MainActivity)

}