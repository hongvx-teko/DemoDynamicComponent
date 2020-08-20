package vn.teko.model.listing

data class HomeBlockContent(
    var items: List<HomeBlockContentItem>,
    var fetchParams: HomeBlockContentParams
)