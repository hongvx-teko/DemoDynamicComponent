package vn.teko.model.generator

import vn.teko.model.listing.*

object HomeBlockGenerator {

    fun generateContent(
        label: String?,
        items: List<HomeBlockContentItem>,
        fetchParams: HomeBlockContentParams?
    ): HomeBlockContent {
        return HomeBlockContent(
            label = label ?: "",
            items = items,
            fetchParams = fetchParams ?: HomeBlockContentParams(null)
        )
    }

    fun generateLayout(
        type: String,
        configurations: Map<String, String>?,
        childLayout: HomeBlockLayout?
    ): HomeBlockLayout {
        return HomeBlockLayout(
            type = type ?: "",
            configurations = configurations ?: mapOf(),
            childLayout = childLayout
        )
    }
}