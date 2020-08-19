package vn.teko.converter.section

import vn.teko.converter.product.SimpleProductConverter
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.Product
import vn.teko.model.ProductBlock
import vn.teko.model.RecentBlock

class RecentSection(
    private val model: RecentBlock,
    private val clickItemListener: ((Int) -> Unit)?
) : BlockWithTitleDataStore {

    override fun getTitle(): String {
        return model.title
    }

    override fun clickItemListener(): ((index: Int) -> Unit)? {
        return clickItemListener
    }

    override fun getData(): List<DataStore> {
        return model.products.mapIndexed { index, product ->
            SimpleProductConverter(
                product = ProductBlock(product),
                clickListener = { clickItemListener?.invoke(index) })
        }
    }

    override fun getId(): String {
        return model.id()
    }
}
