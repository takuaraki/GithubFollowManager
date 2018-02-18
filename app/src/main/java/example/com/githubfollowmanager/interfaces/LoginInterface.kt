package example.com.githubfollowmanager.interfaces

import android.view.View

/**
 * ログイン画面のインターフェース
 */
interface LoginInterface {
    /**
     * 「ログイン」ボタンがタップされた場合のコールバック
     */
    fun onLoginButtonClicked(view: View)
}