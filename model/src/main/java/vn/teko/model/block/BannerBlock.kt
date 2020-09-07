package vn.teko.model.block

import vn.teko.model.listing.Banner

class BannerBlock(val banner: Banner, val configurations: Set<Configuration>) : Block {

    override fun id(): String {
        return banner.id
    }

    override fun getConfiguration(): Set<Configuration> {
        return configurations
    }
}