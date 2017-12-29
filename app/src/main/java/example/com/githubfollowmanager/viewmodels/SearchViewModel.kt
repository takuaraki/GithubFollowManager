package example.com.githubfollowmanager.viewmodels

import example.com.githubfollowmanager.entities.User
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
                fetchAllFollowings(mutableListOf(), userName, 1, 100),
                fetchAllFollowers(mutableListOf(), userName, 1, 100),
                BiFunction
                { followings, followers ->
                    SearchResult(
                            oneSidedFollowingList = followings - followers,
                            oneSidedFollowerList = followers - followings,
                            mutualFollowingList = followings.intersect(followers).toList())
                }
        )
    }

    /**
     * フォロー一覧を全件取得します
     *
     * @param fetchedList 取得データ
     * @param userName    ユーザーネーム
     * @param page        頁目
     * @param perPage     件/頁
     */
    private fun fetchAllFollowings(fetchedList: MutableList<User>, userName: String, page: Int, perPage: Int): Observable<List<User>> {
        return userRepository.fetchFollowing(userName, page, perPage)
                .flatMap {
                    fetchedList.addAll(it)
                    if (it.size == perPage) {
                        fetchAllFollowings(fetchedList, userName, page = page + 1, perPage = perPage)
                    } else {
                        Observable.just(fetchedList)
                    }
                }
    }

    /**
     * フォロワー一覧を全件取得します
     *
     * @param fetchedList 取得データ
     * @param userName    ユーザーネーム
     * @param page        頁目
     * @param perPage     件/頁
     */
    private fun fetchAllFollowers(fetchedList: MutableList<User>, userName: String, page: Int, perPage: Int): Observable<List<User>> {
        return userRepository.fetchFollowers(userName, page, perPage)
                .flatMap {
                    fetchedList.addAll(it)
                    if (it.size == perPage) {
                        fetchAllFollowers(fetchedList, userName, page = page + 1, perPage = perPage)
                    } else {
                        Observable.just(fetchedList)
                    }
                }
    }
}