package ch7


fun <A> List<A>.split(num: Int): Pair<List<A>, List<A>> = if (this.size <= num) {
    this to listOf()
} else {
    this.subList(0, num) to this.subList(num, size)
}

fun sum(ints: List<Int>): Int =
    ints.fold(0) { acc, value -> acc + value }

fun sum2(ints: List<Int>): Int =
    if (ints.size <= 1)
        ints.getOrElse(0) { 0 }
    else {
        val (l, r) = ints.split(ints.size / 2)
        sum2(l) + sum2(r)
    }

fun sum3(ints: List<Int>): Int =
    if (ints.size <= 1)
        ints.getOrElse(0) { 0 }
    else {
        val (l, r) = ints.split(ints.size / 2)
        println("l $l")
        println("r $r")
        val sumL = unit { sum3(l) }
        val sumR = unit { sum3(r) }
        println("sumL $sumL")
        println("sumR $sumR")
        sumL.get + sumR.get
    }

data class Par<A>(val get: A)
fun <A> unit(a:() -> A): Par<A> = Par(a())
fun <A> get(a: Par<A>): A = a.get

object CH7 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(sum(listOf(1, 2, 3, 4, 5)))
        println(sum2(listOf(1, 2, 3, 4, 5)))
        println(sum3(listOf(1, 2, 3, 4, 5)))
    }
}