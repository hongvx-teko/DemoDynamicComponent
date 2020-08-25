package vn.teko.dynamiclayout.parser.factory

import vn.teko.dynamiclayout.parser.factory.concrete.*

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