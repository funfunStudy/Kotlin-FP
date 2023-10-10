package funfun.study.kfp.chap5

import funfun.study.kfp.chap4.None
import funfun.study.kfp.chap4.Option
import funfun.study.kfp.chap4.Some
import funfun.study.kfp.chap3.List
import funfun.study.kfp.chap3.Nil

fun <ITEM> Stream<ITEM>.headOption(): Option<ITEM> =
    when (this) {
        is Empty -> None
        is Cons -> Some(head())
    }

fun main() {
//    val x = Cons({ println("Cons"); 1}, { Empty })
//    val h1 = x.headOption()
//    val h2 = x.headOption()
//
//    val x2 = Cons.cons({ println("cons"); 1}, { Empty })
//    val h3 = x2.headOption()
//    val h4 = x2.headOption()

    val stream = Stream.of(1, 2, 3, 4, 5)
    println(stream)
    println(stream.toList())

    println(stream.take(3).toList())
    println(stream.drop(3).toList())
}

fun <ITEM> Stream<ITEM>.toList(): List<ITEM> =
    when (this) {
        is Empty -> Nil
        is Cons -> funfun.study.kfp.chap3.Cons(head(), tail().toList())
    }