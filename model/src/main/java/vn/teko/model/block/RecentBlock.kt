package vn.teko.model.block

import vn.teko.model.block.Block
import vn.teko.model.block.Combination
import vn.teko.model.block.Configuration
import vn.teko.model.block.ProductBlock

class RecentBlock(val products: List<ProductBlock>, val title: String) :
    Combination {
    override fun getBlocks(): List<Block> {
        return products
    }

    override fun getItemType(): String {
        return "simple" // can be "simple", "discount", "flashsale"
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}