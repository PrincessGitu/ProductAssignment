package com.product_assignment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.product_assignment.model.ProductDataItem
import com.product_assignment.repository.ProductRepository
import com.product_assignment.utils.Resource

import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * Created by Gitanjali Ghangale on 10/25/2021.
 */
class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

     val productDataList: MutableLiveData<Resource<ArrayList<ProductDataItem>>> = MutableLiveData()

    fun getProductData() = viewModelScope.launch {
        productDataList.postValue(Resource.Loading())
        val response = productRepository.fetchFrequentlyOrderData()
        productDataList.postValue(handleProductDataResponse(response))
    }

    private fun handleProductDataResponse(response: Response<ArrayList<ProductDataItem>>): Resource<ArrayList<ProductDataItem>> {
        if (response.isSuccessful) {
            response.body()?.let{
                return Resource.Success(it)


            }
        }
        return Resource.Error(response.message())
    }



}