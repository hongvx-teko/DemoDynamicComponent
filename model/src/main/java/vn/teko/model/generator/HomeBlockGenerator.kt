package vn.teko.model.generator

import vn.teko.model.listing.*

object HomeBlockGenerator {

    fun generateContent(
        label: String? = null,
        items: List<HomeBlockContentItem>? = null,
        fetchParams: HomeBlockContentParams? = null,
        children: List<HomeBlock>? = null
    ): HomeBlockContent {
        return HomeBlockContent(
            label = label,
            items = items ?: listOf(),
            fetchParams = fetchParams ?: HomeBlockContentParams(null),
            childs = children ?: listOf()
        )
    }

    fun generateLayout(
        type: String,
        configurations: Map<String, String>? = null,
        childLayout: HomeBlockLayout? = null
    ): HomeBlockLayout {
        return HomeBlockLayout(
            type = type,
            configurations = configurations ?: mapOf(),
            childLayout = childLayout
        )
    }
}