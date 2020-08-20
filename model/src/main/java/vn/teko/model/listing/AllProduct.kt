package vn.teko.model.listing

class AllProduct : BaseResponse<AllProduct.AllProductResult>() {

    class AllProductResult {
        var products: List<Product>? = null
    }
}

