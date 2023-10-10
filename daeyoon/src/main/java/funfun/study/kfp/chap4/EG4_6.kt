package funfun.study.kfp.chap4

fun <ERROR, ITEM, RESULT> Either<ERROR, ITEM>.map(transfer: (ITEM) -> RESULT): Either<ERROR, RESULT> =
    when (this) {
        is Left -> this // Left<ERROR, Nothing>
        is Right -> {
            Right(transfer(value))
        }
    }

fun <ERROR, ITEM, RESULT> Either<ERROR, ITEM>.flatMap(transfer: (ITEM) -> Either<ERROR, RESULT>): Either<ERROR, RESULT> =
    when (this) {
        is Left -> this
        is Right -> {
            transfer(value)
        }
    }


fun <ERROR, ITEM> Either<ERROR, ITEM>.orElse(f: () -> Either<ERROR, ITEM>): Either<ERROR, ITEM> =
    when (this) {
        is Left -> f()
        is Right -> this
    }

fun <ERROR, ITEM1, ITEM2, RESULT> map2(
    e1: Either<ERROR, ITEM1>,
    e2: Either<ERROR, ITEM2>,
    transfer: (ITEM1, ITEM2) -> RESULT
): Either<ERROR, RESULT> =
    e1.flatMap { item1 ->
        e2.map { item2 ->
            transfer(item1, item2)
        }
    }
