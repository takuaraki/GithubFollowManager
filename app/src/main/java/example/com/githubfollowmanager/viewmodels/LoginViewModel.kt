package example.com.githubfollowmanager.viewmodels

import example.com.githubfollowmanager.repositories.AuthRepository
import example.com.githubfollowmanager.valueobjects.AuthResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * ログイン画面の ViewModel
 */
class LoginViewModel @Inject constructor(
        private val authRepository: AuthRepository
) {
    fun getAccessToken(code: String): Observable<AuthResponse> = authRepository.getAccessToken(code)
}