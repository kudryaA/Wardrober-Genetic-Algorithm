package kam.wardrober.struct

import kam.wardrober.struct.clothes.*
import kam.wardrober.struct.clothes.Set

class ListClothes {
    val footwear = ArrayList<Footwear?>()
    val bottomClothes = ArrayList<BottomClothes?>()
    val topClothes = ArrayList<TopClothes?>()
    val headdress = ArrayList<Headdress?>()

    fun add(clothes: Clothes?, name: String) {
        when(name) {
            "footwear" -> footwear.add(clothes as Footwear)
            "bottomClothes" -> bottomClothes.add(clothes as BottomClothes)
            "topClothes" -> topClothes.add(clothes as TopClothes)
            "headdress" -> headdress.add(clothes as Headdress)
        }
    }

    fun add(footwear: Footwear?) {
        this.footwear.add(footwear)
    }

    fun add(bottomClothes: BottomClothes?) {
        this.bottomClothes.add(bottomClothes)
    }

    fun add(topClothes: TopClothes?) {
        this.topClothes.add(topClothes)
    }

    fun add(headdress: Headdress?) {
        this.headdress.add(headdress)
    }

    fun get(i: Int): Set {
        var foot: Footwear? = null
        var bottom: BottomClothes? = null
        var top: TopClothes? = null
        var head: Headdress? = null
        try {
            foot = footwear[i]
        } catch (e: Exception) { }

        try {
            bottom = bottomClothes[i]
        } catch (e: Exception) { }

        try {
            top = topClothes[i]
        } catch (e: Exception) { }

        try {
            head = headdress[i]
        } catch (e: Exception) { }

        val set = Set(foot, bottom, top, head)
        return  set
    }

    fun maxSize(): Int {
        return Math.max(footwear.size, Math.max(bottomClothes.size, Math.max(topClothes.size, headdress.size)))
    }
}