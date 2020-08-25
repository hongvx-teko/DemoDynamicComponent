package vn.teko.dynamiclayout.parser.factory

import vn.teko.model.block.Block
import vn.teko.model.listing.HomeBlock

interface BlockFactory {

    /**
     * create a block from HomeBlock loaded from listing service
     *
     * @return a Block to be render, or null if cannot create a block
     */
    fun createBlock(homeBlock: HomeBlock): Block?

}