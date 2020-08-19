package vn.teko.converter.section

import vn.teko.converter.UnsupportedBlockException
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.*

class Block2Section(
    private val model: Block2,
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
                    dataStores.add(FlashSaleSection(block, null))
                }
                is BestSaleBlock -> {
                    dataStores.add(BestSaleSection(block, null))
                }
                is RecentBlock -> {
                    dataStores.add(RecentSection(block, null))
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
