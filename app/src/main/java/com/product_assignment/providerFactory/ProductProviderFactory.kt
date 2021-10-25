package com.product_assignment.providerFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.product_assignment.repository.ProductRepository
import com.product_assignment.viewModel.ProductViewModel

/**
 * Created by Gitanjali Ghangale on 10/25/2021.
 */
@Suppress("UNCHECKED_CAST")
class ProductProviderFactory(private val productRepository: ProductRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return ProductViewModel(productRepository) as T
    }
}