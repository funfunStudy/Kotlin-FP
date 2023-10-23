package ch4

import ch3.List
import ch3.Cons
import ch3.List.Companion.foldRight
import ch3.Nil
import kotlin.Exception

object Ch4 {
    sealed class Option<out A> {
        companion object {
            fun <A> empty(): Option<A> = None
        }
    }

    data class Some<out A>(val get: A) : Option<A>()
    data object None : Option<Nothing>()


    fun <A, B> Option<A>.map(f: (A) -> B): Option<B> =
        when (this) {
            is None -> None
            is Some -> Some(f(get))
        }

    fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> =
        when (this) {
            is None -> None
            is Some -> f(get)
        }

    fun <A> Option<A>.getOrElse(default: () -> A): A =
        when (this) {
            is None -> default()
            is Some -> get
        }

    fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> =
        when (this) {
            is None -> None
            is Some -> ob()
        }

    fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> =
        when (this) {
            is None -> None
            is Some -> when (f(get)) {
                true -> this
                false -> None
            }
        }

    fun <A, B, C> map2(
        oa: Option<A>,
        ob: Option<B>,
        f: (A, B) -> C
    ): Option<C> =
        oa.flatMap { a ->
            ob.map { b ->
                f(a, b)
            }
        }


    tailrec fun <A> sequence(remains: List<Option<A>>, acc: Option<List<A>> = None): Option<List<A>> = when (remains) {
        is Nil -> acc
        is Cons -> when (val head = remains.head) {
            is None -> None
            is Some -> sequence(remains.tail, Some(Cons(head.get, acc.getOrElse { Nil })))
        }
    }

    fun <A> sequenceViaFoldRight(xs: List<Option<A>>): Option<List<A>> =
        xs.foldRight(Some(Nil)) { value: Option<A>, acc: Option<List<A>> ->
            map2(value, acc) { a1: A, a2: List<A> ->
                Cons(a1, a2)
            }
        }

    fun <A, B> traverse(xs: List<A>, f: (A) -> Option<B>): Option<List<B>> =
        xs.foldRight(Some(Nil)) { value: A, acc: Option<List<B>> ->
            map2(f(value), acc) { a1: B, a2: List<B> ->
                Cons(a1, a2)
            }
        }

    fun <A> sequenceViaTraverse(xs: List<Option<A>>): Option<List<A>> =
        traverse(xs) { optionA: Option<A> ->
            optionA
        }

    sealed class Either<out E, out A>
    data class Left<out E>(val value: E) : Either<E, Nothing>()
    data class Right<out A>(val value: A) : Either<Nothing, A>()

    fun <A> catches(a: () -> A): Either<Exception, A> =
        try {
            Right(a())
        } catch (e: Exception) {
            Left(e)
        }

    fun <E, A, B> Either<E, A>.map(f: (A) -> B): Either<E, B> =
        when (this) {
            is Left -> this
            is Right -> Right(f(value))
        }

    fun <E, A, B> Either<E, A>.flatMap(f: (A) -> Either<E, B>): Either<E, B> =
        when (this) {
            is Left -> this
            is Right -> f(value)
        }

    fun <E, A, B> Either<E, A>.orElse(f: () -> Either<E, A>): Either<E, A> =
        when (this) {
            is Left -> this
            is Right -> f()
        }

    fun <E, A, B, C> map2(
        ea: Either<E, A>,
        eb: Either<E, B>,
        f: (A, B) -> C
    ): Either<E, C> =
        ea.flatMap { a ->
            eb.map { b ->
                f(a, b)
            }
        }


    fun <E, A> sequenceViaFoldRightForEither(xs: List<Either<E, A>>): Either<E, List<A>> =
        xs.foldRight(Right(Nil)) { value: Either<E, A>, acc: Either<E, List<A>> ->
            map2(value, acc) { a1: A, a2: List<A> ->
                Cons(a1, a2)
            }
        }

    fun <E, A, B> traverseForEither(xs: List<A>, f: (A) -> Either<E, B>): Either<E, List<B>> =
        xs.foldRight(Right(Nil)) { value: A, acc: Either<E, List<B>> ->
            map2(f(value), acc) { a1: B, a2: List<B> ->
                Cons(a1, a2)
            }
        }

    fun <E, A, B, C> map2ListError(
        ea: Either<E, A>,
        eb: Either<E, B>,
        f: (A, B) -> C
    ): Either<List<E>, C> = when (ea) {
        is Left -> when (eb) {
            is Left -> Left(List.of(ea.value, eb.value))
            is Right -> Left(List.of(ea.value))
        }

        is Right -> when (eb) {
            is Left -> Left(List.of(eb.value))
            is Right -> Right(f(ea.value, eb.value))
        }
    }
}