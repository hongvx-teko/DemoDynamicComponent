package vn.teko.dynamiclayout.parser.factory

interface AbstractBlockFactory {

    /**
     * create a family of blocks
     */
    fun createFactory(type: String): BlockFactory

}