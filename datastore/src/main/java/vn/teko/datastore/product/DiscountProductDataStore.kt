package vn.teko.datastore.product

import vn.teko.datastore.DataStore

abstract class DiscountProductDataStore : DataStore {
    abstract fun getImageUrl(): String
    abstract fun getName(): String
    abstract fun discount(): String
    abstract fun sellPrice(): String
    abstract fun listedPrice(): String
    abstract fun clickListener(): (() -> Unit)?

    final override fun getAvailableKeys(): List<String> {
        return listOf()
    }
}