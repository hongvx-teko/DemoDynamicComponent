package vn.teko.model.block

import vn.teko.model.listing.Category

class CategoryBlock(val category: Category, val configurations: Set<Configuration>) : Block {

    override fun id(): String {
        return category.id
    }

    override fun getConfiguration(): Set<Configuration> {
        return configurations
    }
}