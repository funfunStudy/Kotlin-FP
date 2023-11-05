package funfun.study.kfp.chap7

import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

//fun sum(ints: List<Int>): Int =
//    if (ints.size <= 1) {
//        ints.firstOrNull() ?: 0
//    } else {
//        val l = ints.subList(0, ints.size / 2)
//        val r = ints.subList(ints.size / 2, ints.size)
//        sum(l) + sum(r)
//    }
