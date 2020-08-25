package vn.teko.model.listing

data class HomeBlockLayout(
    var type: String,
    var configurations: Map<String, String>? = null,
    var childLayout: HomeBlockLayout? = null
)