package funfun.study.kfp.ps

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary.formatNumber

class Golf2 {
    fun solution(k: Int, a: Int, b: Int, init_password: String, times: IntArray): Array<String> {
        val answer = mutableListOf<String>()
        val initNum = init_password.toInt()

        for (n in times) {
            val time = n / k
            val password = getPassword(a, b, initNum, time)
            val result = formatNumber(password)
            answer.add(result)
        }

        return answer.toTypedArray()
    }

    private fun getPassword(a: Int, b: Int, initPassword: Int, time: Int): Int {
        var seed = initPassword
        for (i in 0 until time) {
            seed = (a * seed + b) % 10000
        }
        return seed
    }

    private fun formatNumber(password: Int): String =
        (10000 + password).toString().slice(1..4)
}

fun main() {
    val g = Golf2()
    val result = g.solution(30, 25, 13, "0001", intArrayOf(0, 29, 30, 119, 120))
    println(result.contentToString())
    val result2 = g.solution(1, 1, 1000, "7123", intArrayOf(2, 3, 4))
    println(result2.contentToString())
}