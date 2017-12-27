package example.com.githubfollowmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import example.com.githubfollowmanager.repositories.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    @Inject lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloTextView.text = "Hello Kotlin!!"

        (application as MainApplication).getComponent()
                .plus().inject(this)

        userRepository.fetchFollowers("takuaraki")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, it.toString())
                })
    }
}
