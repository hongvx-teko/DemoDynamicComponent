package vn.teko.dynamiclayout.parser.vnshop

import vn.teko.dynamiclayout.parser.AbstractBlockFactory
import vn.teko.dynamiclayout.parser.BlockFactory
import vn.teko.dynamiclayout.parser.vnshop.concrete.*

class VnShopBlockFactory : AbstractBlockFactory {

    override fun createFactory(type: String): BlockFactory {
        return when (type) {
            "SLIDE_BANNER" -> {
                BannerGroupBlockFactory()
            }
            "RECOMMENDED_CATEGORY" -> {
                CategoryGroupBlockFactory()
            }
            "BEST_SELLING_PRODUCT", "PROMOTE_SELLER", "FLASH_SALE" -> {
                ProductGroupBlockFactory()
            }
            "NESTED_BLOCK" -> {
                NestedBlockFactory()
            }
            else -> {
                EmptyBlockFactory()
            }
        }
    }

}