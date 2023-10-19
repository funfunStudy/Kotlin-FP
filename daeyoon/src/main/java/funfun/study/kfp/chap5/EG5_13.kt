package funfun.study.kfp.chap5

import funfun.study.kfp.chap3.tail
import funfun.study.kfp.chap4.None
import funfun.study.kfp.chap4.Option
import funfun.study.kfp.chap4.Some

fun <ITEM, RESULT> Stream<ITEM>.map2(f: (ITEM) -> RESULT): Stream<RESULT> =
    unfold(this) {
        when (it) {
            is Empty -> None
            is Cons -> {
                Some(f(it.head()) to it.tail())
            }
        }
    }

fun <ITEM, RESULT> Stream<ITEM>.mapFold(f: (ITEM) -> RESULT): Stream<RESULT> =
    foldRight({ Empty.empty() }) { h, t ->
        Cons.cons({ f(h) }, t)
    }

fun <ITEM> Stream<ITEM>.take2(n: Int): Stream<ITEM> =
    unfold(n to this) { (num, item) ->
        if (num == 0) None
        else when (item) {
            is Empty -> None
            is Cons -> Some(item.head() to (num - 1 to item.tail()))
        }
    }

fun <ITEM> Stream<ITEM>.take3(n: Int): Stream<ITEM> =
    unfold(this) { item ->
        when (item) {
            is Empty -> None
            is Cons -> {
                if (n == 0) None
                else Some(item.head() to item.tail().take3(n - 1))
            }
        }
    }

fun <ITEM> Stream<ITEM>.takeWhile3(p: (ITEM) -> Boolean): Stream<ITEM> =
    unfold(this) { item ->
        when (item) {
            is Empty -> None
            is Cons ->
                if (p(item.head())) Some(item.head() to item.tail())
                else None
        }
    }

fun <ITEM1, ITEM2, RESULT> Stream<ITEM1>.zipWith(
    that: Stream<ITEM2>,
    combine: (ITEM1, ITEM2) -> RESULT
): Stream<RESULT> =
    unfold(this to that) { (item1, item2) ->
        when (item1) {
            is Empty -> None
            is Cons -> when (item2) {
                is Empty -> None
                is Cons -> Some(combine(item1.head(), item2.head()) to (item1.tail() to item2.tail()))
            }
        }
    }

fun <ITEM1, ITEM2> Stream<ITEM1>.zipAll(that: Stream<ITEM2>): Stream<Pair<Option<ITEM1>, Option<ITEM2>>> =
    unfold(this to that) { (item1, item2) ->
        when (item1) {
            is Empty -> when (item2) {
                is Empty -> None
                is Cons -> Some((None to Some(item2.head())) to (Empty.empty<ITEM1>() to item2.tail()))
            }

            is Cons -> when (item2) {
                is Empty -> Some((Some(item1.head()) to None) to (item1.tail() to Empty.empty()))
                is Cons -> Some((Some(item1.head()) to Some(item2.head())) to (item1.tail() to item2.tail()))
            }
        }
    }


fun main() {
    val s = Stream.of(1, 2, 3, 4, 5)
    println(s)

    println(s.map2 {
        it + 1
    }.toList())
    println(s.mapFold { it + 1 }.toList())

//    println()
//    println(s.take2(3).toList())
//    println(s.take3(3).toList())
//    println(s.takeWhile3 { it <= 3 }.toList())
//
//
//    val i1 = Stream.of(1, 2, 3)
//    val i2 = Stream.of(3, 2, 1)
//    println(
//        i1.zipWith(i2) { a, b ->
//            a + b
//        }.toList()
//    )
}