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
     * @param page     頁目
     * @param perPage  件/頁
     * @return 指定されたユーザーがフォローしているユーザーのリスト
     */
    fun fetchFollowing(userName: String, page: Int, perPage: Int) = githubClient.getFollowing(userName, page, perPage)

    /**
     * 指定されたユーザーをフォローしているユーザーのリストを取得する
     *
     * @param userName ユーザー名
     * @param page     頁目
     * @param perPage  件/頁
     * @return 指定されたユーザーをフォローしているユーザーのリスト
     */
    fun fetchFollowers(userName: String, page: Int, perPage: Int) = githubClient.getFollowers(userName, page, perPage)

}
