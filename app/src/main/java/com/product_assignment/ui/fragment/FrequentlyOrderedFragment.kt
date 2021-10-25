package com.product_assignment.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.product_assignment.R
import com.product_assignment.adapter.ProductAdapter
import com.product_assignment.api.RetrofitInstance
import com.product_assignment.databinding.FragmentFrequentlyOrderedBinding

import com.product_assignment.model.ProductDataItem
import com.product_assignment.providerFactory.ProductProviderFactory
import com.product_assignment.repository.ProductRepository
import com.product_assignment.utils.Resource
import com.product_assignment.viewModel.ProductViewModel
import java.util.ArrayList


class FrequentlyOrderedFragment : Fragment() {
private lateinit var binding:FragmentFrequentlyOrderedBinding
private lateinit var mAdapter:ProductAdapter
private lateinit var productViewModel: ProductViewModel
private val productList: ArrayList<ProductDataItem> = ArrayList<ProductDataItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_frequently_ordered, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val productRepository = ProductRepository(RetrofitInstance.retrofitAPi)
        val productProviderFactory = ProductProviderFactory(productRepository)
        productViewModel = ViewModelProvider(this, productProviderFactory).get(ProductViewModel::class.java)

       productViewModel.getProductData()

        setupObserversForProduct()

    }
    private fun setupRecyclerView() {
        mAdapter= ProductAdapter()
        binding.rvFrequentlyOrdered.apply {
            adapter=mAdapter
            layoutManager= LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(
                    binding.rvFrequentlyOrdered.context, ( binding.rvFrequentlyOrdered.layoutManager as LinearLayoutManager).orientation)
            )
        }
    }//setupRecyclerView

    private fun hideProgressBar() {
        binding.progressBarFrequentlyOrdered.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBarFrequentlyOrdered.visibility = View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupObserversForProduct() {
        productViewModel.productDataList.observe(requireActivity(), { productResponse ->
            when (productResponse) {
                is Resource.Success -> {
                    hideProgressBar()
                    productResponse.data.let{
                        for(element in it!!){
                            if(!element.smartRecommendation){
                                val pData=ProductDataItem(element.displayText,element.distributorName,
                                element.manufacturerName,element.mrp,element.productName,element.productUrl,element.ptr
                                ,element.schemeLabelForRetailer,element.smartRecommendation,element.stock)
                                productList.add(pData)
                                mAdapter.differ.submitList(productList)

                            }else{
                                Log.e("Else","="+element.smartRecommendation)
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    showToast(productResponse.message!!)
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }//setupObserversForProduct
}