package vn.teko.model.listing

data class HomeBlockContentParams(
    var filter: HomeBlockContentParamsFilter? = null,
    var sorting: HomeBlockContentParamsSort? = null
)