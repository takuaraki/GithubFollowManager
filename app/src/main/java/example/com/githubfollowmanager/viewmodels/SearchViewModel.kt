package example.com.githubfollowmanager.viewmodels

import example.com.githubfollowmanager.repositories.AuthRepository
import example.com.githubfollowmanager.repositories.UserRepository
import example.com.githubfollowmanager.valueobjects.AuthResponse
import example.com.githubfollowmanager.valueobjects.SearchResult
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * 検索画面の ViewModel
 */
class SearchViewModel @Inject constructor(
        private val userRepository: UserRepository,
        private val authRepository: AuthRepository
) {

    fun search(userName: String): Observable<SearchResult> {
        return Observable.zip(
                userRepository.fetchFollowing(userName),
                userRepository.fetchFollowers(userName),
                BiFunction
                { followings, followers ->
                    SearchResult(
                            oneSidedFollowingList = followings - followers,
                            oneSidedFollowerList = followers - followings,
                            mutualFollowingList = followings.intersect(followers).toList())
                }
        )
    }

    fun getAccessToken(code: String): Observable<AuthResponse> = authRepository.getAccessToken(code)
}