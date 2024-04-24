import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class LottoApiApplication

fun main(args: Array<String>) {
    runApplication<LottoApiApplication>(*args)
}

data class LottoNumbers(val numbers: List<Int>)

data class WinningNumbers(val numbers: List<Int>)

data class GameResult(
    val matchedNumbers: Int,
    val isBonusMatched: Boolean,
    val prize: String
)

val savedNumbers = mutableListOf<LottoNumbers>()

val winningNumbers = WinningNumbers(listOf(15, 16, 17, 25, 30, 31, 32))

@RestController
@RequestMapping("/lotto")
class LottoController {

    @PostMapping("/generate")
    fun generateNumbers(): LottoNumbers {
        val randomNumbers = (1..45).toList().shuffled().take(7)
        return LottoNumbers(randomNumbers)
    }

    @PostMapping("/save")
    fun saveNumbers(@RequestBody numbers: LottoNumbers) {
        savedNumbers.add(numbers)
    }

    @GetMapping("/saved")
    fun getSavedNumbers(): List<LottoNumbers> {
        return savedNumbers
    }

    @PostMapping("/result")
    fun checkResult(@RequestBody numbers: LottoNumbers): GameResult {
        val matchedNumbers = numbers.numbers.intersect(winningNumbers.numbers).count()
        val isBonusMatched = numbers.numbers.contains(winningNumbers.numbers.last())
        val prize = when {
            matchedNumbers == 6 -> "1등"
            matchedNumbers == 5 && isBonusMatched -> "2등"
            matchedNumbers == 5 -> "3등"
            matchedNumbers == 4 -> "4등"
            matchedNumbers == 3 -> "5등"
            else -> "꽝"
        }
        return GameResult(matchedNumbers, isBonusMatched, prize)
    }
}
