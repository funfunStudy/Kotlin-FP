package ch6

import ch3.Cons
import ch3.List
import ch3.List.Companion.foldLeft
import ch3.List.Companion.map
import ch3.Nil

object Ch6Main {
    @JvmStatic
    fun main(args: Array<String>) {
//        test()

        val machine = Machine(true, 10, 10)
        val inputs =
            List.of(Input.Coin, Input.Turn, Input.Coin, Input.Turn, Input.Coin, Input.Turn, Input.Coin, Input.Turn)
        val result = simulatorMachine(inputs).run(machine)
        println(result)

        val result2 = simulatorMachine(List.of(Input.Turn, Input.Turn, Input.Turn)).run(machine)
        println(result2)
        val result3 = simulatorMachine(List.of(Input.Turn, Input.Turn, Input.Turn, Input.Coin)).run(machine)
        println(result3)
        val result4 = simulatorMachine(List.of(Input.Turn, Input.Turn, Input.Turn, Input.Coin, Input.Turn)).run(machine)
        println(result4)
    }

    fun test() {
        val rng = SimpleRNG(42)
        val (n1, rng2) = rng.nextInt()
        println("n1:$n1; rng2:$rng2") // n1:430813; rng2:SimpleRNG(seed=28233813485)
        val (n1_1, rng2_1) = rng.nextInt()
        println("n1_1:$n1_1; rng2_1:$rng2_1") // n1_1:430813; rng2_1:SimpleRNG(seed=28233813485)

        val (n2, rng3) = rng2.nextInt()
        println("n2:$n2; rng3:$rng3")   // n2:928751; rng3:SimpleRNG(seed=60866665460)
        val (n2_1, rng3_1) = rng2_1.nextInt()
        println("n2_1:$n2_1; rng3_1:$rng3_1")   // n2_1:928751; rng3_1:SimpleRNG(seed=60866665460)

        val (n3, rng4) = rng3.nextInt()
        println("n3:$n3; rng4:$rng4")   // n3:481298; rng4:SimpleRNG(seed=31542386415)
        val (n3_1, rng4_1) = rng3_1.nextInt()
        println("n3_1:$n3_1; rng2_2:$rng4_1")   // n3_1:481298; rng4_1:SimpleRNG(seed=31542386415)
    }
}

interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

data class State<S, out A>(val run: (S) -> Pair<A, S>)

fun <S, A> unit(value: A): State<S, A> = State { state ->
    value to state
}

fun <S, A, B> State<S, A>.map(f: (A) -> B): State<S, B> = State { state ->
    val (a, rng2) = run(state)
    f(a) to rng2
}

fun <S, A, B> State<S, A>.flatMap(f: (A) -> State<S, B>): State<S, B> = State { state ->
    val (valueA, stateS) = run(state)
    f(valueA).run(stateS)
}

fun <S, A, B> State<S, A>.mapViaFlatMap(f: (A) -> B): State<S, B> = flatMap { value ->
    unit(f(value))
}

fun <S, A, B, C> map2(ra: State<S, A>, rb: State<S, B>, f: (A, B) -> C): State<S, C> =
    ra.flatMap { valueA ->
        rb.map { valueB ->
            f(valueA, valueB)
        }
    }

//fun <S, A> sequence(fs: List<State<S, A>>): State<S, List<A>> =
//    fs.foldLeft(unit(empty())) { state, value ->
//        map2(value, state) { head, tail ->
//            Cons(head, tail)
//        }
//    }

sealed class Input {
    object Coin : Input()
    object Turn : Input()
}

data class Machine(
    val locked: Boolean,
    val candies: Int,
    val coins: Int
)

fun simulatorMachine(
    inputs: List<Input>
): State<Machine, Pair<Int, Int>> = State { machine ->
    inputs
        .foldLeft(machine) { acc, input ->
            when (input) {
                Input.Coin ->
                    if (!acc.locked || acc.candies == 0) {
                        acc
                    } else {
                        Machine(false, acc.candies, acc.coins + 1)
                    }

                Input.Turn ->
                    if (acc.locked || acc.candies == 0) {
                        acc
                    } else {
                        Machine(true, acc.candies - 1, acc.coins)
                    }
            }
        }
        .run {
            (candies to coins) to this
        }
}

data class SimpleRNG(val seed: Long) : RNG {
    override fun nextInt(): Pair<Int, RNG> {
        val newSeed = (seed * 0x5DEECE66DL + 0xBL) and 0xFFFFFFFFFL
        val nextRng = SimpleRNG(newSeed)
        val n = (newSeed ushr 16).toInt()
        return n to nextRng
    }
}