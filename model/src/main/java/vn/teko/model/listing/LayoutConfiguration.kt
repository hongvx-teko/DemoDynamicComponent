package vn.teko.model.listing

import vn.teko.model.block.Configuration

interface LayoutConfiguration {

    fun configs(): Set<Configuration>

}

open class BaseLayoutConfiguration : LayoutConfiguration {

    override fun configs(): Set<Configuration> {
        return setOf(
            Configuration("", "backgroundColor", "#00000000"),
            Configuration("", "backgroundImageUrl", ""),
            Configuration("", "paddingTop", "0"),
            Configuration("", "paddingBottom", "0"),
            Configuration("", "paddingLeft", "0"),
            Configuration("", "paddingRight", "0"),
            Configuration("", "marginTop", "0"),
            Configuration("", "marginBottom", "0"),
            Configuration("", "marginLeft", "0"),
            Configuration("", "marginRight", "0")
        )
    }

}

class CategoryConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "ratio", "1:1"),
            Configuration("", "paddingBottom", "15"),
            Configuration("", "backgroundColor", "#ffffffff")
        )
    }

}

class BannerConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "ratio", "3:1")
        )
    }

}

class SimpleProductConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "textSize", "10"),
            Configuration("", "textColor", "#f00")
        )
    }

}

class DiscountProductConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "productNameTextSize", "10"),
            Configuration("", "productNameTextColor", "#f00"),
            Configuration("", "priceTextSize", "10"),
            Configuration("", "priceTextColor", "#f00"),
            Configuration("", "listedPriceTextSize", "10"),
            Configuration("", "listedPriceTextColor", "#f00"),
            Configuration("", "discountTextSize", "10"),
            Configuration("", "discountTextColor", "#f00")
        )
    }

}

class SlideBannerConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "autoSwipeTimeMilliseconds", "10")
        )
    }

}

class RecommendedCategoryConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "titleSize", "10"),
            Configuration("", "titleColor", "#f00")
        )
    }

}

class BestSellingProductConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "titleSize", "10"),
            Configuration("", "titleColor", "#f00")
        )
    }

}

class PromoteSellerConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "titleSize", "10"),
            Configuration("", "titleColor", "#f00")
        )
    }

}

class FlashSaleConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Set<Configuration> {
        return super.configs() + setOf(
            Configuration("", "titleSize", "10"),
            Configuration("", "titleColor", "#f00")
        )
    }

}

class NestedBlockConfiguration : BaseLayoutConfiguration()

