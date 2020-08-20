package vn.teko.converter.section

import vn.teko.converter.UnsupportedBlockException
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.block.*

class NestedBlockConverter(
    private val model: NestedBlock,
    private val clickItemListener: ((Int) -> Unit)?
) : BlockWithTitleDataStore {

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
                is FlashSaleBlock -> {
                    dataStores.add(FlashSaleConverter(block, null))
                }
                is BestSaleBlock -> {
                    dataStores.add(BestSaleConverter(block, null))
                }
                is RecentBlock -> {
                    dataStores.add(RecentConverter(block, null))
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
}
