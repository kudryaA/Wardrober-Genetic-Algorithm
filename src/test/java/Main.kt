import kam.wardrober.algorithm.geneticSolve
import kam.wardrober.struct.ListClothes
import kam.wardrober.struct.Temperature
import kam.wardrober.struct.clothes.*
import kam.wardrober.struct.clothes.Set
import kam.wardrober.struct.enums.Colors
import kam.wardrober.struct.enums.Target
import java.util.*
import kotlin.collections.ArrayList

private val random = Random()

fun main(args: Array<String>) {
    val list = ListClothes()
    for (i in 0 until 200) {
        val clothes: Clothes?
        var cl = ""
        if (random.nextInt() % 2 == 0) {
            if (random.nextInt() % 2 == 0) {
                clothes = Footwear()
                cl = "footwear"
            }
            else {
                clothes = BottomClothes()
                cl = "bottomClothes"
            }
        } else {
            if (random.nextInt() % 2 == 0) {
                clothes = TopClothes()
                cl = "topClothes"
            }
            else {
                clothes = Headdress()
                cl = "headdress"
            }
        }
        generate(clothes)
        list.add(clothes, cl)
    }

    val temperature = Temperature(-1.0, 4.0)
    temperature.isRain = random.nextBoolean()
    val resGenetic = geneticSolve(list, temperature)
    val resRange = allRange(list, temperature)


    println(resGenetic!!.getMark(temperature).toString() + "\n" + resRange!!.getMark(temperature))
}

private fun generate(clothes: Clothes): Clothes {


    var t1 = random.nextInt() % 50
    val t2 = t1 + 10 - random.nextInt() % 10
    if (random.nextBoolean())
        t1 *= -1

    val max = Math.max(t1, t2).toDouble()
    val min = Math.min(t1, t2).toDouble()

    val temperature = Temperature(min, max)
    temperature.isRain = random.nextBoolean()
    clothes.temperature = temperature

    when(Math.abs(random.nextInt() % 3)) {
        0 -> clothes.target = Target.WALK.value
        1 -> clothes.target = Target.WORK.value
        2 -> clothes.target = Target.PARTY.value
    }
    clothes.comfortable  = Math.abs(random.nextInt() % 10 + 1).toShort()
    val size = random.nextInt() % 7
    clothes.colors = ArrayList()
    val list = ArrayList<String>()
    val buf = Colors.values()
    (0 until size).mapTo(list) { buf[it].value }

    clothes.colors = list

    return clothes
}

fun allRange(list: ListClothes, temperature: Temperature): Set? {
    var max: Set? = Set(null, null, null, null)

    list.bottomClothes.forEach { bottom ->
        list.footwear.forEach { foot ->
            list.headdress.forEach { head ->
                list.topClothes.forEach { top ->
                    val set = Set(foot, bottom,  top, head)
                    if (set.getMark(temperature) > max!!.getMark(temperature))
                        max = set
                }
            }
        }
    }

    return max

}