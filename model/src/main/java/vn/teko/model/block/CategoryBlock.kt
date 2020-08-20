package vn.teko.model.block

import vn.teko.model.listing.Category

class CategoryBlock(val category: Category) : Block {

    override fun id(): String {
        return category.id
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}