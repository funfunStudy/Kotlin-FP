package funfun.study.kfp.chap4

fun <A, B, C> map2_1(a: Option<A>, b: Option<B>, f: (A, B) -> C): Option<C> =
    if (a is Some && b is Some) {
        Some(f(a.get, b.get))
    } else {
        None
    }

fun <A, B, C> map2(oa: Option<A>, ob: Option<B>, f: (A, B) -> C): Option<C> =
    oa.flatMap { a: A ->
        ob.map { b: B ->
            f(a, b)
        }
    }

fun <A, B, C> map2_2(oa: Option<A>, ob: Option<B>, f: (A, B) -> C): Option<C> =
    oa.flatMap { a: A ->
        ob.map { b: B ->
            f(a, b)
        }
    }
