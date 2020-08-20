package vn.teko.designsystem.product

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import vn.teko.datastore.product.SimpleProductDataStore
import vn.teko.designsystem.R

@EpoxyModelClass
abstract class SimpleProductModel : EpoxyModelWithHolder<ProductItemHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.item_simple_product
    }

    @EpoxyAttribute
    lateinit var dataStore: SimpleProductDataStore

    override fun bind(holder: ProductItemHolder) {
        holder.apply {
            image?.let {
                Glide.with(it).load(dataStore.getImageUrl()).into(it)
            }
            price?.text = dataStore.getPrice()

            root?.setOnClickListener {
                dataStore.clickListener()?.invoke()
            }
        }
    }

}

class ProductItemHolder : EpoxyHolder() {
    var image: AppCompatImageView? = null
    var price: AppCompatTextView? = null
    var root: View? = null

    override fun bindView(itemView: View) {
        image = itemView.findViewById(R.id.image)
        price = itemView.findViewById(R.id.price)
        root = itemView
    }
}