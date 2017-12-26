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
        val cur = gen.getMax()
        count++
        if (cur!= null) {
            if(max!!.getMark(temperature) < cur.getMark(temperature) ) {
                count = 0
                max = cur
            } else {
                gen.mutation(list)
                continue
            }
        }
        gen = gen.createChildren()
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

        val max = list[0]
        res.list.add(max)
        res.list.add(list[1])

        for (i in 1 until count / 2) {
            val set1 = Set(max.footwear, max.bottomClothes, list[i].topClothes, list[i].headdress)
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

    fun mutation(listC: ListClothes) {
        val random = Random()
        list.forEach {
            random.nextInt()
            val choose = Math.abs(random.nextInt()) % 4
            when(choose) {
                0 -> {
                    if (listC.footwear.size > 0) {
                        val i = Math.abs(random.nextInt()) % listC.footwear.size
                        it.footwear = listC.footwear[i]
                    }
                }

                1 -> {
                    if (listC.bottomClothes.size > 0) {
                        val i = Math.abs(random.nextInt()) % listC.bottomClothes.size
                        it.bottomClothes = listC.bottomClothes[i]
                    }
                }

                2 -> {
                    if (listC.topClothes.size > 0) {
                        val i = Math.abs(random.nextInt()) % listC.topClothes.size
                        it.topClothes = listC.topClothes[i]
                    }
                }

                3 -> {
                    if (listC.headdress.size > 0) {
                        val i = Math.abs(random.nextInt()) % listC.headdress.size
                        it.headdress = listC.headdress[i]
                    }
                }
            }
        }
    }
}