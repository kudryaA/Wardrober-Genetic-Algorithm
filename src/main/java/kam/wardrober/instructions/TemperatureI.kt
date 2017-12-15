package kam.wardrober.instructions

import kam.wardrober.struct.Temperature
import kam.wardrober.struct.clothes.Clothes
import kam.wardrober.struct.clothes.Set

fun solveTemperature(set: Set, temperature: Temperature): Double {
    val top = get(set.topClothes, temperature)
    val head = get(set.headdress, temperature)
    val bottom = get(set.bottomClothes, temperature)
    val foot = get(set.footwear, temperature)
    return (top + head + bottom + foot).toDouble()
}

private fun get(clothes: Clothes?, temperature: Temperature): Int {
    var res = 0
    var countT = 0
    var count = 0
    if (clothes?.temperature != null) {
        if (temperature.isRain && clothes.temperature!!.isRain) {
            res += 30
        }

        val set = HashSet<Int>()
        for (i in Math.round(temperature.l).toInt()..Math.round(temperature.h).toInt())
            set.add(i)


        for (i in Math.round(clothes.temperature!!.l).toInt()..Math.round(clothes.temperature!!.h).toInt()) {
            if (set.contains(i)) {
                res += 15
                countT++
            }
            count++
        }

        res *= (countT / count)
        res = Math.abs(res)
    }


    return res
}
