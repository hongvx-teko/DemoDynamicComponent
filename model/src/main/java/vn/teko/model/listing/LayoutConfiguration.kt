package vn.teko.model.listing

abstract class LayoutConfiguration {

    abstract fun configs(): Map<String, String>

}

open class BaseLayoutConfiguration : LayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return mapOf(
            "backgroundColor" to "#f00",
            "backgroundImageUrl" to ""
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
            "autoSwipeTimeMilliseconds" to "10"
        )
    }
}

class BestSellingProductConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "autoSwipeTimeMilliseconds" to "10"
        )
    }
}

class PromoteSellerConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "autoSwipeTimeMilliseconds" to "10"
        )
    }
}

class FlashSaleConfiguration : BaseLayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return super.configs() + mapOf(
            "autoSwipeTimeMilliseconds" to "10"
        )
    }
}
