package funfun.study.kfp.chap3_practice

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class FConsTest : BehaviorSpec({

    Given("data object 출력 테스트") {
        val nil = FNil

        When("출력했을 때") {
            nil.toString() shouldBe "FNil"
        }
    }

    Given("cons 생성 테스트") {
        val list = FList(1, 2, 3, 4, 5)
        val nil = FList<Nothing>()
        println(list)
        println(nil)

        dropWhileIndexed(l = list) { index, value ->
            println("list[$index] = $value")
            value < 3
        }
    }
})