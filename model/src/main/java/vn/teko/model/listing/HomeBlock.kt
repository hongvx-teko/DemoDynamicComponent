package vn.teko.model.listing

data class HomeBlock(
    var id: String,
    var label: String,
    var type: String,
    var content: HomeBlockContent
)