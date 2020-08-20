package vn.teko.datastore.banner

import vn.teko.datastore.DataStore

interface BannerDataStore : DataStore {
    fun getImageUrl(): String
    fun clickListener(): (() -> Unit)?
}