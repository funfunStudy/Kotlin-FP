package funfun.study.kfp.chap6

fun double(rng: RNG): Pair<Double, RNG> {
    val (value, next) = nonNegativeInt(rng)
    return (value / (Int.MAX_VALUE.toDouble() + 1)) to next
}