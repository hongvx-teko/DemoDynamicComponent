package vn.teko.converter.product

import vn.teko.datastore.product.DiscountProductDataStore
import vn.teko.model.block.ProductBlock

class DiscountProductConverter(private val product: ProductBlock, private val clickListener: (() -> Unit)?) :
    DiscountProductDataStore {

    override fun getName(): String {
        return product.product.name
    }

    override fun discount(): String {
        return "-${product.product.discount}%"
    }

    override fun sellPrice(): String {
        return product.product.sellPrice.toString()
    }

    override fun listedPrice(): String {
        return product.product.listedPrice.toString()
    }

    override fun clickListener(): (() -> Unit)? {
        return clickListener
    }

    override fun getId(): String {
        return product.product.name
    }

}