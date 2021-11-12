package com.example.testapp.ForApi

data class Regres(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)