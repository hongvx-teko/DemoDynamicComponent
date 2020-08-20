package vn.teko.model.listing

data class Product(
    val name: String,
    val sellPrice: Int,
    val listedPrice: Int,
    val sold: Int,
    val total: Int
) {
    val discount = sellPrice.toDouble() / listedPrice
}
