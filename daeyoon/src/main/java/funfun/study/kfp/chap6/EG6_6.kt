package funfun.study.kfp.chap6

fun <ITEM1, ITEM2, RESULT> map2(
    ra: Rand<ITEM1>,
    rb: Rand<ITEM2>,
    f: (ITEM1, ITEM2) -> RESULT
): Rand<RESULT> = { rng ->
    val (item1, rng1) = ra(rng)
    val (item2, rng2) = rb(rng1)

    f(item1, item2) to rng2
}

fun <ITEM1, ITEM2> both(ra: Rand<ITEM1>, rb: Rand<ITEM2>): Rand<Pair<ITEM1, ITEM2>> =
    map2(ra, rb) { a, b -> a to b }

val intDoubleR: Rand<Pair<Int, Double>> = both(intR, doubleR)
val doubleIntR: Rand<Pair<Double, Int>> = both(doubleR, intR)
