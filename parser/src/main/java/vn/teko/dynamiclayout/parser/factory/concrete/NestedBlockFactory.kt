package vn.teko.dynamiclayout.parser.factory.concrete

import vn.teko.dynamiclayout.parser.factory.BlockFactory
import vn.teko.dynamiclayout.parser.factory.VnShopBlockFactory
import vn.teko.model.block.Block
import vn.teko.model.block.NestedBlock
import vn.teko.model.listing.HomeBlock

class NestedBlockFactory : BlockFactory {

    override fun createBlock(homeBlock: HomeBlock): Block? {
        val blocks = mutableListOf<Block>()

        homeBlock.content.childs?.map { child ->
            val factory = VnShopBlockFactory().createFactory(child.layout.type)
            factory.createBlock(child)?.let { block ->
                blocks.add(block)
            }
        }

        return NestedBlock(blocks, homeBlock.content.label ?: "")
    }

}