package vn.teko.model.block

class NestedBlock(val bs: List<Block>, val title: String) :
    Combination {
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