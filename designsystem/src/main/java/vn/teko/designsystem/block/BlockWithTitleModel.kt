package vn.teko.designsystem.block

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.airbnb.epoxy.*
import vn.teko.datastore.DataStore
import vn.teko.datastore.block.BlockWithTitleDataStore
import vn.teko.datastore.banner.BannerDataStore
import vn.teko.datastore.category.CategoryDataStore
import vn.teko.datastore.product.DiscountProductDataStore
import vn.teko.datastore.product.FlashSaleProductDataStore
import vn.teko.datastore.product.SimpleProductDataStore
import vn.teko.designsystem.R
import vn.teko.designsystem.UnsupportedTypeException
import vn.teko.designsystem.banner.BannerModel_
import vn.teko.designsystem.category.CategoryModel_
import vn.teko.designsystem.product.DiscountProductModel_
import vn.teko.designsystem.product.FlashSaleProductModel_
import vn.teko.designsystem.product.SimpleProductModel_

@EpoxyModelClass
abstract class BlockWithTitleModel : EpoxyModelWithHolder<FlashSaleItemHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.block_title_with_items
    }

    private val controller = BlockWithTitleController()

    @EpoxyAttribute
    lateinit var dataStore: BlockWithTitleDataStore

    override fun bind(holder: FlashSaleItemHolder) {
        holder.apply {
            title?.text = dataStore.getTitle()
            title?.isVisible = dataStore.getTitle().isNotEmpty()

            controller.setData(dataStore.getData())
            items?.setController(controller)
            items?.setItemSpacingDp(8)
        }
    }

    override fun unbind(holder: FlashSaleItemHolder) {
        super.unbind(holder)
        holder.items?.clear()
        holder.items?.adapter = null
    }

}

class FlashSaleItemHolder : EpoxyHolder() {
    var title: AppCompatTextView? = null
    var items: EpoxyRecyclerView? = null
    var root: View? = null

    override fun bindView(itemView: View) {
        title = itemView.findViewById(R.id.title)
        items = itemView.findViewById(R.id.items)
        root = itemView
    }
}

class BlockWithTitleController : TypedEpoxyController<List<DataStore>>() {

    /**
     * define all types this view supports
     */
    override fun buildModels(data: List<DataStore>?) {
        data?.mapIndexed { index: Int, uiContent: DataStore ->
            when (uiContent) {
                is SimpleProductDataStore -> {
                    SimpleProductModel_()
                        .id(uiContent.getId())
                        .dataStore(uiContent)
                        .addTo(this)
                }
                is FlashSaleProductDataStore -> {
                    FlashSaleProductModel_()
                        .id(uiContent.getId())
                        .dataStore(uiContent)
                        .addTo(this)
                }
                is DiscountProductDataStore -> {
                    DiscountProductModel_()
                        .id(uiContent.getId())
                        .dataStore(uiContent)
                        .addTo(this)
                }
                is BlockWithTitleDataStore -> {
                    BlockWithTitleModel_()
                        .id(uiContent.getId())
                        .dataStore(uiContent)
                        .addTo(this)
                }
                is BannerDataStore -> {
                    BannerModel_()
                        .id(uiContent.getId())
                        .dataStore(uiContent)
                        .addTo(this)
                }
                is CategoryDataStore -> {
                    CategoryModel_()
                        .id(uiContent.getId())
                        .dataStore(uiContent)
                        .addTo(this)
                }
                else -> {
                    throw UnsupportedTypeException(uiContent.javaClass)
                }
            }

        }
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        throw exception
    }
}