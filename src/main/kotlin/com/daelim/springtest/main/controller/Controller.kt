package com.daelim.springtest.main.controller

import com.daelim.springtest.main.api.model.dto.GameResult
import com.daelim.springtest.main.api.model.dto.LottoNumbers
import com.daelim.springtest.main.api.model.dto.WinningNumbers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/lotto")
class LottoController {
    private val savedNumbers = mutableListOf<LottoNumbers>()
    private val winningNumbers = WinningNumbers(listOf(15, 16, 17, 25, 30, 31, 32))

    @PostMapping("/generate")
    fun generateNumbers(): ResponseEntity<LottoNumbers> {
        val randomNumbers = (1..45).shuffled().take(7).sorted()
        return ResponseEntity.ok(LottoNumbers(randomNumbers))
    }

    @PostMapping("/save")
    fun saveNumbers(@RequestBody numbers: LottoNumbers): ResponseEntity<Unit> {
        savedNumbers.add(numbers)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/saved")
    fun getSavedNumbers(): ResponseEntity<List<LottoNumbers>> {
        return ResponseEntity.ok(savedNumbers)
    }

    @PostMapping("/result")
    fun checkResult(@RequestBody numbers: LottoNumbers): ResponseEntity<GameResult> {
        val matchedNumbers = numbers.numbers.intersect(winningNumbers.numbers.dropLast(1)).count()
        val isBonusMatched = numbers.numbers.contains(winningNumbers.numbers.last())
        val prize = when {
            matchedNumbers == 6 -> "1등"
            matchedNumbers == 5 && isBonusMatched -> "2등"
            matchedNumbers == 5 -> "3등"
            matchedNumbers == 4 -> "4등"
            matchedNumbers == 3 -> "5등"
            else -> "꽝"
        }
        return ResponseEntity.ok(GameResult(matchedNumbers, isBonusMatched, prize))
    }
}
