package funfun.study.kfp.chap6

import kotlin.math.abs

fun main() {
    println(Int.MAX_VALUE)
    println(Int.MIN_VALUE)
}

fun nonNegativeInt(rng: RNG): Pair<Int, RNG> {
    val cur = rng.nextInt()
    return if (cur.first < 0) {
        if (cur.first == Int.MIN_VALUE) {
            Int.MAX_VALUE to cur.second
        } else {
            abs(cur.first) to cur.second
        }
    } else {
        cur
    }
}

fun nonNegativeInt2(rng: RNG): Pair<Int, RNG> {
    val (value, next) = rng.nextInt()
    return (if (value < 0) -(value + 1) else value) to next
}
