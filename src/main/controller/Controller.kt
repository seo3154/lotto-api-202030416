package com.daelim.springtest.main.controller

import com.daelim.springtest.main.api.model.dto.TestDto
import com.daelim.springtest.main.api.model.dto.TestDtoRequest
import net.datafaker.Faker
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class Controller {
    private val tests = mutableListOf<TestDto>()

    @PostMapping("/test")
    fun postTestDto(
        @RequestBody testDtoRequest: TestDtoRequest
    ): ResponseEntity<TestDto> {
        val faker = Faker(Locale.KOREA)
        val test = TestDto(
            id = faker.name().name(),
            age = testDtoRequest.age
        )
        tests.add(test)
        return ResponseEntity.ok().body(test)
    }
    @GetMapping("/test")
    fun getAllTestDto(
    ): ResponseEntity<List<TestDto>> {
        val response = tests
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/test/{age}")
    fun getTestDto(
        @PathVariable("age") userAge: Int
    ): ResponseEntity<TestDto> {
        val response = tests.firstOrNull{it.age == userAge}
        return ResponseEntity.ok().body(response)
    }
}
