package funfun.study.kfp.chap6

import funfun.study.kfp.chap3.Cons
import funfun.study.kfp.chap3.List
import funfun.study.kfp.chap3.Nil

fun ints(count: Int, rng: RNG): Pair<List<Int>, RNG> {
    if (count == 0) return Nil to rng
    val (i, rng1) = rng.nextInt()
    val (list, rng2) = ints(count - 1, rng1)
    return Cons(i, list) to rng2
}

fun main() {
    val (list, rng) = ints(3, SimpleRNG(2))
    println(list)
}