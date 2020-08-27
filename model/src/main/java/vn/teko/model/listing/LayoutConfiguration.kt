package vn.teko.model.listing

interface LayoutConfiguration {

    fun configs(): Map<String, String>

}

open class BaseLayoutConfiguration : LayoutConfiguration {

    override fun configs(): Map<String, String> {
        return mapOf(
            "backgroundColor" to "#f00",
            "backgroundImageUrl" to ""
        )
    }

}

class CategoryConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "ratio" to "1:1"
        )
    }

}

class BannerConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "ratio" to "3:1"
        )
    }

}

class SimpleProductConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "textSize" to "10",
            "textColor" to "#f00"
        )
    }

}

class DiscountProductConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "productNameTextSize" to "10",
            "productNameTextColor" to "#f00",
            "priceTextSize" to "10",
            "priceTextColor" to "#f00",
            "listedPriceTextSize" to "10",
            "listedPriceTextColor" to "#f00",
            "discountTextSize" to "10",
            "discountTextColor" to "#f00"
        )
    }

}

class SlideBannerConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "autoSwipeTimeMilliseconds" to "10"
        )
    }

}

class RecommendedCategoryConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "titleSize" to "10",
            "titleColor" to "#f00"
        )
    }

}

class BestSellingProductConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "titleSize" to "10",
            "titleColor" to "#f00"
        )
    }

}

class PromoteSellerConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "titleSize" to "10",
            "titleColor" to "#f00"
        )
    }

}

class FlashSaleConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "titleSize" to "10",
            "titleColor" to "#f00"
        )
    }

}

class NestedBlockConfiguration : BaseLayoutConfiguration()

