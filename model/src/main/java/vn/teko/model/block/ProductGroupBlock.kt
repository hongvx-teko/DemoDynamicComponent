package vn.teko.model.block

class ProductGroupBlock(val products: List<ProductBlock>, val title: String) :
    Combination {
    override fun getBlocks(): List<Block> {
        return products
    }

    override fun getItemType(): String {
        return "discount" // can be "simple", "discount"
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}