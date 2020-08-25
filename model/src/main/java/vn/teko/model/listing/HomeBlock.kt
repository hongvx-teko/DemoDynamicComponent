package vn.teko.model.listing

data class HomeBlock(
    /**
     * identity of a block
     */
    var id: String,
    /**
     * how it will be laid out, include its children
     */
    var layout: HomeBlockLayout,
    /**
     * block content
     */
    var content: HomeBlockContent
)
