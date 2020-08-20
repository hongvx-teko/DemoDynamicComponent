package vn.teko.designsystem.category

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import vn.teko.datastore.banner.BannerDataStore
import vn.teko.datastore.category.CategoryDataStore
import vn.teko.designsystem.R

@EpoxyModelClass
abstract class CategoryModel : EpoxyModelWithHolder<CategoryItemHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.item_category
    }

    @EpoxyAttribute
    lateinit var dataStore: CategoryDataStore

    override fun bind(holder: CategoryItemHolder) {
        holder.apply {
            image?.let {
                Glide.with(it).load(dataStore.getImageUrl()).into(it)
            }

            root?.setOnClickListener {
                dataStore.clickListener()?.invoke()
            }
        }
    }

}

class CategoryItemHolder : EpoxyHolder() {
    var image: AppCompatImageView? = null
    var root: View? = null

    override fun bindView(itemView: View) {
        image = itemView.findViewById(R.id.image)
        root = itemView
    }
}