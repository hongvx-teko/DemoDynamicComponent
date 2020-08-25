package vn.teko.dynamiclayout.parser

import vn.teko.dynamiclayout.parser.factory.AbstractBlockFactory
import vn.teko.model.block.Block
import vn.teko.model.listing.HomeBlock

abstract class Parser {

    private var factory: AbstractBlockFactory? = null

    /**
     * how to create a block to render from HomeBlock return by listing service
     */
    private fun createBlock(homeBlock: HomeBlock): Block? {
        return factory?.createFactory(homeBlock.layout.type)?.createBlock(homeBlock)
    }

    /**
     * start fetching data and return a list of block will be displayed
     */
    fun fetchData(params: Map<String, String>): List<Block> {
        factory = provideBlockFactory()

        val result = mutableListOf<Block>()

        val blocks = getAllBlocks(params)
        blocks.map {
            createBlock(it)?.let { block ->
                result.add(block)
            }
        }

        return result
    }

    protected abstract fun provideBlockFactory(): AbstractBlockFactory

    /**
     * how to fetch all blocks from listing service
     */
    protected abstract fun getAllBlocks(params: Map<String, String>): List<HomeBlock>
}