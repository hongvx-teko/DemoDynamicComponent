package vn.teko.model.block

class ProductGroupBlock(
    private val products: List<ProductBlock>,
    val title: String,
    private val itemLayout: String
) : Combination {
    override fun getBlocks(): List<Block> {
        return products
    }

    override fun getItemType(): String {
        return itemLayout // can be "simple", "discount"
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): Set<Configuration> {
        return setOf()
    }
}