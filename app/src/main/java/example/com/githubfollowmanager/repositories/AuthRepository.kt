package example.com.githubfollowmanager.repositories

import example.com.githubfollowmanager.Constants
import example.com.githubfollowmanager.repositories.clients.AuthClient
import javax.inject.Inject

/**
 * Auth のデータを管理するクラス
 */
class AuthRepository @Inject constructor(
        private val authClient: AuthClient
) {
    /**
     * アクセストークンを取得します
     *
     * @param code 取得したコード
     * @return
     * @see <a href="https://developer.github.com/apps/building-oauth-apps/authorization-options-for-oauth-apps/">Authorization options for OAuth Apps</a>
     */
    fun getAccessToken(code: String) =
            authClient.getAccessToken(
                    clientId = Constants.CLIENT_ID,
                    clientSecret = Constants.CLIENT_SECRET,
                    code = code
            )
}