package example.com.githubfollowmanager.valueobjects

import example.com.githubfollowmanager.entities.User

/**
 * 検索結果の Value Object
 */
data class SearchResult(
        val oneSidedFollowingList: List<User>,
        val oneSidedFollowerList: List<User>,
        val mutualFollowingList: List<User>
)