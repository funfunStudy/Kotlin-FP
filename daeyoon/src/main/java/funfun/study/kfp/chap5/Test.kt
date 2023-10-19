package funfun.study.kfp.chap5

import java.util.EmptyStackException

//fun main() {
//    val s = Stream.of(1, 2, 3)
//    println(s)
//
//    val stream = Cons({ 1 }, { Cons({ 2 }, { Cons({ 3 }, { Empty.empty() }) }) })
//
//    val one = FOne()
//    val tail = FCons2()
//    val stream2 = Cons(one, FCons2())
//}
//
//class FOne : Function0<Int> {
//    override fun invoke(): Int {
//        return 1
//    }
//}
//
//class FTwo : Function0<Int> {
//    override fun invoke(): Int {
//        return 2
//    }
//}
//
//class FThree : Function0<Int> {
//    override fun invoke(): Int {
//        return 3
//    }
//}
//
//class FCons2 : Function0<Stream<Int>> {
//    override fun invoke(): Stream<Int> {
//        val two = FTwo()
//        return Cons(two, FCons3())
//    }
//}
//
//class FCons3 : Function0<Stream<Int>> {
//    override fun invoke(): Stream<Int> {
//        val three = FThree()
//        return Cons(three, FEmpty())
//    }
//}
//
//class FEmpty : Function0<Stream<Int>> {
//    override fun invoke(): Stream<Int> {
//        return Empty
//    }
//}

fun main() {
    val x3 = Cons.cons3({ println("create"); 1 }, { Empty })
    val x2 = Cons.cons2({ println("create"); 1 }, { Empty })
    val x = Cons({ println("create"); 1 }, { Empty })
    x2.headOption()
    x2.headOption()
    x2.headOption()
    x2.headOption()
}