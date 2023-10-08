package funfun.study.kfp.chap4

sealed class Either<out ERROR, out RESULT>
data class Left<out ERROR>(val value: ERROR): Either<ERROR, Nothing>()
data class Right<out RESULT>(val value: RESULT): Either<Nothing, RESULT>()

fun <RESULT> catches(a: () -> RESULT): Either<Exception, RESULT> =
    try {
        Right(a())
    } catch (e: Exception) {
        Left(e)
    }

