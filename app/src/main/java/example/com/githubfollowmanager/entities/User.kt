package example.com.githubfollowmanager.entities

/**
 * ユーザーのエンティティ
 */
class User(
        val login: String,
        val id: Int,
        val avatarUrl: String,
        val avatarId: String,
        val url: String,
        val htmlUrl: String,
        val followersUrl: String,
        val followingUrl: String,
        val gistsUrl: String,
        val starredUrl: String,
        val subscriptionsUrl: String,
        val organizationsUrl: String,
        val reposUrl: String,
        val eventsUrl: String,
        val receivedEventsUrl: String,
        val type: String,
        val siteAdmin: Boolean
)