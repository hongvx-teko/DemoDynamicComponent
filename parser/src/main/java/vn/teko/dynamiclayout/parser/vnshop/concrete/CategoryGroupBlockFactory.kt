package vn.teko.dynamiclayout.parser.vnshop.concrete

import vn.teko.dynamiclayout.parser.BlockFactory
import vn.teko.model.block.Block
import vn.teko.model.block.CategoryBlock
import vn.teko.model.block.CategoryGroupBlock
import vn.teko.model.listing.Category
import vn.teko.model.listing.HomeBlock

class CategoryGroupBlockFactory : BlockFactory {

    override fun createBlock(homeBlock: HomeBlock): Block? {
        val categories = mutableListOf<Category>()
        homeBlock.content.items?.map { i -> categories.add(Category(i.id, i.label, i.imageUrl)) }
        return if (categories.isNotEmpty()) {
            CategoryGroupBlock(
                categories.map { i -> CategoryBlock(i) },
                homeBlock.content.label ?: "",
                homeBlock.layout.childLayout?.type ?: "unknown"
            )
        } else {
            null
        }
    }

}