package vn.teko.datastore.category

import vn.teko.datastore.DataStore

interface CategoryDataStore : DataStore {
    fun getImageUrl(): String
    fun clickListener(): (() -> Unit)?
}