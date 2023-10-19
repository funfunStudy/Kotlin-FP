package funfun.study.kfp.chap6

val doubleR: Rand<Double> =
    map(::nonNegativeInt) { value ->
        value / (Int.MAX_VALUE.toDouble() + 1)
    }

fun main() {
    val (value, rng) = doubleR(SimpleRNG(10))
}