package funfun.study.kfp.chap4

import kotlin.math.pow

fun mean(xs: List<Double>): Option<Double> =
    if (xs.isEmpty()) {
        None
    } else {
        Some(xs.sum() / xs.size)
    }

fun variance(xs: List<Double>): Option<Double> =
    mean(xs).flatMap { m ->
        mean(xs.map { x -> (x-m).pow(2) })
    }
