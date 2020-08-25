package vn.teko.model.generator

import vn.teko.model.listing.HomeBlock
import vn.teko.model.listing.HomeBlockContent
import vn.teko.model.listing.HomeBlockContentParams
import vn.teko.model.listing.HomeBlockLayout

class HomeBlockBuilder {
    private var id: String = ""
    private var layout: HomeBlockLayout = HomeBlockLayout("", null, null)
    private var content: HomeBlockContent =
        HomeBlockContent("", listOf(), HomeBlockContentParams())

    fun build(): HomeBlock {
        return HomeBlock(
            id = id,
            layout = layout,
            content = content
        )
    }

    fun setId(id: String) {
        this.id = id
    }

    fun setLayout(layout: HomeBlockLayout) {
        this.layout = layout
    }

    fun setFetchParams(content: HomeBlockContent) {
        this.content = content
    }

}
