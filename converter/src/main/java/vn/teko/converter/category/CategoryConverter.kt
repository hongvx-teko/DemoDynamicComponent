package vn.teko.converter.category

import vn.teko.datastore.Configuration
import vn.teko.datastore.category.CategoryDataStore
import vn.teko.model.block.CategoryBlock

class CategoryConverter(
    private val block: CategoryBlock,
    private val clickListener: (() -> Unit)?
) : CategoryDataStore() {

    override fun getImageUrl(): String {
        return block.category.imageUrl
    }

    override fun clickListener(): (() -> Unit)? {
        return clickListener
    }

    override fun getId(): String {
        return block.category.id
    }

    override fun getConfigurations(): List<Configuration> {
        return block.getConfiguration()
            .filter { getAvailableKeys().contains(it.code) }
            .map { Configuration(it.code, it.value) }
    }

}