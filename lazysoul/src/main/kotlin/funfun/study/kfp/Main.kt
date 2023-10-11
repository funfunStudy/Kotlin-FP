package funfun.study.kfp

import ch3.List
import ch3.List.Companion.reverse
import ch4.Ch4.Either
import ch4.Ch4.Right
import ch4.Ch4.Left
import ch4.Ch4.Either2
import ch4.Ch4.Right2
import ch4.Ch4.Left2
import ch4.Ch4.Some
import ch4.Ch4.None
import ch4.Ch4.catches
import ch4.Ch4.catches2
import ch4.Ch4.sequence
import ch4.Ch4.sequenceViaFoldRight
import ch4.Ch4.sequenceViaFoldRightForEither
import ch4.Ch4.sequenceViaFoldRightForEither2
import ch4.Ch4.sequenceViaTraverse
import ch4.Ch4.traverse
import ch4.Ch4.traverseForEither
import ch4.Ch4.traverseForEither2

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello Lazysoul!")

//        ex4()
//        ex5()
//        ex7()
        ex8()

//        shouldBe Some(List.of("1", "2", "3", "4", "5"))
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

    fun ex8() {
        val xa = List.of("1", "2", "3", "4", "5")
        val xb = List.of("1", "2", "x", "4", "y")

        val result = traverseForEither2(xa) { a ->
            catches2 { Integer.parseInt(a) + 3 }
        }

        val result2 = traverseForEither2(xb) { a ->
            catches2 { Integer.parseInt(a) + 3 }
        }

        println(result)
        println(result2)

        val xe: List<Either2<String, Int>> =
            List.of(Right2(1), Right2(2), Right2(3))

        val xe2: List<Either2<String, Int>> =
            List.of(Right2(1), Left2(List.of("test1")), Right2(3), Left2(List.of("test2")))

        println(sequenceViaFoldRightForEither2(xe))
        // Right(value=Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Nil)))
        println(sequenceViaFoldRightForEither2(xe2))
        // Left(value=boom)
    }
}