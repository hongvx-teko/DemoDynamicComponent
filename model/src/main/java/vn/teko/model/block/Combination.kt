package vn.teko.model.block

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