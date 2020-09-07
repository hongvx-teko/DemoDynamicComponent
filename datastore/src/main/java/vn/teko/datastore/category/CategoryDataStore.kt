package vn.teko.datastore.category

import vn.teko.datastore.DataStore

abstract class CategoryDataStore : DataStore {
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