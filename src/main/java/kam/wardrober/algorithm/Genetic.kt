package kam.wardrober.algorithm

import kam.wardrober.struct.ListClothes
import kam.wardrober.struct.Temperature
import kam.wardrober.struct.clothes.Set
import java.util.*
import java.util.stream.Collectors

fun geneticSolve(list: ListClothes, temperature: Temperature): Set? {
    var gen = Generation(temperature)

    for (i in 0..list.maxSize()) {
        gen.list.add(list.get(i))
    }

    var max: Set? = Set(null, null, null, null)
    var count = 0

    while(count < 10) {
        gen = gen.createChildren()
        val cur = gen.getMax()
        count++
        if (cur!= null && max!!.getMark(temperature) < cur.getMark(temperature) ) {
            count = 0
            max = cur
        }
    }

    return max
}

private class Generation(private val temperature: Temperature) {
    var list = ArrayList<Set>()
    private val comparator = kotlin.Comparator<Set> { o1, o2 ->
        (o1.getMark(temperature) - o2.getMark(temperature)).toInt()
    }

    fun createChildren(): Generation {
        list.sortWith(comparator)
        list.reverse()
        val res = Generation(temperature)
        val count = list.size

        val max = getMax()

        for (i in 0 until count / 2) {
            val set1 = Set(max!!.footwear, max.bottomClothes, list[i].topClothes, list[i].headdress)
            res.list.add(set1)

            val set2 = Set(list[i].footwear, list[i].bottomClothes, max.topClothes, max.headdress)
            res.list.add(set2)
        }

        val buf = res.list.stream()
                .distinct()
                .collect(Collectors.toList())
        res.list.clear()
        res.list.addAll(buf)
        return res
    }

    fun getMax(): Set? {
        val res = list.maxWith(comparator)
        return res
    }
}