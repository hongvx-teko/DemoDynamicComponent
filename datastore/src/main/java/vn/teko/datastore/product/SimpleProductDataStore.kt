package vn.teko.datastore.product

import vn.teko.datastore.DataStore

abstract class SimpleProductDataStore : DataStore {
    abstract fun getPrice(): String
    abstract fun getImageUrl(): String
    abstract fun clickListener(): (() -> Unit)?

    final override fun getAvailableKeys(): List<String> {
        return listOf()
    }
}