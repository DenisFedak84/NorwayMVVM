package com.example.norway.data

data class PaginationModel(
    val total: Int,
    val pages: Int,
    val page: Int,
    val limit: Int,
    val links: LinksModel
)
