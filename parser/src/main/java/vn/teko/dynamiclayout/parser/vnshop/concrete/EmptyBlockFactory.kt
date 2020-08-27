package vn.teko.dynamiclayout.parser.vnshop.concrete

import vn.teko.dynamiclayout.parser.BlockFactory
import vn.teko.model.block.Block
import vn.teko.model.listing.HomeBlock

class EmptyBlockFactory : BlockFactory {

    override fun createBlock(homeBlock: HomeBlock): Block? {
        return null
    }

}