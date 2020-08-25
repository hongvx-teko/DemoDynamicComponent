package vn.teko.dynamiclayout.parser

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import vn.teko.dynamiclayout.parser.factory.BannerGroupBlockFactory
import vn.teko.dynamiclayout.parser.factory.BlockFactory
import vn.teko.dynamiclayout.parser.factory.CategoryGroupBlockFactory
import vn.teko.dynamiclayout.parser.factory.ProductGroupBlockFactory
import vn.teko.model.block.*
import vn.teko.model.generator.HomeBlockGenerator
import vn.teko.model.listing.*

class Parser {

    companion object {
        const val REQ_GET_BLOCKS = "https://discovery.stag.tekoapis.net/api/v1/blocks"
        const val REQ_GET_PRODUCTS = "https://listing.stage.tekoapis.net/api/search"
    }

    private val bannerImages = listOf(
        "https://lh3.googleusercontent.com/Qn76uZPKqd70-NLyaSgX9VNJaxbfUMDb95FiHsWhKzBuzEctpZK2_PJ9CF6uloOKhgHORb6Okum5el8zp6E",
        "https://lh3.googleusercontent.com/wbQLhZEM9svQtJqB4OEtCCLbRk5qM9JsT-ib98cWvZs6jDAJh8GSea-q_LPxu4EuXhI2nny8rdJWivuS5vk",
        "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/2/28/dab503c3-bef5-42ea-83a7-1c0f2d4feee1_1111.png"
    )

    private val catImages = arrayOf(
        "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/3/12/20200312_e2481386-ea8a-4cc5-b818-1a425ae9ffec.png",
        "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/3/18/20200318_8e36f70d-1551-4db9-81ff-f2e07e16da32.jpe",
        "https://phongvu.vn/media/catalog_v2/media/6/90/1575607382.0744328_191200109.jpg",
        "https://phongvu.vn/media/catalog_v2/media/4/18/1569404509.003934_190901259.jpg",
        "https://lh3.googleusercontent.com/apU3QVDtEQtt03xVg3lld-SmDQnfcdO3V48osnMzTPHLRSiNvqMwJPEMwVQty9wSZ3y7gebUf2x-NON6Kdk",
        "https://lh3.googleusercontent.com/Hi7DIhfdVnmRPhQA-JQ9NO_HBAC-Hxj3SfrDXVx-ahHm24r2lsmnG2J7WtJLbw6Du0uHGtQG0T2eosyiOg",
        "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/3/25/20200325_d7e65d06-ce49-42f9-a570-8e0ba35bfbd4.png",
        "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/4/16/20200416_6e021416-a3d1-4077-96d4-bed2fe002f2e.png"
    )

    fun fetchData(): List<Block> {
        println("start fetching data from network")

        val result = mutableListOf<Block>()

        val blocks = getAllBlocks()

        blocks.map {
            val factory: BlockFactory? = when (it.layout.type) {
                "SLIDE_BANNER" -> {
                    BannerGroupBlockFactory()
                }
                "RECOMMENDED_CATEGORY" -> {
                    CategoryGroupBlockFactory()
                }
                "BEST_SELLING_PRODUCT", "PROMOTE_SELLER", "FLASH_SALE" -> {
                    ProductGroupBlockFactory()
                }
                else -> {
                    null
                }
            }

            factory?.createBlock(it)?.let { block ->
                result.add(block)
            }
        }


        return result
    }

    private fun getAllBlocks(): List<HomeBlock> {
//                val homePage = REQ_GET_BLOCKS.httpGet(
//            listOf(
//                "terminalCode" to "vnshop_app"
//            )
//        ).responseObject<HomePage>()
//        return homePage.third.get().result?.blocks ?: listOf()

        val blocks = mutableListOf<HomeBlock>()
        blocks.add(
            HomeBlock(
                id = "block1",
                layout = HomeBlockGenerator.generateLayout(
                    type = "SLIDE_BANNER",
                    configurations = SlideBannerConfiguration().configs(),
                    childLayout = null
                ),
                content = HomeBlockGenerator.generateContent(
                    label = "Banner Slider",
                    items = bannerImages.mapIndexed { index, url ->
                        HomeBlockContentItem(
                            "banner$index",
                            "Label $index",
                            url
                        )
                    },
                    fetchParams = HomeBlockContentParams(
                        sorting = HomeBlockContentParamsSort("SORT_BY_SCORE", "ORDER_BY_DESCENDING")
                    )
                )
            )
        )
        blocks.add(
            HomeBlock(
                id = "block2",
                layout = HomeBlockGenerator.generateLayout(
                    type = "RECOMMENDED_CATEGORY",
                    configurations = RecommendedCategoryConfiguration().configs(),
                    childLayout = null
                ),
                content = HomeBlockGenerator.generateContent(
                    label = "Recommended Categories",
                    items = catImages.mapIndexed { index, url ->
                        HomeBlockContentItem(
                            "cat$index",
                            "Cat $index",
                            url
                        )
                    },
                    fetchParams = HomeBlockContentParams(
                        sorting = null
                    )
                )
            )
        )

        blocks.add(
            HomeBlock(
                id = "block3",
                layout = HomeBlockGenerator.generateLayout(
                    type = "BEST_SELLING_PRODUCT",
                    configurations = BestSellingProductConfiguration().configs(),
                    childLayout = null
                ),
                content = HomeBlockGenerator.generateContent(
                    label = "BEST_SELLING_PRODUCT",
                    items = listOf(),
                    fetchParams = HomeBlockContentParams(
                        sorting = HomeBlockContentParamsSort("SORT_BY_SCORE", "ORDER_BY_DESCENDING")
                    )
                )
            )
        )

        blocks.add(
            HomeBlock(
                id = "block3",
                layout = HomeBlockGenerator.generateLayout(
                    type = "PROMOTE_SELLER",
                    configurations = PromoteSellerConfiguration().configs(),
                    childLayout = null
                ),
                content = HomeBlockGenerator.generateContent(
                    label = "PROMOTE_SELLER",
                    items = listOf(),
                    fetchParams = HomeBlockContentParams(
                        sorting = HomeBlockContentParamsSort("SORT_BY_PRICE", "ORDER_BY_DESCENDING")
                    )
                )
            )
        )

        blocks.add(
            HomeBlock(
                id = "block4",
                layout = HomeBlockGenerator.generateLayout(
                    type = "FLASH_SALE",
                    configurations = FlashSaleConfiguration().configs(),
                    childLayout = null
                ),
                content = HomeBlockGenerator.generateContent(
                    label = "FLASH_SALE",
                    items = listOf(),
                    fetchParams = HomeBlockContentParams(
                        sorting = HomeBlockContentParamsSort("SORT_BY_SCORE", "ORDER_BY_DESCENDING")
                    )
                )
            )
        )

        return blocks
    }

}