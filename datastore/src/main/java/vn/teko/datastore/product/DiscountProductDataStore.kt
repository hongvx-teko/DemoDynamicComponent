package vn.teko.datastore.product

import vn.teko.datastore.DataStore

interface DiscountProductDataStore : DataStore {
    fun getName(): String
    fun discount(): String
    fun sellPrice(): String
    fun listedPrice(): String
    fun clickListener(): (() -> Unit)?
}