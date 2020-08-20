package vn.teko.model.block

class BannerGroupBlock(val banners: List<BannerBlock>) : Combination {

    override fun getBlocks(): List<Block> {
        return banners
    }

    override fun getItemType(): String {
        return "banner"
    }

    override fun id(): String {
        return "banner"
    }

    override fun getConfiguration(): List<Configuration> {
        return listOf()
    }
}