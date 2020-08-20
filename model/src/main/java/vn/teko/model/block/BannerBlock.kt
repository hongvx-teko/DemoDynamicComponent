package vn.teko.model.block

import vn.teko.model.listing.Banner

class BannerBlock(val banner: Banner) : Block {

    override fun id(): String {
        return banner.id
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}