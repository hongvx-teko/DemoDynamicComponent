package vn.teko.demodynamiclayout

import com.airbnb.epoxy.TypedEpoxyController
import vn.teko.converter.section.FlashSaleSection
import vn.teko.converter.product.SimpleProductConverter
import vn.teko.converter.section.BestSaleSection
import vn.teko.converter.section.Block2Section
import vn.teko.converter.section.RecentSection
import vn.teko.designsystem.block.BlockWithTitleModel_
import vn.teko.designsystem.product.SimpleProductModel_
import vn.teko.model.*

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
                is FlashSaleBlock -> {
                    resolveFlashSaleBlock(uiContent)
                }
                is BestSaleBlock -> {
                    resolveBestSaleBlock(uiContent)
                }
                is RecentBlock -> {
                    resolveRecentBlock(uiContent)
                }
                is Block2 -> {
                    resolveBlock2(uiContent)
                }

                else -> {
                }
            }
        }
    }

    private fun resolveBlock2(uiContent: Block2) {
        val block2Section = Block2Section(uiContent, null)
        BlockWithTitleModel_()
            .id(block2Section.getId())
            .dataStore(block2Section)
            .addIf(block2Section.getData().isNotEmpty(), this)

    }

    private fun resolveRecentBlock(uiContent: RecentBlock) {
        val recentSection = RecentSection(uiContent) { idx ->
            callbacks.gotoDetail(
                idx,
                uiContent,
                uiContent.products[idx].product
            )
        }
        BlockWithTitleModel_()
            .id(recentSection.getId())
            .dataStore(recentSection)
            .addIf(uiContent.products.isNotEmpty(), this)
    }

    private fun resolveBestSaleBlock(uiContent: BestSaleBlock) {
        val products = uiContent.products

        BlockWithTitleModel_()
            .id(uiContent.id())
            .dataStore(
                BestSaleSection(uiContent) { idx ->
                    callbacks.gotoDetail(
                        idx,
                        uiContent,
                        products[idx].product
                    )
                })
            .addIf(products.isNotEmpty(), this)
    }

    private fun resolveFlashSaleBlock(uiContent: FlashSaleBlock) {
        val products = uiContent.products

        BlockWithTitleModel_()
            .id(uiContent.id())
            .dataStore(
                FlashSaleSection(uiContent) { idx ->
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