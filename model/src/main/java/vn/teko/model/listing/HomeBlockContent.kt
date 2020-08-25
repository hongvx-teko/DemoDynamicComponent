package vn.teko.model.listing

data class HomeBlockContent(
    var label: String? = null, // label for this block
    var items: List<HomeBlockContentItem>? = null,
    var fetchParams: HomeBlockContentParams? = null,
    /**
     * child blocks
     */
    var childs: List<HomeBlock>? = null
)