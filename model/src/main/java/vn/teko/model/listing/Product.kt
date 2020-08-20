package vn.teko.model.listing

data class Product(
    var name: String,
    var price: Price,
    var images: List<Image>
) {
    val discount = if (price.supplierSalePrice <= 0) 0.0 else price.sellPrice / price.supplierSalePrice
}


data class Price(
    var sellPrice: Double,
    var supplierSalePrice: Double
)

data class Image(
    var url: String
)