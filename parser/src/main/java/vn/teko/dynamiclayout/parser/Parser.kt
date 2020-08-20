package vn.teko.dynamiclayout.parser

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import vn.teko.model.block.*
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

    private var params: Map<String, String>? = null

    fun fetchData(params: Map<String, String>): List<Block> {
        this.params = params

        return startFetching()
    }

    private fun startFetching(): List<Block> {
        println("start fetching data from network")

        return getRealData()

//        val products = mutableListOf<Block>()
//        products.add(generateBannerGroup())
//        products.add(generateProductGroupBlock())
//        products.add(generateRecommendCategoryBlock())
//        products.add(generateNestedBlock())
//
//        return products
    }

    private fun getRealData(): List<Block> {
        val result = mutableListOf<Block>()

        val blocks = getAllBlocks()

        val expectedTypes = blocks.filter {
            listOf("SLIDE_BANNER", "RECOMMENDED_CATEGORY", "BEST_SELLING_PRODUCT").contains(it.type)
        }

        expectedTypes.map {
            when (it.type) {
                "SLIDE_BANNER" -> {
                    val banners = mutableListOf<Banner>()
                    it.content.items.map { i -> banners.add(Banner(i.id, i.imageUrl)) }
                    if (banners.isNotEmpty()) {
                        result.add(BannerGroupBlock(banners.map { i -> BannerBlock(i) }))
                    }
                }
                "RECOMMENDED_CATEGORY" -> {
                    val categories = mutableListOf<Category>()
                    it.content.items.map { i ->
                        categories.add(
                            Category(
                                i.id,
                                i.label,
                                i.imageUrl
                            )
                        )
                    }
                    if (categories.isNotEmpty()) {
                        result.add(
                            CategoryGroupBlock(
                                categories.map { i -> CategoryBlock(i) },
                                it.label
                            )
                        )
                    }
                }
                "BEST_SELLING_PRODUCT" -> {
                    val products = mutableListOf<Product>()
                    products.addAll(getProducts(it.content.fetchParams))
                    if (products.isNotEmpty()) {
                        result.add(
                            ProductGroupBlock(
                                products.map { i -> ProductBlock(i) },
                                it.label
                            )
                        )
                    }
                }
                else -> {
                }
            }
        }

        return result
    }

    private fun getProducts(params: HomeBlockContentParams): List<Product> {
        val listProduct = REQ_GET_PRODUCTS.httpGet(
            listOf(
                "channel" to "vnshop_online",
                "terminal" to "vnshop_app",
                "_sort" to params.sorting?.sort,
                "_order" to params.sorting?.order,
                "_page" to "1",
                "_limit" to "20",
                "publishStatus" to "true",
                "price_gte" to "1",
                "saleStatuses_ne" to "ngung_kinh_doanh,hang_dat_truoc",
                "clientCode" to "vnshop"
            )
        ).responseObject<AllProduct>()

        return listProduct.third.get().result?.products ?: listOf()
    }

    private fun getAllBlocks(): List<HomeBlock> {
        val homePage = REQ_GET_BLOCKS.httpGet(
            listOf(
                "terminalCode" to "vnshop_app"
            )
        ).responseObject<HomePage>()

        return homePage.third.get().result?.blocks ?: listOf()
    }

    private fun generateNestedBlock(): NestedBlock {
        return NestedBlock(
            listOf(
                generateProductGroupBlock(),
                generateRecommendCategoryBlock()
            ), "Nested Block"
        )
    }

    private fun generateRecommendCategoryBlock(): CategoryGroupBlock {
        val categories = mutableListOf<Category>()
        val catImages = arrayOf(
            "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/3/12/20200312_e2481386-ea8a-4cc5-b818-1a425ae9ffec.png",
            "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/3/18/20200318_8e36f70d-1551-4db9-81ff-f2e07e16da32.jpe",
            "https://phongvu.vn/media/catalog_v2/media/6/90/1575607382.0744328_191200109.jpg",
            "https://phongvu.vn/media/catalog_v2/media/4/18/1569404509.003934_190901259.jpg",
            "https://lh3.googleusercontent.com/apU3QVDtEQtt03xVg3lld-SmDQnfcdO3V48osnMzTPHLRSiNvqMwJPEMwVQty9wSZ3y7gebUf2x-NON6Kdk",
            "https://lh3.googleusercontent.com/Hi7DIhfdVnmRPhQA-JQ9NO_HBAC-Hxj3SfrDXVx-ahHm24r2lsmnG2J7WtJLbw6Du0uHGtQG0T2eosyiOg",
            "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/3/25/20200325_d7e65d06-ce49-42f9-a570-8e0ba35bfbd4.png",
            "https://storage.googleapis.com/teko-gae.appspot.com/media/image/2020/4/16/20200416_6e021416-a3d1-4077-96d4-bed2fe002f2e.png"
        )
        for (j in catImages.indices) {
            categories.add(Category("cat $j", "cat $j", catImages[j]))
        }
        return CategoryGroupBlock(categories.map { CategoryBlock(it) }, "Recommend Categories")
    }

    private fun generateProductGroupBlock(): ProductGroupBlock {
        val products = mutableListOf<Product>()
        for (j in 0..10) {
            products.add(
                Product("flashproduct $j", Price(1000.0, 2000.0), listOf())
            )
        }
        return ProductGroupBlock(products.map {
            ProductBlock(
                it
            )
        }, "Products")
    }

    private fun generateBannerGroup(): BannerGroupBlock {
        val banners = mutableListOf<Banner>()
        for (i in bannerImages.indices) {
            banners.add(Banner(id = "banner$i", imageUrl = bannerImages[i]))
        }

        return BannerGroupBlock(banners.map { BannerBlock(it) })
    }


}