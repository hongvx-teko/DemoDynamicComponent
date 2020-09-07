package vn.teko.designsystem.banner

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import vn.teko.datastore.banner.BannerDataStore
import vn.teko.designsystem.R

@EpoxyModelClass
abstract class BannerModel : EpoxyModelWithHolder<BannerItemHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.item_banner
    }

    @EpoxyAttribute
    lateinit var dataStore: BannerDataStore

    override fun bind(holder: BannerItemHolder) {
        holder.apply {
            image?.let {
                Glide.with(it).load(dataStore.getImageUrl()).into(it)
            }

            dataStore.getConfigurations().map {
                when (it.key) {
                    "backgroundColor" -> {
                        image?.setBackgroundColor(Color.parseColor(it.value))
                    }
                    "backgroundImageUrl" -> {
                        image?.let { img ->
                            Glide.with(img).load(it.value)
                                .listener(object : RequestListener<Drawable> {
                                    override fun onLoadFailed(
                                        e: GlideException?,
                                        model: Any?,
                                        target: Target<Drawable>?,
                                        isFirstResource: Boolean
                                    ): Boolean {
                                        return false
                                    }

                                    override fun onResourceReady(
                                        resource: Drawable?,
                                        model: Any?,
                                        target: Target<Drawable>?,
                                        dataSource: DataSource?,
                                        isFirstResource: Boolean
                                    ): Boolean {
                                        img.background = resource
                                        return false
                                    }

                                })
                        }
                    }
                    "paddingTop" -> {
                        val left = image?.paddingLeft ?: 0
                        val top = image?.paddingTop ?: 0
                        val bottom = image?.paddingBottom ?: 0
                        val right = image?.paddingRight ?: 0

                        image?.setPadding(left, it.value.toIntOrNull() ?: 0, right, bottom)
                    }
                    "paddingBottom" -> {
                        val left = image?.paddingLeft ?: 0
                        val top = image?.paddingTop ?: 0
                        val bottom = image?.paddingBottom ?: 0
                        val right = image?.paddingRight ?: 0

                        image?.setPadding(left, top, right, it.value.toIntOrNull() ?: 0)
                    }
                    "paddingLeft" -> {
                        val left = image?.paddingLeft ?: 0
                        val top = image?.paddingTop ?: 0
                        val bottom = image?.paddingBottom ?: 0
                        val right = image?.paddingRight ?: 0

                        image?.setPadding(it.value.toIntOrNull() ?: 0, top, right, bottom)
                    }
                    "paddingRight" -> {
                        val left = image?.paddingLeft ?: 0
                        val top = image?.paddingTop ?: 0
                        val bottom = image?.paddingBottom ?: 0
                        val right = image?.paddingRight ?: 0

                        image?.setPadding(left, top, it.value.toIntOrNull() ?: 0, bottom)
                    }
                    "marginTop" -> {
                        val layoutParams = image?.layoutParams as ViewGroup.MarginLayoutParams
                        layoutParams.topMargin = it.value.toIntOrNull() ?: 0
                    }
                    "marginBottom" -> {
                        val layoutParams = image?.layoutParams as ViewGroup.MarginLayoutParams
                        layoutParams.bottomMargin = it.value.toIntOrNull() ?: 0
                    }
                    "marginLeft" -> {
                        val layoutParams = image?.layoutParams as ViewGroup.MarginLayoutParams
                        layoutParams.leftMargin = it.value.toIntOrNull() ?: 0
                    }
                    "marginRight" -> {
                        val layoutParams = image?.layoutParams as ViewGroup.MarginLayoutParams
                        layoutParams.rightMargin = it.value.toIntOrNull() ?: 0
                    }
                    else -> {
                    }
                }
            }

            root?.setOnClickListener {
                dataStore.clickListener()?.invoke()
            }
        }
    }

}

class BannerItemHolder : EpoxyHolder() {
    var image: AppCompatImageView? = null
    var root: View? = null

    override fun bindView(itemView: View) {
        image = itemView.findViewById(R.id.image)
        root = itemView
    }
}