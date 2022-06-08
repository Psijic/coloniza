package com.psvoid.coloniza.world.towns.people

import kotlin.random.Random

/**
 * Приоритеты по 10-бальной шкале
 * @author Psijic_Void
 */
open class Priorities {
    var food: Byte = 0
    var housing: Byte = 0
    var religion: Byte = 0
    var healthcare: Byte = 0
    var entertainment: Byte = 0
    var safety: Byte = 0
    var environment: Byte = 0
    var freedom: Byte = 0
    var work: Byte = 0
    var respect: Byte = 0

    //val PRIORITIES = arrayOf(food, housing, religion, healthcare, entertainment, safety, environment, freedom, work, respect)

    /**
     * Создание приоритетов. Высокие и низкие приоритеты чередуются
     */
    fun generate() {
//        PRIORITIES.forEach { it = Random.nextInt(1, 10).toByte() }

        food = Random.nextInt(1, 10).toByte()
        housing = Random.nextInt(1, 10).toByte()
        religion = Random.nextInt(1, 10).toByte()
        healthcare = Random.nextInt(1, 10).toByte()
        entertainment = Random.nextInt(1, 10).toByte()
        safety = Random.nextInt(1, 10).toByte()
        environment = Random.nextInt(1, 10).toByte()
        freedom = Random.nextInt(1, 10).toByte()
        work = Random.nextInt(1, 10).toByte()
        respect = Random.nextInt(1, 10).toByte()

        //--- можно еще добавить 3-4 главных приоритета ---
    }

}
