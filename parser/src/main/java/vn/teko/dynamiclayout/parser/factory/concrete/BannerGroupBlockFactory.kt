package vn.teko.dynamiclayout.parser.factory.concrete

import vn.teko.dynamiclayout.parser.factory.BlockFactory
import vn.teko.model.block.BannerBlock
import vn.teko.model.block.BannerGroupBlock
import vn.teko.model.block.Block
import vn.teko.model.listing.Banner
import vn.teko.model.listing.HomeBlock

class BannerGroupBlockFactory : BlockFactory {

    override fun createBlock(homeBlock: HomeBlock): Block? {
        val banners = mutableListOf<Banner>()
        homeBlock.content.items.map { i -> banners.add(Banner(i.id, i.imageUrl)) }
        return if (banners.isNotEmpty()) {
            BannerGroupBlock(banners.map { i -> BannerBlock(i) })
        } else {
            null
        }
    }

}