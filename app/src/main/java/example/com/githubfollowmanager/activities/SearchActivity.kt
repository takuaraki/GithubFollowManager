package example.com.githubfollowmanager.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
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

                    }, {
                        Snackbar.make(
                                findViewById(android.R.id.content),
                                R.string.search_error,
                                Snackbar.LENGTH_SHORT).show();
                    })
        }
    }
}
