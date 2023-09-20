package funfun.study.kfp.chap3

fun <A> flatList(list: List<List<A>>): List<A> =
    foldRight(list, Nil as List<A>) { h, t ->
        append(h, t)
    }

fun main() {
    val list1 = Cons(1, Cons(2, Cons(3, Nil)))
    val list2 = Cons(4, Cons(5, Cons(6, Nil)))
    val list3 = Cons(7, Cons(8, Cons(9, Nil)))


    val list = Cons(list1, Cons(list2, Cons(list3, Nil)))

    println(list)
    println(flatList(list))
}