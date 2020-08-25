package vn.teko.converter.section

import vn.teko.converter.product.DiscountProductConverter
import vn.teko.converter.product.SimpleProductConverter
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.block.ProductGroupBlock
import vn.teko.model.block.ProductBlock

class ProductGroupConverter(
    private val model: ProductGroupBlock,
    private val clickItemListener: ((Int) -> Unit)?
) : BlockWithTitleDataStore {

    override fun getTitle(): String {
        return model.title
    }

    override fun clickItemListener(): ((index: Int) -> Unit)? {
        return clickItemListener
    }

    override fun getData(): List<DataStore> {
        return model.getBlocks().mapIndexed { index, product ->
            when (model.getItemType()) {
                "simple" -> {
                    SimpleProductConverter(
                        product = product as ProductBlock,
                        clickListener = { clickItemListener?.invoke(index) })

                }
                "discount" -> {
                    DiscountProductConverter(
                        product = product as ProductBlock,
                        clickListener = { clickItemListener?.invoke(index) })

                }
                else -> {
                    SimpleProductConverter(
                        product = product as ProductBlock,
                        clickListener = { clickItemListener?.invoke(index) })

                }
            }
        }
    }

    override fun getId(): String {
        return model.id()
    }
}
