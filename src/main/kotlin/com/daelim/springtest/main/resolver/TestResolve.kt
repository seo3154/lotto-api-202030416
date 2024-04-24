package com.daelim.springtest.main.resolver

import com.daelim.springtest.main.api.model.dto.TestDto
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class PostResolver : GraphQLQueryResolver, GraphQLMutationResolver {
    private val tests = mutableListOf<TestDto>()

    fun findAllTests(): List<TestDto> {
        return tests
    }

    fun findTestById(id: String): TestDto? {
        return tests.find { it.id == id }
    }

    fun createTest(matchedNumbers: Int, isBonusMatched: Boolean, prize: String): TestDto {
        val newTest = TestDto(
            matchedNumbers = matchedNumbers,
            isBonusMatched = isBonusMatched,
            prize = prize
        )
        tests.add(newTest)
        return newTest
    }

    fun deleteAllTests(): Boolean {
        tests.clear()
        return true
    }

    fun updateTestResult(id: String, matchedNumbers: Int, isBonusMatched: Boolean, prize: String): TestDto? {
        val index = tests.indexOfFirst { it.id == id }
        if (index != -1) {
            val updatedTest = TestDto(
                matchedNumbers = matchedNumbers,
                isBonusMatched = isBonusMatched,
                prize = prize
            )
            tests[index] = updatedTest
            return updatedTest
        }
        return null
    }
}
