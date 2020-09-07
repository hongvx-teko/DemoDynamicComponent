package vn.teko.datastore

interface DataStore {
    fun getId(): String
    fun getConfigurations(): List<Configuration>
    fun getAvailableKeys(): List<String>
}