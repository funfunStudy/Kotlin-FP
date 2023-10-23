package ch5

import ch3.Nil
import ch3.List
import ch4.Ch4.Option
import ch4.Ch4.None
import ch4.Ch4.Some
import ch4.Ch4.filter
import ch4.Ch4.flatMap
import ch4.Ch4.getOrElse
import ch4.Ch4.map
import ch4.Ch4.orElse

sealed class Stream<out A>

data class Cons<out A>(
    val head: () -> A,
    val tail: () -> Stream<A>
) : Stream<A>()

object Empty : Stream<Nothing>()

fun <A> Stream<A>.headOption(): Option<A> = when (this) {
    is Empty -> None
    is Cons -> Some(head())
}

fun <A> Stream<A>.isEmpty(): Boolean = when (this) {
    is Empty -> true
    is Cons -> false
}

fun <A> cons(hd: () -> A, tl: () -> Stream<A>): Stream<A> {
    val head: A by lazy(hd)
    val tail: Stream<A> by lazy(tl)
    return Cons({ head }, { tail })
}

fun <A> emptySteam(): Stream<A> = Empty

fun <A> of(vararg xs: A): Stream<A> =
    if (xs.isEmpty()) emptySteam()
    else cons({ xs[0] }, { of(*xs.sliceArray(1..<xs.size)) })

fun <A> Stream<A>.toList(): List<A> = when (this) {
    is Empty -> Nil
    is Cons -> ch3.Cons(head(), tail().toList())
}

//fun <A> Stream<A>.toListViaTailRec(): List<A> {
//    tailrec fun tailrecFun(stream: Stream<A>, acc: List<A>): List<A> = when (stream) {
//        is Empty -> Nil
//        is ch5.Cons -> tailrecFun(stream.tail(), Cons(stream.head(), acc))
//    }
//    return tailrecFun(this, Nil).reverse()
//}

fun <A> Stream<A>.take(n: Int): Stream<A> = when (this) {
    is Empty -> Empty
    is Cons -> if (n == 0) {
        Empty
    } else {
        cons(this.head) { tail().take(n - 1) }
    }
}

fun <A> Stream<A>.drop(n: Int): Stream<A> = when (this) {
    is Empty -> Empty
    is Cons -> if (n == 0) {
        this
    } else {
        tail().drop(n - 1)
    }
}

fun <A> Stream<A>.takeWhile(f: (A) -> Boolean): Stream<A> = when (this) {
    is Empty -> Empty
    is Cons -> if (f(head())) {
        Cons(head) { tail().takeWhile(f) }
    } else {
        Empty
    }
}

fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean = when (this) {
    is Cons -> p(head()) || tail().exists(p)
    else -> false
}

fun <A> Stream<A>.exists2(p: (A) -> Boolean): Boolean =
    foldRight({ false }, { a, acc -> p(a) || acc() })

fun <A, B> Stream<A>.foldRight(z: () -> B, f: (A, () -> B) -> B): B = when (this) {
    is Empty -> z()
    is Cons -> f(head()) {
        tail().foldRight(z, f)
    }
}

fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean = when (this) {
    is Empty -> true
    is Cons -> if (p(head())) {
        tail().forAll(p)
    } else {
        false
    }

}

fun <A> Stream<A>.takeWhileViaFoldRight(p: (A) -> Boolean): Stream<A> =
    foldRight(
        { emptySteam() },
        { head, acc ->
            if (p(head)) {
                cons({ head }, acc)
            } else {
                emptySteam()
            }
        }
    )

fun <A> Stream<A>.headOptionViFoldRight(): Option<A> =
    foldRight(
        { Option.empty() },
        { head, _ ->
            Some(head)
        }
    )

fun <A, B> Stream<A>.map(f: (A) -> B): Stream<B> =
    foldRight(
        { emptySteam() },
        { head, acc ->
            cons({ f(head) }, acc)
        }
    )

fun <A> Stream<A>.filter(f: (A) -> Boolean): Stream<A> =
    foldRight(
        { emptySteam() },
        { head, acc ->
            if (f(head)) {
                cons({ head }, acc)
            } else {
                acc()
            }
        }
    )

fun <A> Stream<A>.append(other: () -> Stream<A>): Stream<A> =
    foldRight(
        { other() },
        { head, acc ->
            cons({ head }, acc)
        }
    )

