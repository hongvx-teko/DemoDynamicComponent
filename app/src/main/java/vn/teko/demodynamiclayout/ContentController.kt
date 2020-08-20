package vn.teko.demodynamiclayout

import com.airbnb.epoxy.TypedEpoxyController
import vn.teko.converter.product.SimpleProductConverter
import vn.teko.converter.section.*
import vn.teko.designsystem.block.BlockWithTitleModel_
import vn.teko.designsystem.product.SimpleProductModel_
import vn.teko.model.block.*
import vn.teko.model.listing.Product

class ContentController(private val callbacks: Callbacks) : TypedEpoxyController<List<Block>>() {

    interface Callbacks {
        /**
         * all callbacks should be categorized as detail as possible
         */
        fun gotoDetail(index: Int, block: Block, product: Product)
    }

    override fun buildModels(data: List<Block>?) {
        data?.mapIndexed { index: Int, uiContent: Block ->
            when (uiContent) {
                is ProductBlock -> {
                    resolveProductBlock(uiContent, index)
                }
                is ProductGroupBlock -> {
                    resolveProductGroup(uiContent)
                }
                is NestedBlock -> {
                    resolveNestedBlock(uiContent)
                }
                is BannerGroupBlock -> {
                    resolveBannerGroup(uiContent)
                }
                is CategoryGroupBlock -> {
                    resolveCategoryGroup(uiContent)
                }

                else -> {
                }
            }
        }
    }

    private fun resolveCategoryGroup(uiContent: CategoryGroupBlock) {
        val categories = CategoryGroupConverter(uiContent, null)
        BlockWithTitleModel_()
            .id(categories.getId())
            .dataStore(categories)
            .addIf(categories.getData().isNotEmpty(), this)
    }

    private fun resolveBannerGroup(uiContent: BannerGroupBlock) {
        val banners = BannerGroupConverter(uiContent, null)
        BlockWithTitleModel_()
            .id(banners.getId())
            .dataStore(banners)
            .addIf(banners.getData().isNotEmpty(), this)
    }

    private fun resolveNestedBlock(uiContent: NestedBlock) {
        val nestedBlockSection = NestedBlockConverter(uiContent, null)
        BlockWithTitleModel_()
            .id(nestedBlockSection.getId())
            .dataStore(nestedBlockSection)
            .addIf(nestedBlockSection.getData().isNotEmpty(), this)

    }

    private fun resolveProductGroup(uiContent: ProductGroupBlock) {
        val products = uiContent.products

        BlockWithTitleModel_()
            .id(uiContent.id())
            .dataStore(
                ProductGroupConverter(uiContent) { idx ->
                    callbacks.gotoDetail(
                        idx,
                        uiContent,
                        products[idx].product
                    )
                })
            .addIf(products.isNotEmpty(), this)
    }

    private fun resolveProductBlock(uiContent: ProductBlock, index: Int) {
        val product = uiContent.product
        val productInList =
            SimpleProductConverter(uiContent) {
                callbacks.gotoDetail(
                    index,
                    uiContent,
                    product
                )
            }
        SimpleProductModel_()
            .id(productInList.getId())
            .dataStore(productInList)
            .addTo(this)
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        throw exception
    }
}