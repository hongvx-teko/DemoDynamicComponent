package vn.teko.converter.product

import vn.teko.datastore.Configuration
import vn.teko.datastore.product.DiscountProductDataStore
import vn.teko.model.block.ProductBlock

class DiscountProductConverter(private val product: ProductBlock, private val clickListener: (() -> Unit)?) :
    DiscountProductDataStore() {

    override fun getImageUrl(): String {
        return product.product.images.firstOrNull()?.url ?: ""
    }

    override fun getName(): String {
        return product.product.name
    }

    override fun discount(): String {
        return "-${product.product.discount}%"
    }

    override fun sellPrice(): String {
        return product.product.price.sellPrice.toString()
    }

    override fun listedPrice(): String {
        return product.product.price.supplierSalePrice.toString()
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