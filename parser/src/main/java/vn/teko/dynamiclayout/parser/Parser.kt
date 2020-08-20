package vn.teko.dynamiclayout.parser

import vn.teko.model.*

class Parser {

    private var params: Map<String, String>? = null

    fun fetchData(params: Map<String, String>): List<Block> {
        this.params = params

        return startFetching()
    }

    private fun startFetching(): List<Block> {
        println("start fetching data from network")

        val products = mutableListOf<Block>()
        for (i in 0..1) {
            products.add(ProductBlock(Product("product $i", 1000, 2000, 0, 0)))
        }

        val flashSaleProducts = mutableListOf<Product>()
        for (j in 0..10) {
            flashSaleProducts.add(Product("flashproduct $j", 1000, 2000, 0, 0))
        }
        val flashSaleBlock =
            FlashSaleBlock(flashSaleProducts.map { ProductBlock(it) }, "Flash Sale")
        products.add(flashSaleBlock)

        val bestSaleProducts = mutableListOf<Product>()
        for (j in 0..10) {
            bestSaleProducts.add(Product("bestproduct $j", 1000, 2000, 0, 0))
        }
        val bestSaleBlock = BestSaleBlock(bestSaleProducts.map { ProductBlock(it) }, "Best Sales")
        products.add(bestSaleBlock)

        val recentProducts = mutableListOf<Product>()
        for (j in 0..10) {
            recentProducts.add(Product("recentproduct $j", 1000, 2000, 0, 0))
        }
        products.add(RecentBlock(recentProducts.map { ProductBlock(it) }, "Recent"))

        products.add(Block2(listOf(flashSaleBlock, bestSaleBlock), "Block2"))

        return products
    }


}