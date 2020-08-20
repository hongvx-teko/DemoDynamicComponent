package vn.teko.model

interface Block {

    /**
     * identifier of a block
     */
    fun id(): String

    /**
     * all configurations of this block will be listed here, everything about how to layout this block in parent
     * such as padding, margin, size, color, ...
     */
    fun getConfiguration(): List<Configuration>

}

interface Combination : Block {

    /**
     * Combination is a Block and contains other blocks
     */
    fun getBlocks(): List<Block>

    /**
     * UI type to display an item of Combination
     */
    fun getItemType(): String

}

abstract class BlockFactory {
    abstract fun createBlock(): Block
}

class ProductBlock(val product: Product) : Block {

    override fun id(): String {
        return product.name
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }

}

class FlashSaleBlock(val products: List<ProductBlock>, val title: String) : Combination {
    override fun getBlocks(): List<Block> {
        return products
    }

    override fun getItemType(): String {
        return "discount" // can be "simple", "discount", "flashsale"
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}

class BestSaleBlock(val products: List<ProductBlock>, val title: String) : Combination {
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

class RecentBlock(val products: List<ProductBlock>, val title: String) : Combination {
    override fun getBlocks(): List<Block> {
        return products
    }

    override fun getItemType(): String {
        return "simple" // can be "simple", "discount", "flashsale"
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}

class Block2(val bs: List<Block>, val title: String) : Combination {
    override fun getBlocks(): List<Block> {
        return bs
    }

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }

    override fun getItemType(): String {
        return ""
    }

}

class SectionTitleBlock(val title: String) : Block {

    override fun id(): String {
        return title
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}

//class ApolloBlockFactory : BlockFactory() {
//
//    override fun createBlock(): Block {
//        return when (type) {
//            "product" -> {
//                ProductBlock()
//            }
//            "section_title" -> {
//                SectionTitleBlock()
//            }
//            "flash_sale" -> {
//                FlashSaleBlock()
//            }
//            else -> {
//                throw UnSupportModelException()
//            }
//        }
//    }
//}

class UnSupportModelException() : Exception("unsupported model.")