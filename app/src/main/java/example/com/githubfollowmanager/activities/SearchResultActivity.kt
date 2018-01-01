package example.com.githubfollowmanager.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import example.com.githubfollowmanager.R
import example.com.githubfollowmanager.databinding.ActivitySearchResultBinding
import example.com.githubfollowmanager.entities.User
import example.com.githubfollowmanager.fragments.UserListFragment
import example.com.githubfollowmanager.viewmodels.SearchResultViewModel
import java.util.*

/**
 * 検索結果表示画面
 */
class SearchResultActivity : AppCompatActivity() {

    private lateinit var query: String
    private lateinit var mutualFollowers: List<User>
    private lateinit var followings: List<User>
    private lateinit var followers: List<User>

    companion object {
        private val EXTRA_QUERY = "EXTRA_QUERY"
        private val EXTRA_MUTUAL_FOLLOWERS = "EXTRA_MUTUAL_FOLLOWERS"
        private val EXTRA_FOLLOWINGS = "EXTRA_FOLLOWINGS"
        private val EXTRA_FOLLOWERS = "EXTRA_FOLLOWERS"

        fun createIntent(context: Context,
                         query: String,
                         mutualFollowers: List<User>,
                         followings: List<User>,
                         followers: List<User>) =
                Intent(context, SearchResultActivity::class.java)
                        .putExtra(EXTRA_QUERY, query)
                        .putParcelableArrayListExtra(EXTRA_MUTUAL_FOLLOWERS, mutualFollowers.toCollection(ArrayList()))
                        .putParcelableArrayListExtra(EXTRA_FOLLOWINGS, followings.toCollection(ArrayList()))
                        .putParcelableArrayListExtra(EXTRA_FOLLOWERS, followers.toCollection(ArrayList()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        intent.apply {
            query = getStringExtra(EXTRA_QUERY)
            mutualFollowers = getParcelableArrayListExtra(EXTRA_MUTUAL_FOLLOWERS)
            followings = getParcelableArrayListExtra(EXTRA_FOLLOWINGS)
            followers = getParcelableArrayListExtra(EXTRA_FOLLOWERS)
        }

        val binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
                as ActivitySearchResultBinding
        binding.apply {
            viewModel = SearchResultViewModel(query)
            viewPager.adapter = SearchResultPagerAdapter(
                    mutualFollowers, followings, followers, this@SearchResultActivity, supportFragmentManager
            )
            tabLayout.setupWithViewPager(viewPager)
        }

        binding.viewModel = SearchResultViewModel(query)

        binding.viewPager.adapter = SearchResultPagerAdapter(
                mutualFollowers, followings, followers, this, supportFragmentManager
        )
    }

}

private class SearchResultPagerAdapter(
        private val mutualFollowers: List<User>, private val followings: List<User>, private val followers: List<User>,
        private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    enum class Tab(val titleStringRes: Int) {
        FOLLOW(R.string.follow),
        FOLLOWER(R.string.follower),
        MUTUAL(R.string.mutual);

        companion object {
            fun convert(position: Int): Tab? = Tab.values().find {
                it.ordinal == position
            }
        }
    }

    override fun getItem(position: Int): Fragment? = when (Tab.convert(position)) {
        Tab.FOLLOW -> UserListFragment.newInstance(followings)
        Tab.FOLLOWER -> UserListFragment.newInstance(followers)
        Tab.MUTUAL -> UserListFragment.newInstance(mutualFollowers)
        else -> null
    }

    override fun getCount(): Int = Tab.values().size

    override fun getPageTitle(position: Int): CharSequence {
        val title = Tab.convert(position)?.titleStringRes?.let {
            context.getString(it)
        }
        return title ?: ""
    }

}
