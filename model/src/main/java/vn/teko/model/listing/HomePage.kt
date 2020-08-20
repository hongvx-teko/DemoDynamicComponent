package vn.teko.model.listing

open class BaseResponse<T> {
    var result: T? = null
}

class HomePage(var blocks: List<HomeBlock>)

data class HomeBlock(
    var id: String,
    var label: String,
    var type: String,
    var content: HomeBlockContent
)

data class HomeBlockContent(
    var items: List<HomeBlockContentItem>,
    var fetchParams: HomeBlockContentParams
)

data class HomeBlockContentItem(
    var id: String,
    var label: String,
    var imageUrl: String
)

data class HomeBlockContentParams(
    var filter: HomeBlockContentParamsFilter? = null,
    var sorting: HomeBlockContentParamsSort? = null
)

data class HomeBlockContentParamsFilter(
    var priceLte: String,
    var priceGte: String
)

data class HomeBlockContentParamsSort(
    var sort: String,
    var order: String
)