package example.com.githubfollowmanager.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import example.com.githubfollowmanager.BuildConfig
import example.com.githubfollowmanager.MainApplication
import example.com.githubfollowmanager.R
import example.com.githubfollowmanager.viewmodels.SearchViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    @Inject lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        (application as MainApplication).getComponent()
                .plus().inject(this)

        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data;
            if (uri != null) {
                val code: String = uri.getQueryParameter("code");
                searchViewModel.getAccessToken(code)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                        }, {
                        })
            }
        }

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            searchButton.isEnabled = false
            val userName: String = userNameInputEditText.text.toString()
            searchViewModel.search(userName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                        searchButton.isEnabled = true
                    }
                    .subscribe({
                        startActivity(SearchResultActivity.createIntent(
                                context = this,
                                query = userName,
                                mutualFollowers = it.mutualFollowingList,
                                followings = it.oneSidedFollowingList,
                                followers = it.oneSidedFollowerList
                        ))
                    }, {
                        Snackbar.make(
                                findViewById(android.R.id.content),
                                R.string.search_error,
                                Snackbar.LENGTH_SHORT).show();
                    })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.login -> {
                val url = "https://github.com/login/oauth/authorize?client_id=" + BuildConfig.GITHUB_CLIENT_ID
                val customTabsIntent = CustomTabsIntent.Builder().build()
                customTabsIntent.launchUrl(this, Uri.parse(url))
            }
        }
        return true
    }
}
