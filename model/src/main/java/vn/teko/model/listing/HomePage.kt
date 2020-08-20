package vn.teko.model.listing

class HomePage : BaseResponse<HomePage.HomePageResult>() {

    class HomePageResult {
        var blocks: List<HomeBlock>? = null
    }

}

