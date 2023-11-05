package funfun.study.kfp.chap7

import funfun.study.kfp.chap7.Pars.map2
import funfun.study.kfp.chap7.Pars.unit

fun sortPar(parList: Par<List<Int>>): Par<List<Int>> =
    map2(parList, unit(Unit)) { list, _ -> list.sorted() }


fun <ITEM, RESULT> map(pa: Par<ITEM>, block: (ITEM) -> RESULT): Par<RESULT> =
    map2(pa, unit(Unit)) { a, _ ->
        block(a)
    }

fun sortPar2(parList: Par<List<Int>>): Par<List<Int>> =
    map(parList) { it.sorted() }

fun <ITEM, RESULT> parMap(
    list: List<ITEM>,
    block: (ITEM) -> RESULT
): Par<List<RESULT>> {
    val map: List<Par<RESULT>> = list.map(asyncF(block))
    return sequence1(map)
}

val <T> List<T>.head: T
    get() = first()

val <T> List<T>.tail: List<T>
    get() = this.drop(1)

val Nil = listOf<Nothing>()

fun <ITEM> sequence1(ps: List<Par<ITEM>>): Par<List<ITEM>> =
    when (ps) {
        Nil -> unit(Nil)
        else -> map2(
            ps.head,
            sequence1(ps.tail)
        ) { a: ITEM, b: List<ITEM> ->
            listOf(a) + b
        }
    }

fun <ITEM> sequence(ps: List<Par<ITEM>>): Par<List<ITEM>> =
    when {
        ps.isEmpty() -> unit(Nil)
        ps.size == 1 -> map(ps.head) { listOf(it) }
        else -> {
            val l = ps.subList(0, ps.size / 2)
            val r = ps.subList(ps.size / 2, ps.size)
            map2(sequence(l), sequence(r)) { la, lb ->
                la + lb
            }
        }
    }

fun <ITEM> parFilter2(list: List<ITEM>, block: (ITEM) -> Boolean): Par<List<ITEM>> = TODO()