fun <A> Stream<A>.find(p: (A) -> Boolean): Option<A> =
    filter(p)
        .headOption()

fun <A> constant(a: A): Stream<A> =
    cons({ a }, { constant(a) })


fun from(value: Int): Stream<Int> =
    cons({ value }, { from(value + 1) })

fun <A> unfoldConstant(a: A): Stream<A> =
    unfold(a) { value -> Some(value to value) }

fun unfoldFrom(a: Int): Stream<Int> =
    unfold(a) { value -> Some(value to (value + 1)) }

fun unfoldFibs(): Stream<Int> =
    unfold(0 to 1) { (value, next) -> Some(value to (next to (value + next))) }

fun unfoldOnes(): Stream<Int> =
    unfold(1) { Some(1 to 1) }

fun fibs(): Stream<Int> {
    fun test(num1: Int, num2: Int): Stream<Int> =
        cons({ num1 }, { test(num2, num1 + num2) })
    return test(0, 1)
}

fun <A, S> unfold(z: S, f: (S) -> Option<Pair<A, S>>): Stream<A> =
    when (val value = f(z)) {
        is None -> emptySteam()
        is Some -> cons({ value.get.first }, { unfold(value.get.second, f) })
    }

fun <A, S> unfold2(z: S, f: (S) -> Option<Pair<A, S>>): Stream<A> =
    f(z)
        .map { (value, state) ->
            cons({ value }, { unfold(state, f) })
        }.getOrElse {
            emptySteam()
        }

fun <A, B> Stream<A>.unfoldMap(f: (A) -> B): Stream<B> =
    unfold(this) { stream ->
        when (stream) {
            is Empty -> None
            is Cons -> Some(f(stream.head()) to stream.tail())
        }
    }

fun <A> Stream<A>.unfoldTake(n: Int): Stream<A> =
    unfold(this) { s: Stream<A> ->
        when (s) {
            is Cons ->
                if (n > 0)
                    Some(s.head() to s.tail().take(n - 1))
                else None

            else -> None
        }
    }

fun <A> Stream<A>.unfoldTakeWhile(p: (A) -> Boolean): Stream<A> =
    unfold(this) { s: Stream<A> ->
        when (s) {
            is Cons ->
                if (p(s.head()))
                    Some(s.head() to s.tail())
                else None

            else -> None
        }
    }

fun <A, B, C> Stream<A>.zipWith(that: Stream<B>, f: (A, B) -> C): Stream<C> =
    unfold(this to that) { (thisS, thatS) ->
        when (thisS) {
            is Empty -> None
            is Cons -> when (thatS) {
                is Empty -> None
                is Cons -> Some(f(thisS.head(), thatS.head()) to (thisS.tail() to thatS.tail()))
            }
        }
    }

fun <A, B> Stream<A>.zipAll(that: Stream<B>): Stream<Pair<Option<A>, Option<B>>> =
    unfold(this to that) { (thisS, thatS) ->
        when (thisS) {
            is Empty -> when (thatS) {
                is Empty -> None
                is Cons -> Some((None to Some(thatS.head())) to (emptySteam<A>() to thatS.tail()))
            }

            is Cons -> when (thatS) {
                is Empty -> Some((Some(thisS.head()) to None) to (thisS.tail() to emptySteam()))
                is Cons -> Some((Some(thisS.head()) to Some(thatS.head())) to (thisS.tail() to thatS.tail()))
            }
        }
    }

fun <A> Stream<A>.startsWith(that: Stream<A>): Boolean =
    zipAll(that)
        .takeWhile { (_, thatS) ->
            thatS !is None
        }
        .forAll { it.first == it.second }

fun <A> Stream<A>.tails(): Stream<Stream<A>> =
    unfold(this) { stream ->
        when (stream) {
            is Empty -> None
            is Cons -> Some(stream to stream.tail())
        }
    }

fun <A, B> Stream<A>.scanRight(z: B, f: (A, () -> B) -> B): Stream<B> =
    foldRight({ z to of(z) },
        { a: A, p0: () -> Pair<B, Stream<B>> ->
            val p1: Pair<B, Stream<B>> by lazy { p0() }
            val b2: B = f(a) { p1.first }
            Pair(b2, cons({ b2 }, { p1.second }))
        }).second