package vn.teko.converter.product

import vn.teko.datastore.Configuration
import vn.teko.datastore.product.SimpleProductDataStore
import vn.teko.model.block.ProductBlock

class SimpleProductConverter(
    private val product: ProductBlock,
    private val clickListener: (() -> Unit)?
) : SimpleProductDataStore() {

    override fun getPrice(): String {
        return product.product.price.sellPrice.toString()
    }

    override fun getImageUrl(): String {
        return product.product.images.firstOrNull()?.url ?: ""
    }

    override fun clickListener(): (() -> Unit)? {
        return clickListener
    }

    override fun getId(): String {
        return product.product.name
    }

    override fun getConfigurations(): List<Configuration> {
        return product.getConfiguration()
            .filter { getAvailableKeys().contains(it.code) }
            .map { Configuration(it.code, it.value) }
    }

}