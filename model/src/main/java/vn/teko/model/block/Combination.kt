package vn.teko.model.block

import vn.teko.model.block.Block

interface Combination : Block {

    /**
     * Combination is a Block and contains other blocks
     */
    fun getBlocks(): List<Block>

    /**
     * UI type to display an item of Combination
     */
    fun getItemType(): String

}