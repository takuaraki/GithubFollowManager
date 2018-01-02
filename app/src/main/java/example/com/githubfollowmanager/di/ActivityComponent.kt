package example.com.githubfollowmanager.di

import dagger.Subcomponent
import example.com.githubfollowmanager.activities.SearchActivity

/**
 * Component of Activity
 */
@Subcomponent
interface ActivityComponent {

    fun inject(activity: SearchActivity)

}