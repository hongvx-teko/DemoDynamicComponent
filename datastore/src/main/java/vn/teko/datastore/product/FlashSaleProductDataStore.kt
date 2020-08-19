package vn.teko.datastore.product

import vn.teko.datastore.DataStore

interface FlashSaleProductDataStore : DataStore {
    fun getName(): String
    fun getDiscount(): String
    fun getTotal(): Int
    fun getSold(): Int
    fun clickListener(): (() -> Unit)?
}