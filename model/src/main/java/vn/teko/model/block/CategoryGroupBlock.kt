package vn.teko.model.block

class CategoryGroupBlock(val categories: List<CategoryBlock>, val title: String, val itemLayout: String) : Combination {

    override fun getBlocks(): List<Block> {
        return categories
    }

    override fun getItemType(): String {
        return itemLayout
//        return "category" // can be "simple", "discount", "flashsale", "category"
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}