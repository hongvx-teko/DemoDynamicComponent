package vn.teko.model.block

import vn.teko.model.listing.Product

class ProductBlock(val product: Product) : Block {

    override fun id(): String {
        return product.name
    }

    override fun getConfiguration(): Set<Configuration> {
        return setOf()
    }

}