package example.com.githubfollowmanager.repositories

import example.com.githubfollowmanager.repositories.clients.GithubClient
import javax.inject.Inject

/**
 * Githubユーザーのデータを管理するクラス
 */
class UserRepository @Inject constructor(
        private val githubClient: GithubClient
) {

    /**
     * 指定されたユーザーがフォローしているユーザーのリストを取得する
     *
     * @param userName ユーザー名
     * @return 指定されたユーザーがフォローしているユーザーのリスト
     */
    fun fetchFollowing(userName: String) = githubClient.getFollowing(userName)

    /**
     * 指定されたユーザーをフォローしているユーザーのリストを取得する
     *
     * @param userName ユーザー名
     * @return 指定されたユーザーをフォローしているユーザーのリスト
     */
    fun fetchFollowers(userName: String) = githubClient.getFollowers(userName)

}
