package com.albar.moviecatalogue.data

data class CatalogueEntity(
    val id: Int = 0,
    val image: String? = null,
    val movieName: String? = null,
    val release: Int = 0,
    val review: String? = null,
    val rating: Int = 0,
    val duration: String? = null,
    val price: String? = null,
    val about: String? = null
)
