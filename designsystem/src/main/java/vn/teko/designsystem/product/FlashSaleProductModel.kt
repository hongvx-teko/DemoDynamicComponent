package vn.teko.designsystem.product

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import vn.teko.datastore.product.FlashSaleProductDataStore
import vn.teko.designsystem.R

@EpoxyModelClass
abstract class FlashSaleProductModel : EpoxyModelWithHolder<FlashSaleProductItemHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.item_flashsale_product
    }

    @EpoxyAttribute
    lateinit var dataStore: FlashSaleProductDataStore

    override fun bind(holder: FlashSaleProductItemHolder) {
        holder.apply {
            name?.text = dataStore.getName()
            discount?.text = dataStore.getDiscount()
            remain?.text = "${dataStore.getSold()}/${dataStore.getTotal()}"

            root?.setOnClickListener {
                dataStore.clickListener()?.invoke()
            }
        }
    }

}

class FlashSaleProductItemHolder : EpoxyHolder() {
    var name: AppCompatTextView? = null
    var discount: AppCompatTextView? = null
    var remain: AppCompatTextView? = null
    var root: View? = null

    override fun bindView(itemView: View) {
        name = itemView.findViewById(R.id.name)
        discount = itemView.findViewById(R.id.discount)
        remain = itemView.findViewById(R.id.remain)
        root = itemView
    }
}