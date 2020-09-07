package vn.teko.datastore.block

import vn.teko.datastore.DataStore

abstract class BlockWithTitleDataStore : DataStore {
    abstract fun getTitle(): String
    abstract fun clickItemListener(): ((index: Int) -> Unit)?
    abstract fun getData(): List<DataStore>

    final override fun getAvailableKeys(): List<String> {
        return listOf()
    }
}