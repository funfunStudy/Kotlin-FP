package funfun.study.kfp.chap6

typealias Rand<A> = (RNG) -> Pair<A, RNG>

// 함수를 반환
val intR: Rand<Int> = { rng -> rng.nextInt() }

fun <A> unit(a: A): Rand<A> = { rng -> a to rng }
fun <A, B> map(s: Rand<A>, f: (A) -> B): Rand<B> = { rng ->
    val (a, rng2) = s(rng)
    f(a) to rng2
}

fun nonNegativeEven(): Rand<Int> =
    map(::nonNegativeInt) { it - (it % 2) }
