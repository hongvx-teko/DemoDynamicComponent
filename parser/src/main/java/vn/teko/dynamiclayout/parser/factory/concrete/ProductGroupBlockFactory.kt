package vn.teko.dynamiclayout.parser.factory.concrete

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import vn.teko.dynamiclayout.parser.VnShopParser
import vn.teko.dynamiclayout.parser.factory.BlockFactory
import vn.teko.model.block.Block
import vn.teko.model.block.ProductBlock
import vn.teko.model.block.ProductGroupBlock
import vn.teko.model.listing.AllProduct
import vn.teko.model.listing.HomeBlock
import vn.teko.model.listing.HomeBlockContentParams
import vn.teko.model.listing.Product

class ProductGroupBlockFactory : BlockFactory {

    override fun createBlock(homeBlock: HomeBlock): Block? {
        val products = mutableListOf<Product>()
        products.addAll(getProducts(homeBlock.content.fetchParams))
        return if (products.isNotEmpty()) {
            ProductGroupBlock(
                products.map { i -> ProductBlock(i) },
                homeBlock.content.label
            )
        } else {
            null
        }
    }

    private fun getProducts(params: HomeBlockContentParams): List<Product> {
        val listProduct = VnShopParser.REQ_GET_PRODUCTS.httpGet(
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
}