package com.product_assignment.api


import com.product_assignment.model.ProductDataItem
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by Gitanjali Ghangale on 10/25/2021.
 */
interface RetrofitApi {
    @GET("fetchFrequentlyOrderedProduct?retailerId=4990224")
    suspend fun fetchAPIData():Response<ArrayList<ProductDataItem>>

}



