package com.product_assignment.model

data class ProductDataItem(
    val displayText: String,
    val distributorName: String,
    val manufacturerName: String,
    val mrp: Double,
    val productName: String,
    val productUrl: String,
    val ptr: Double,
    val schemeLabelForRetailer: String,
    val smartRecommendation: Boolean,
    val stock: Int

)