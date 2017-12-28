package example.com.githubfollowmanager.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import example.com.githubfollowmanager.R
import example.com.githubfollowmanager.databinding.ActivitySearchResultBinding
import example.com.githubfollowmanager.entities.User
import example.com.githubfollowmanager.viewmodels.SearchResultViewModel
import java.util.*

/**
 * 検索結果表示画面
 */
class SearchResultActivity : AppCompatActivity() {

    private lateinit var query: String
    companion object {
        private val EXTRA_QUERY = "EXTRA_QUERY"

        fun createIntent(context: Context,
                         query: String,
                         mutualFollowers: List<User>,
                         followings: List<User>,
                         followers: List<User>) =
                Intent(context, SearchResultActivity::class.java)
                        .putExtra(EXTRA_QUERY, query)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        intent.apply {
            query = getStringExtra(EXTRA_QUERY)
        }

        val binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
                as ActivitySearchResultBinding
        binding.viewModel = SearchResultViewModel(query)
    }

}
