package funfun.study.kfp

import ch3.List
import ch3.List.Companion.filter
import ch3.List.Companion.map
import ch3.List.Companion.reverse
import ch4.Ch4.Either
import ch4.Ch4.Right
import ch4.Ch4.Left
import ch4.Ch4.Some
import ch4.Ch4.None
import ch4.Ch4.catches
import ch4.Ch4.sequence
import ch4.Ch4.sequenceViaFoldRight
import ch4.Ch4.sequenceViaFoldRightForEither
import ch4.Ch4.sequenceViaTraverse
import ch4.Ch4.traverse
import ch4.Ch4.traverseForEither
import ch5.*
import java.util.stream.Stream
import kotlin.system.exitProcess

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello Lazysoul!")

        ch5_3()
    }

    fun ex4() {
        val list = List.of(Some(10), Some(20), Some(30))
        val list2 = List.of(Some(10), None, Some(30))

        println(sequence(list.reverse()))   // reverse 를 해야되네?
        // Some(get=Cons(head=10, tail=Cons(head=20, tail=Cons(head=30, tail=Nil))))
        println(sequence(list2.reverse()))
        // None

        println(sequenceViaFoldRight(list))   // reverse 필요 없다.
        // Some(get=Cons(head=10, tail=Cons(head=20, tail=Cons(head=30, tail=Nil))))
        println(sequenceViaFoldRight(list2))
        // None

        println(sequenceViaTraverse(list))   // reverse 필요 없다.
        // Some(get=Cons(head=10, tail=Cons(head=20, tail=Cons(head=30, tail=Nil))))
        println(sequenceViaTraverse(list2))
        // None
    }

    fun ex5() {
        val l1 = List.of(1, 2, 3, 4, 5)
        val traverseResult = traverse(l1) { a: Int ->
            Some(a + 10)
        }

        val traverseResult2 = traverse(l1) { a: Int ->
            if (a == 3) {
                None
            } else {
                Some(a + 10)
            }
        }

        println(traverseResult)
        // Some(get=Cons(head=11, tail=Cons(head=12, tail=Cons(head=13, tail=Cons(head=14, tail=Cons(head=15, tail=Nil))))))
        println(traverseResult2)
        // None
    }

    fun ex7() {
        val xa = List.of("1", "2", "3", "4", "5")
        val xb = List.of("1", "2", "x", "4", "5")

        val result = traverseForEither(xa) { a ->
            catches { Integer.parseInt(a) + 3 }
        }

        val result2 = traverseForEither(xb) { a ->
            catches { Integer.parseInt(a) + 3 }
        }

        println(result)
        // Right(value=Cons(head=4, tail=Cons(head=5, tail=Cons(head=6, tail=Cons(head=7, tail=Cons(head=8, tail=Nil))))))
        println(result2)
        // Left(value=java.lang.NumberFormatException: For input string: "x")


        val xe: List<Either<String, Int>> =
            List.of(Right(1), Right(2), Right(3))

        val xe2: List<Either<String, Int>> =
            List.of(Right(1), Left("boom"), Right(3))

        println(sequenceViaFoldRightForEither(xe))
        // Right(value=Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Nil)))
        println(sequenceViaFoldRightForEither(xe2))
        // Left(value=boom)
    }

    fun <A> lazyIf(
        cond: Boolean,
        onTrue: () -> A,
        onFalse: () -> A
    ): A = if (cond) onTrue() else onFalse()

    fun maybeTwice(b: Boolean, i: () -> Int) =
        if (b) i() + i() else 0

    fun maybeTwice2(b: Boolean, i: () -> Int) {
        val j: Int by lazy(i)
        if (b) j + j else 0
    }


    fun ch5_1() {
        List.of(1, 2, 3, 4)
            .map { it + 10 }
            .filter { it % 2 == 0 }
            .map { it * 3 }

        List.of(11, 12, 13, 14)
            .filter { it % 2 == 0 }
            .map { it * 3 }

        List.of(36, 42)

        false && { println("!!"); true }.invoke()    // 아무것도 출력하지 않음

        true || { println("!!"); false }.invoke()    // 아무것도 출력하지 않음

//        val result = if (input.isEmpty()) exitProcess(-1) else input

        val a = 1

        val y = lazyIf((a < 22),
            { println("a") },
            { println("b") }
        )

//        val x = maybeTwice2(true, { println("hi"); 1 + 41})

//        hi
//        val x: Int by lazy { expensiveOp() }    // by 키워드를 사용해 lazy가 반환하는 Lazy<Int>를 x에 바인드함
//
//        fun useit() =
//            if (x > 10) "hi"
//            else if (x == 0) "zero"
//            else ("lo")
    }

    fun ch5_2() {
        val a = ch5.of(1, 2, 3, 4, 5)

        println(a.take(3).toList())
        // Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Nil)))
        println(a.drop(3).toList())
        // Cons(head=4, tail=Cons(head=5, tail=Nil))

        println(a.takeWhile { it <= 3 }.toList())
        // Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Nil)))
    }

    fun ch5_3() {

        val a = ch5.of(1, 2, 3, 4, 5)
        val b = ch5.of(4, 5)

        println((a.map { it + 10 }).toList())
        // Cons(head=11, tail=Cons(head=12, tail=Cons(head=13, tail=Cons(head=14, tail=Cons(head=15, tail=Nil)))))

        println((a.filter { it % 2 == 0 }).toList())
        // Cons(head=2, tail=Cons(head=4, tail=Nil))

        println((a.append { b }.toList()))
        // Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Cons(head=4, tail=Cons(head=5, tail=Cons(head=4, tail=Cons(head=5, tail=Nil)))))))

        val result = of(1, 2, 3, 4)
            .map { it + 10 }
            .filter { it % 2 == 0 }
            .map { it * 3 }
            .toList()

        println(result)
        // Cons(head=36, tail=Cons(head=42, tail=Nil))

        val result2 = cons({ 11 }, { of(2, 3, 4).map { it + 10 } })
            .filter { it % 2 == 0 }
            .map { it * 3 }
            .toList()

        println(result2)
        // Cons(head=36, tail=Cons(head=42, tail=Nil))

        val result3 = of(2, 3, 4)
            .map { it + 10 }
            .filter { it % 2 == 0 }
            .map { it * 3 }
            .toList()

        println(result3)
        // Cons(head=36, tail=Cons(head=42, tail=Nil))


        println(constant(3).take(4).toList())
        println(unfoldConstant(3).take(4).toList())
        // Cons(head=3, tail=Cons(head=3, tail=Cons(head=3, tail=Cons(head=3, tail=Nil))))

        println(from(3).take(4).toList())
        println(unfoldFrom(3).take(4).toList())
        //Cons(head=3, tail=Cons(head=4, tail=Cons(head=5, tail=Cons(head=6, tail=Nil))))

        println(fibs().take(7).toList())
        println(unfoldFibs().take(7).toList())
        // Cons(head=0, tail=Cons(head=1, tail=Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Cons(head=5, tail=Cons(head=8, tail=Nil)))))))


        println(fibs().unfoldMap { it + 10 }.take(3).toList())
        // Cons(head=10, tail=Cons(head=11, tail=Cons(head=11, tail=Nil)))
        println(fibs().unfoldTake(7).toList())
        // Cons(head=0, tail=Cons(head=1, tail=Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Cons(head=5, tail=Cons(head=8, tail=Nil)))))))

        println(of(1, 2, 3).startsWith(of(1, 2))) // true
        println(of(1, 2, 3).startsWith(of(1, 3))) // false
        println(of(1, 2, 3, 4, 5).tails().toList().map { it.toList() }) // false
    }

}