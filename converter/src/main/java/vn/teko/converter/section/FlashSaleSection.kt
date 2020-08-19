package vn.teko.converter.section

import vn.teko.converter.product.FlashSaleProductConverter
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.FlashSaleBlock
import vn.teko.model.ProductBlock

class FlashSaleSection(
    private val model: FlashSaleBlock,
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
            FlashSaleProductConverter(
                product = ProductBlock(product),
                clickListener = { clickItemListener?.invoke(index) })
        }
    }

    override fun getId(): String {
        return model.id()
    }
}
