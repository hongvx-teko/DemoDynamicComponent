package vn.teko.converter.section

import vn.teko.converter.product.DiscountProductConverter
import vn.teko.converter.product.SimpleProductConverter
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.BestSaleBlock
import vn.teko.model.Product
import vn.teko.model.ProductBlock

class BestSaleSection(
    private val model: BestSaleBlock,
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
            DiscountProductConverter(
                product = ProductBlock(product),
                clickListener = { clickItemListener?.invoke(index) })
        }
    }

    override fun getId(): String {
        return model.id()
    }
}
