package funfun.study.kfp.chap3

fun <A> filter(list: List<A>, f: (A) -> Boolean): List<A> =
    foldRight(list, Nil as List<A>) { h, t ->
        if (f(h)) {
            Cons(h, t)
        } else {
            t
        }
    }


fun main() {
    val list = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Cons(7, Cons(8, Nil))))))))
    val filteredList = filter(list) { a ->
        a % 2 == 0
    }

    println(filteredList)
}
