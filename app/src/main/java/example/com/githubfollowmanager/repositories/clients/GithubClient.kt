package example.com.githubfollowmanager.repositories.clients

import example.com.githubfollowmanager.entities.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * GithubAPIを呼び出すClient
 */
interface GithubClient {

    /**
     * 指定されたユーザーがフォローしているユーザーのリストを取得する
     *
     * @param userName ユーザー名
     * @return 指定されたユーザーがフォローしているユーザーのリスト
     */
    @GET("/users/{userName}/following")
    fun getFollowing(@Path("userName") userName: String): Observable<List<User>>

    /**
     * 指定されたユーザーをフォローしているユーザーのリストを取得する
     *
     * @param userName ユーザー名
     * @return 指定されたユーザーをフォローしているユーザーのリスト
     */
    @GET("/users/{userName}/followers")
    fun getFollowers(@Path("userName") userName: String): Observable<List<User>>

}
