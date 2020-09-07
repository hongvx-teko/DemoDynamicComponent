package vn.teko.converter.banner

import vn.teko.datastore.Configuration
import vn.teko.datastore.banner.BannerDataStore
import vn.teko.model.block.BannerBlock

class BannerConverter(private val block: BannerBlock, private val clickListener: (() -> Unit)?) :
    BannerDataStore() {

    override fun getImageUrl(): String {
        return block.banner.imageUrl
    }

    override fun clickListener(): (() -> Unit)? {
        return clickListener
    }

    override fun getId(): String {
        return block.banner.id
    }

    override fun getConfigurations(): List<Configuration> {
        return block.getConfiguration()
            .filter { getAvailableKeys().contains(it.code) }
            .map { Configuration(it.code, it.value) }
    }

}