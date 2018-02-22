package example.com.githubfollowmanager.repositories.clients

import example.com.githubfollowmanager.valueobjects.AuthResponse
import io.reactivex.Observable
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * OAuth Authorizations API を呼び出す Client
 */
interface AuthClient {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    fun getAccessToken(
            @Query("client_id") clientId: String,
            @Query("client_secret") clientSecret: String,
            @Query("code") code: String
    ): Observable<AuthResponse>
}