package funfun.study.kfp.chap3

fun plusEach(list1: List<Int>, list2: List<Int>, result: List<Int>): List<Int> =
    when (list1) {
        is Nil -> {
            when (list2) {
                is Nil -> result
                is Cons -> append(result, list2)
            }
        }

        is Cons -> {
            when (list2) {
                is Nil -> append(result, list1)
                is Cons -> plusEach(list1.tail, list2.tail, append(result, Cons(list1.head + list2.head, Nil)))
            }
        }
    }

fun plusEach2(list1: List<Int>, list2: List<Int>): List<Int> =
    when (list1) {
        is Nil -> Nil
        is Cons -> {
            when (list2) {
                is Nil -> Nil
                is Cons -> Cons(list1.head + list2.head, plusEach2(list1.tail, list2.tail))
            }
        }
    }


fun main() {
    val list1 = Cons(1, Cons(2, Cons(3, Nil)))
    val list2 = Cons(4, Cons(5, Cons(6, Nil)))

    val result = plusEach(Nil, list2, Nil)
    val result2 = plusEach2(Nil, list2)

    println(result)
    println(result2)
}