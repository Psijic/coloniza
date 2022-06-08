package com.psvoid.coloniza.world.city.domain.people

import kotlin.random.Random

/**
 * Параметры(характеристики) от 0 до 10 (кроме образования)
 * @author Psijic_Void
 */
class Attributes {
//        static const DEGREE: Vector.<String> = Vector.<String>(["Нет", "Начальное", "Среднее", "Высшее"]);
//        static const LEVEL: Vector.<String> = Vector.<String>(
//                ["Катастрофично", "Ужасно", "Очень плохо", "Плохо", "Ниже среднего", "Средне", "Выше среднего", "Хорошо", "Превосходно", "Исключительно"]);
//
//        static const PARAMS: Vector.<String> = Vector.<String>(["intellect", "bravery", "leadership"]);

    var education: Byte = 0
    var bravery: Byte = 0
    var leadership: Byte = 0

    var strength: Byte = 0
    var perception: Byte = 0
    var endurance: Byte = 0
    var charisma: Byte = 0
    var intelligence: Byte = 0
    var agility: Byte = 0

    val ATTRIBUTES = arrayOf("intellect", "bravery", "leadership")

    /**
     * Создание параметров
     */
    fun Params() {
//            for each (var str: String in PARAMS)
//            {
//                this[str] = Math.random() * 10;
//                trace(str, LEVEL[this[str]]);
//            }
    }

    /**
     * Generate Average Joe
     */
    fun generateAverage() {
        strength = Random.nextInt(1, 10).toByte()
        perception = Random.nextInt(1, 10).toByte()
        endurance = Random.nextInt(1, 10).toByte()
        charisma = Random.nextInt(1, 10).toByte()
        intelligence = Random.nextInt(1, 10).toByte()
        agility = Random.nextInt(1, 10).toByte()
    }
}


