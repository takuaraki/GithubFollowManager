package example.com.githubfollowmanager.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import example.com.githubfollowmanager.BuildConfig
import example.com.githubfollowmanager.MainApplication
import example.com.githubfollowmanager.R
import example.com.githubfollowmanager.databinding.ActivityLoginBinding
import example.com.githubfollowmanager.interfaces.LoginInterface
import example.com.githubfollowmanager.viewmodels.LoginViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ログイン画面のアクティビティ
 */
class LoginActivity() : AppCompatActivity(), LoginInterface {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApplication).getComponent().plus().inject(this)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.callback = this

        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data;
            if (uri != null) {
                val code: String = uri.getQueryParameter("code");
                loginViewModel.getAccessToken(code)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({

                        }, {
                            Snackbar.make(
                                    findViewById(R.id.activity_login_viewGroup_root),
                                    R.string.login_failure,
                                    Snackbar.LENGTH_LONG)
                                    .show()
                        })
            }
        }
    }

    override fun onLoginButtonClicked(view: View) {
        val url = "https://github.com/login/oauth/authorize?client_id=" + BuildConfig.GITHUB_CLIENT_ID
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}