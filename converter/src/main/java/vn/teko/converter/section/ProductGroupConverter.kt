package vn.teko.converter.section

import vn.teko.converter.product.DiscountProductConverter
import vn.teko.converter.product.FlashSaleProductConverter
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
        return model.products.mapIndexed { index, product ->
            when (model.getItemType()) {
                "simple" -> {
                    SimpleProductConverter(
                        product = ProductBlock(product.product),
                        clickListener = { clickItemListener?.invoke(index) })

                }
                "discount" -> {
                    DiscountProductConverter(
                        product = ProductBlock(product.product),
                        clickListener = { clickItemListener?.invoke(index) })

                }
                "flashsale" -> {
                    FlashSaleProductConverter(
                        product = ProductBlock(product.product),
                        clickListener = { clickItemListener?.invoke(index) })

                }
                else -> {
                    SimpleProductConverter(
                        product = ProductBlock(product.product),
                        clickListener = { clickItemListener?.invoke(index) })

                }
            }
        }
    }

    override fun getId(): String {
        return model.id()
    }
}
