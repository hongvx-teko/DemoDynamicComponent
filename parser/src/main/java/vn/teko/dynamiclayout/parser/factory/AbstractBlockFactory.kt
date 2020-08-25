package vn.teko.dynamiclayout.parser.factory

interface AbstractBlockFactory {

    fun createFactory(type: String): BlockFactory

}