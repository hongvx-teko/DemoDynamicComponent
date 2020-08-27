package vn.teko.dynamiclayout.parser

interface AbstractBlockFactory {

    /**
     * create a family of blocks
     */
    fun createFactory(type: String): BlockFactory

}