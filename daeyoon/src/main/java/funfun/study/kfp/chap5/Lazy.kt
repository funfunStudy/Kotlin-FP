package funfun.study.kfp.chap5

fun maybeTwice2(b: Boolean, i: () -> Int) {
    val j: Int by lazy(i)
    println("before if")
    if (b) j + j else 0
}

fun maybeTwice3(b: Boolean, i: () -> Int) {
    val j: Int = lazy(i).value
    println("before if")
    if (b) j + j else 0
}

fun maybeTwice4(b: Boolean, i: () -> Int) {
    val j: Int = i()
    println("before if")
    if (b) j + j else 0
}

fun maybeTwice5(b: Boolean, i: () -> Int) {
    val j: Lazy<Int> = lazy(i)
    println("before if")
    if (b) j.value + j.value else 0
}

fun main() {
    maybeTwice2(true) {
        println("hi")
        1 + 41
    }
    println()
    maybeTwice3(true) {
        println("hi")
        1 + 41
    }
    println()
    maybeTwice4(true) {
        println("hi")
        1 + 41
    }
    println()
    maybeTwice5(true) {
        println("hi")
        1 + 41
    }
}
