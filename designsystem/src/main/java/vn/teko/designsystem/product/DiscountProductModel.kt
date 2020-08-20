package vn.teko.designsystem.product

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import vn.teko.datastore.product.DiscountProductDataStore
import vn.teko.designsystem.R

@EpoxyModelClass
abstract class DiscountProductModel : EpoxyModelWithHolder<DiscountProductItemHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.item_discount_product
    }

    @EpoxyAttribute
    lateinit var dataStore: DiscountProductDataStore

    override fun bind(holder: DiscountProductItemHolder) {
        holder.apply {
            image?.let {
                Glide.with(it).load(dataStore.getImageUrl()).into(it)
            }
            name?.text = dataStore.getName()
            sellPrice?.text = dataStore.sellPrice()
            listedPrice?.text = dataStore.listedPrice()
            discount?.text = dataStore.discount()

            root?.setOnClickListener {
                dataStore.clickListener()?.invoke()
            }
        }
    }

}

class DiscountProductItemHolder : EpoxyHolder() {
    var image: AppCompatImageView? = null
    var name: AppCompatTextView? = null
    var sellPrice: AppCompatTextView? = null
    var listedPrice: AppCompatTextView? = null
    var discount: AppCompatTextView? = null
    var root: View? = null

    override fun bindView(itemView: View) {
        image = itemView.findViewById(R.id.image)
        name = itemView.findViewById(R.id.name)
        sellPrice = itemView.findViewById(R.id.sellPrice)
        listedPrice = itemView.findViewById(R.id.listedPrice)
        discount = itemView.findViewById(R.id.discount)
        root = itemView
    }
}