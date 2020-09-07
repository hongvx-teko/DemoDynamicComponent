package vn.teko.model.listing

import vn.teko.model.block.Configuration

data class HomeBlockLayout(
    var type: String,
    var configurations: Set<Configuration>? = null,
    var childLayout: HomeBlockLayout? = null
)