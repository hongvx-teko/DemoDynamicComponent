package vn.teko.dynamiclayout.parser

import com.google.gson.Gson
import vn.teko.model.block.Block
import vn.teko.model.listing.HomeBlock
import vn.teko.model.listing.HomePage

abstract class Parser {

    /**
     * how to create a block to render from HomeBlock return by listing service
     */
    private fun createBlock(homeBlock: HomeBlock): Block? {
        return provideBlockFactory().createFactory(homeBlock.layout.type).createBlock(homeBlock)
    }

    /**
     * start fetching data and return a list of block will be displayed
     */
    fun fetchData(params: Map<String, String>): List<Block> {
        val result = mutableListOf<Block>()

        val blocks = getAllBlocks(params)
        val homePageResult = HomePage.HomePageResult()
        homePageResult.blocks = blocks
        val homePage = HomePage()
        homePage.result = homePageResult
        val json = Gson().toJson(homePage)

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