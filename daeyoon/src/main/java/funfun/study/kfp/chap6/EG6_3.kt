package funfun.study.kfp.chap6

fun intDouble(rng: RNG): Pair<Pair<Int, Double>, RNG> {
    val (i, rng1) = rng.nextInt()
    val (d, rng2) = double(rng1)
    return (i to d) to rng2
}

fun doubleInt(rng: RNG): Pair<Pair<Double, Int>, RNG> {
    val (d, rng1) = double(rng)
    val (i, rng2) = rng1.nextInt()
    return (d to i) to rng2
}

fun double3(rng: RNG): Pair<Triple<Double, Double, Double>, RNG> {
    val (d, rng1) = double(rng)
    val (d1, rng2) = double(rng1)
    val (d2, rng3) = double(rng2)
    return Triple(d, d1, d2) to rng3
}
