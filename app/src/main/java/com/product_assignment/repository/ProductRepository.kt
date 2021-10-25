package com.product_assignment.repository

import com.product_assignment.api.RetrofitApi



/**
 * Created by Gitanjali Ghangale on 10/25/2021.
 */
class ProductRepository(private val apiHelper: RetrofitApi) {

    suspend fun fetchFrequentlyOrderData() = apiHelper.fetchAPIData()
}