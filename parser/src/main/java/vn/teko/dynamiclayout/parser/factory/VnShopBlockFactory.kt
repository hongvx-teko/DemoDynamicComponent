package vn.teko.dynamiclayout.parser.factory

import vn.teko.dynamiclayout.parser.factory.concrete.BannerGroupBlockFactory
import vn.teko.dynamiclayout.parser.factory.concrete.CategoryGroupBlockFactory
import vn.teko.dynamiclayout.parser.factory.concrete.EmptyBlockFactory
import vn.teko.dynamiclayout.parser.factory.concrete.ProductGroupBlockFactory

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
            else -> {
                EmptyBlockFactory()
            }
        }
    }

}