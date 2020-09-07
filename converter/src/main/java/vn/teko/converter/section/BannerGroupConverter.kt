package vn.teko.converter.section

import vn.teko.converter.UnsupportedBlockException
import vn.teko.converter.banner.BannerConverter
import vn.teko.datastore.Configuration
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.block.*

class BannerGroupConverter(
    private val model: BannerGroupBlock,
    private val clickItemListener: ((Int) -> Unit)?
) : BlockWithTitleDataStore() {

    override fun getTitle(): String {
        return ""
    }

    override fun clickItemListener(): ((index: Int) -> Unit)? {
        return clickItemListener
    }

    override fun getData(): List<DataStore> {
        val dataStores = mutableListOf<DataStore>()
        model.getBlocks().mapIndexed { index, block ->
            when (block) {
                is BannerBlock -> {
                    dataStores.add(BannerConverter(block, null))
                }
                else -> {
                    // this block is unsupported. ignore
                    throw UnsupportedBlockException(block.javaClass)
                }
            }
        }

        return dataStores
    }

    override fun getId(): String {
        return model.id()
    }

    override fun getConfigurations(): List<Configuration> {
        return model.getConfiguration()
            .filter { getAvailableKeys().contains(it.code) }
            .map { Configuration(it.code, it.value) }
    }
}
