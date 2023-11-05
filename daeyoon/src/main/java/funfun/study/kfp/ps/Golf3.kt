package funfun.study.kfp.ps

import kotlin.math.*;

class Golf3 {
    fun solution(events: IntArray, limit: Int): Int {
        var max = 1
        val last = events.last()

        for (interval in 2 .. last) {
            var idx = 0
            println("=== interval $interval ===")
            var isSuccess = true
            var time = 0
            while (time < last) {
                time += interval
                println("time = $time")
                var throughput = 0
                println(idx)
                while (idx < events.size && events[idx] < time) {
                    println("event[$idx] = ${events[idx]}")
                    idx++
                    throughput++
                }
                if (throughput > limit) {
                    isSuccess = false
                    break
                }

            }
            println(isSuccess)
            if (isSuccess) max = interval
        }

        return max
    }
}

fun main() {
    val g = Golf3()
    println(g.solution(intArrayOf(3, 7, 8), 1))
    println(g.solution(intArrayOf(3, 7, 8), 2))
    println(g.solution(intArrayOf(1, 2, 3, 4, 5, 6), 1))
    println(g.solution(intArrayOf(1, 2, 3, 4, 5, 6), 2))
    println(g.solution(intArrayOf(3, 5, 8, 9, 12), 1))
}