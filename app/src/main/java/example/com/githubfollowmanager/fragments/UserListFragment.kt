package example.com.githubfollowmanager.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import example.com.githubfollowmanager.R
import example.com.githubfollowmanager.entities.User
import example.com.githubfollowmanager.extensions.load

/**
 * ユーザーのリストを表示するFragment
 */
class UserListFragment : Fragment() {

    companion object {
        private val ARG_USERS = "ARG_USERS"

        fun newInstance(users: List<User>): UserListFragment {
            val fragment = UserListFragment()
            val args = Bundle().apply {
                putParcelableArrayList(ARG_USERS, users.toCollection(ArrayList()))
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_user_list, container, false)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = UserListAdapter(activity, arguments.getParcelableArrayList(ARG_USERS))
        return view
    }
}

private class UserListAdapter(private val context: Context, private val users: List<User>) : RecyclerView.Adapter<UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_user_list, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder?, position: Int) {
        users[position].apply {
            holder?.userIconImageView?.load(context, this.avatarUrl)
            holder?.userNameTextView?.text = this.login
        }
    }

    override fun getItemCount(): Int = users.size

}

private class UserListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val userIconImageView = itemView?.findViewById<ImageView>(R.id.userIconImageView)
    val userNameTextView = itemView?.findViewById<TextView>(R.id.userNameTextView)
}