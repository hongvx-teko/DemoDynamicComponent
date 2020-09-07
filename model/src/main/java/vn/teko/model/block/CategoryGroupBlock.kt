package vn.teko.model.block

class CategoryGroupBlock(val categories: List<CategoryBlock>, val title: String, val itemLayout: String, val configurations: Set<Configuration>) : Combination {

    override fun getBlocks(): List<Block> {
        return categories
    }

    override fun getItemType(): String {
        return itemLayout
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): Set<Configuration> {
        return configurations
    }
}