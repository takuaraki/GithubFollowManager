package example.com.githubfollowmanager.extensions

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

/*
 * ImageView関連のExtension集
 */

/**
 * URLから画像を読み込む
 *
 * @param context Context
 * @param url 画像のURL
 */
fun ImageView.load(context: Context, url: String) {
    Picasso.with(context)
            .load(url)
            .into(this)
}
