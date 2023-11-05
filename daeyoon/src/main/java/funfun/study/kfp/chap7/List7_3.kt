package funfun.study.kfp.chap7

import funfun.study.kfp.chap7.Pars.fork

//fun sum2(ints: List<Int>): Int =
//    if (ints.size <= 1) {
//        ints.firstOrNull() ?: 0
//    } else {
//        val l = ints.subList(0, ints.size / 2)
//        val r = ints.subList(ints.size / 2, ints.size)
//        val sumL: Par<Int> = lazyUnit { sum2(l) }
//        val sumR: Par<Int> = lazyUnit { sum2(r) }
//        sumL.get + sumR.get
//    }
//
//fun sum3(ints: List<Int>): Par<Int> =
//    if (ints.size <= 1) {
//        lazyUnit { ints.firstOrNull() ?: 0 }
//    } else {
//        val l = ints.subList(0, ints.size / 2)
//        val r = ints.subList(ints.size / 2, ints.size)
//        map2(sum3(l), sum3(r)) { lx: Int, rx: Int -> lx + rx }
//    }
//
//fun sum4(ints: List<Int>): Par<Int> =
//    if (ints.size <= 1) {
//        lazyUnit { ints.firstOrNull() ?: 0 }
//    } else {
//        val l = ints.subList(0, ints.size / 2)
//        val r = ints.subList(ints.size / 2, ints.size)
//        map2(fork { sum4(l) }, fork { sum4(r) }) { lx: Int, rx: Int -> lx + rx }
//    }


