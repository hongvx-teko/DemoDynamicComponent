package vn.teko.datastore.block

import vn.teko.datastore.DataStore

interface BlockWithTitleDataStore : DataStore {
    fun getTitle(): String
    fun clickItemListener(): ((index: Int) -> Unit)?
    fun getData(): List<DataStore>
}