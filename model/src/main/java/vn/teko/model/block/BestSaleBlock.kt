package vn.teko.model.block

class BestSaleBlock(val products: List<ProductBlock>, val title: String) :
    Combination {
    override fun getBlocks(): List<Block> {
        return products
    }

    override fun getItemType(): String {
        return "flashsale" // can be "simple", "discount", "flashsale"
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}