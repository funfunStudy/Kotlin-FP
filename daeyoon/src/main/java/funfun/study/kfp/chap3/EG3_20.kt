package funfun.study.kfp.chap3

fun <A> filter2(list: List<A>, f: (A) -> Boolean): List<A> =
    flatMap2(list) { i ->
        if (f(i)) {
            List.of(i)
        } else {
            Nil
        }
    }

fun main() {
    val list = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Cons(7, Cons(8, Nil))))))))
    val filteredList = filter2(list) { a ->
        a % 2 == 0
    }

    println(filteredList)
}
