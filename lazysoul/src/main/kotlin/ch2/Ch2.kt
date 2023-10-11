package ch2

import java.sql.DriverManager.println


object Ch2 {
    fun fib(n: Int): Int = when (n) {
        0 -> 0
        1 -> 1
        else -> fib(n - 1) + fib(n - 2)
    }
//    val <T> List<T>.tail: List<T>
//        get() = this.drop(1)
//
//    val <T> List<T>.head: T
//        get() = first()
//    fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean = when {
//        aa.isEmpty() -> true
//        order(aa.head, aa.tail.head) -> isSorted(aa.tail, order)
//        else -> false
//    }
}