package com.daelim.springtest.main.api.model.dto

data class LottoNumbers(val numbers: List<Int>)

data class TestDto(
    val id: String,
    val matchedNumbers: Int,
    val isBonusMatched: Boolean,
    val prize: String
)

data class WinningNumbers(val numbers: List<Int>)
