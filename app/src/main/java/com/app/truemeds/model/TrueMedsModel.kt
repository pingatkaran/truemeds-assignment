package com.app.truemeds.model

data class TrueMedsModel(
    val result: Result
)

data class Result(
    val category: List<Category>,
    val article: List<Article>
)

data class Category(

    val name: String,
    val id: Int
)

data class Article(

    val description: String,
    val categoryName: String,
    val type: Int,
    val author: String,
    val categoryId: Int,
    val name: String,
    val id: Int,
    val url: String,
    val createdOn: String,
    val image: String,
    val articleTime: Int,
    val ranking: Int
)