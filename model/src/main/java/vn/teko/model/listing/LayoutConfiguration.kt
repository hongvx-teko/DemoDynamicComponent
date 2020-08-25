package vn.teko.model.listing

abstract class LayoutConfiguration {

    abstract fun configs(): Map<String, String>

}

class SlideBannerConfiguration : LayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return mapOf(
            "backgroundColor" to "#f00",
            "backgroundImageUrl" to "#f00",
            "autoSwipeTimeMilliseconds" to "10"
        )
    }

}

class RecommendedCategoryConfiguration : LayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return mapOf(
            "backgroundColor" to "#f00",
            "backgroundImageUrl" to "#f00",
            "autoSwipeTimeMilliseconds" to "10"
        )
    }
}

class BestSellingProductConfiguration : LayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return mapOf(
            "backgroundColor" to "#f00",
            "backgroundImageUrl" to "#f00",
            "autoSwipeTimeMilliseconds" to "10"
        )
    }
}

class PromoteSellerConfiguration : LayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return mapOf(
            "backgroundColor" to "#f00",
            "backgroundImageUrl" to "#f00",
            "autoSwipeTimeMilliseconds" to "10"
        )
    }
}

class FlashSaleConfiguration : LayoutConfiguration() {

    override fun configs(): Map<String, String> {
        return mapOf(
            "backgroundColor" to "#f00",
            "backgroundImageUrl" to "#f00",
            "autoSwipeTimeMilliseconds" to "10"
        )
    }
}
