package vn.teko.converter.section

import vn.teko.converter.UnsupportedBlockException
import vn.teko.datastore.Configuration
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.block.*

class NestedBlockConverter(
    private val model: NestedBlock,
    private val clickItemListener: ((Int) -> Unit)?
) : BlockWithTitleDataStore() {

    override fun getTitle(): String {
        return model.title
    }

    override fun clickItemListener(): ((index: Int) -> Unit)? {
        return clickItemListener
    }

    override fun getData(): List<DataStore> {
        val dataStores = mutableListOf<DataStore>()
        model.getBlocks().mapIndexed { index, block ->
            when (block) {
                is ProductGroupBlock -> {
                    dataStores.add(ProductGroupConverter(block, null))
                }
                is CategoryGroupBlock -> {
                    dataStores.add(CategoryGroupConverter(block, null))
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
