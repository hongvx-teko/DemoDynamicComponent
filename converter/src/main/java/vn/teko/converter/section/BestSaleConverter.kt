package vn.teko.converter.section

import vn.teko.converter.product.DiscountProductConverter
import vn.teko.converter.product.FlashSaleProductConverter
import vn.teko.converter.product.SimpleProductConverter
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.model.block.BestSaleBlock

class BestSaleConverter(
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
            when (model.getItemType()) {
                "simple" -> {
                    SimpleProductConverter(
                        product = product,
                        clickListener = { clickItemListener?.invoke(index) })
                }
                "flashsale" -> {
                    FlashSaleProductConverter(
                        product = product,
                        clickListener = { clickItemListener?.invoke(index) })
                }
                "discount" -> {
                    DiscountProductConverter(
                        product = product,
                        clickListener = { clickItemListener?.invoke(index) })
                }
                else -> {
                    DiscountProductConverter(
                        product = product,
                        clickListener = { clickItemListener?.invoke(index) })
                }
            }

        }
    }

    override fun getId(): String {
        return model.id()
    }
}
