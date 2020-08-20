package vn.teko.datastore.product

import vn.teko.datastore.DataStore

interface SimpleProductDataStore : DataStore {
    fun getPrice(): String
    fun getImageUrl(): String
    fun clickListener(): (() -> Unit)?
}