package vn.teko.model.block

class BannerGroupBlock(private val banners: List<BannerBlock>, private val configurations: Set<Configuration>) : Combination {

    override fun getBlocks(): List<Block> {
        return banners
    }

    override fun getItemType(): String {
        return "banner"
    }

    override fun id(): String {
        return "banner"
    }

    override fun getConfiguration(): Set<Configuration> {
        return configurations
    }
}