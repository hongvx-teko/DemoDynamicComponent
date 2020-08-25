package vn.teko.model.listing

data class HomeBlockContent(
    var label: String, // label for this block
    var items: List<HomeBlockContentItem>,
    var fetchParams: HomeBlockContentParams
)