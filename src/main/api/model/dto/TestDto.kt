package com.daelim.springtest.main.api.model.dto

data class TestDto(
    val id: String,
    val age: Int,
)

data class TestDtoRequest(
    val age: Int,
)
