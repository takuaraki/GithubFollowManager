package example.com.githubfollowmanager.valueobjects;

import com.google.gson.annotations.SerializedName

/**
 * Auth のレスポンス
 */
data class AuthResponse(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("token_type") val tokenType: String
) {

}