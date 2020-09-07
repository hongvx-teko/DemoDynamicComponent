package vn.teko.datastore.banner

import vn.teko.datastore.DataStore

abstract class BannerDataStore : DataStore {
    abstract fun getImageUrl(): String
    abstract fun clickListener(): (() -> Unit)?
    final override fun getAvailableKeys(): List<String> {
        return listOf(
            "backgroundColor",
            "backgroundImageUrl",
            "paddingTop",
            "paddingBottom",
            "paddingLeft",
            "paddingRight",
            "marginTop",
            "marginBottom",
            "marginLeft",
            "marginRight"
        )
    }
}