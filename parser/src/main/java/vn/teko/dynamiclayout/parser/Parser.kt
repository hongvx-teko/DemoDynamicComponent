package vn.teko.dynamiclayout.parser

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import vn.teko.model.block.*
import vn.teko.model.listing.*

class Parser {

    companion object {
        const val REQ_GET_BLOCKS = "https://discovery.stag.tekoapis.net/api/v1/blocks"
    }

    private val images = listOf(
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

        getRealData()

        val products = mutableListOf<Block>()
        products.add(generateBannerGroup())
        products.add(generateProductGroupBlock())
        products.add(generateRecommendCategoryBlock())
        products.add(generateNestedBlock())

        return products
    }

    private fun getRealData() {
        val homePage = REQ_GET_BLOCKS.httpGet(
            listOf(
                "terminalCode" to "vnshop_app"
            )
        ).responseObject<BaseResponse<HomePage>>()

        val blocks = homePage.third.get().result?.blocks ?: listOf()
        blocks.filter {
            it.type in listOf("SLIDE_BANNER", "RECOMMENDED_CATEGORY", "BEST_SELLING_PRODUCT")
        }
        println(blocks)
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
            "https://phongvu.vn/media/catalog_v2/media/4/18/1569404509.003934_190901259.jpg"
        )
        for (j in 0..3) {
            categories.add(Category("cat $j", "cat $j", catImages[j]))
        }
        return CategoryGroupBlock(categories.map { CategoryBlock(it) }, "Recommend Categories")
    }

    private fun generateProductGroupBlock(): ProductGroupBlock {
        val products = mutableListOf<Product>()
        for (j in 0..10) {
            products.add(
                Product(
                    "flashproduct $j",
                    1000,
                    2000,
                    0,
                    0
                )
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
        for (i in 0..2) {
            banners.add(Banner(id = "banner$i", imageUrl = images[i]))
        }

        return BannerGroupBlock(banners.map { BannerBlock(it) })
    }


}