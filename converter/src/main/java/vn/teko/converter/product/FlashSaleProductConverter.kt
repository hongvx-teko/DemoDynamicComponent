package vn.teko.converter.product

import vn.teko.datastore.product.FlashSaleProductDataStore
import vn.teko.model.block.ProductBlock

class FlashSaleProductConverter(
    private val product: ProductBlock,
    private val clickListener: (() -> Unit)?
) : FlashSaleProductDataStore {

    override fun getName(): String {
        return product.product.name
    }

    override fun getDiscount(): String {
        return "-${product.product.discount}%"
    }

    override fun getTotal(): Int {
        return product.product.total
    }

    override fun getSold(): Int {
        return product.product.sold
    }

    override fun clickListener(): (() -> Unit)? {
        return clickListener
    }

    override fun getId(): String {
        return product.product.name
    }

}