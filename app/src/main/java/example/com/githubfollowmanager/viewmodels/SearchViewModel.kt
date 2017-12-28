package example.com.githubfollowmanager.viewmodels

import example.com.githubfollowmanager.repositories.UserRepository
import example.com.githubfollowmanager.valueobjects.SearchResult
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * 検索画面の ViewModel
 */
class SearchViewModel @Inject constructor(
        private val userRepository: UserRepository
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
}