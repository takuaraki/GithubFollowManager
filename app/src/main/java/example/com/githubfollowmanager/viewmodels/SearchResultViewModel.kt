package example.com.githubfollowmanager.viewmodels

import android.databinding.ObservableField
import jp.keita.kagurazaka.rxproperty.RxProperty

/**
 * 検索結果表示画面のViewModel
 */
class SearchResultViewModel constructor(title: String) {

    val title = RxProperty<String>(title)

}