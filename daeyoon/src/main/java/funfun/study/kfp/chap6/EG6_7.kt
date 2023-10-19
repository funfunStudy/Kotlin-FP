package funfun.study.kfp.chap6

import funfun.study.kfp.chap3.*
import funfun.study.kfp.chap3.List

// Cons(ra, Cons(rb, Nil))
// (raItem
fun <ITEM> sequence(fs: List<Rand<ITEM>>): Rand<List<ITEM>> = { rng ->
    when (fs) {
        is Nil -> Nil to rng
        is Cons -> {
            val (item, rng1) = fs.head(rng)
            val (list, rng2) = sequence(fs.tail)(rng1)
            Cons(item, list) to rng2
        }
    }
}

// Cons(ra, Cons(rb, Nil))
// tail - Nil to rng , head - rb
// Cons(rbItem, Nil to rng) to rng1
// tail - Cons(rbItem, Nil to rng) to rng1, head - ra
// Cons(raItem, Cons(rbItem, Nil to rng)) to rng3
fun <ITEM> sequenceFold(fs: List<Rand<ITEM>>): Rand<List<ITEM>> = { rng ->
    foldRight(fs, Nil to rng) { head: Rand<ITEM>, tail: Pair<List<ITEM>, RNG> ->

        val (item, rng1) = head(rng)
        val (list, _) = tail
        Cons(item, list) to rng1
    }
}

fun ints2(count: Int, rng: RNG): Pair<List<Int>, RNG> {
    fun go(count: Int): List<Rand<Int>> {
        if (count == 0) return Nil
        val (i, _) = rng.nextInt()
        return Cons({ rng -> i to rng }, go(count - 1))
    }
    return sequence(go(count))(SimpleRNG(10))
}