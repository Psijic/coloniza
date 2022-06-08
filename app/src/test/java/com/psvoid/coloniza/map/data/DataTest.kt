package com.psvoid.coloniza.map.data

import com.psvoid.coloniza.common.domain.utils.DateUtils
import com.psvoid.coloniza.map.domain.Categories.getCategory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataTest {

    @Test
    fun dateTest() {
        val ts: Long = 1620507600
        assertEquals("2021-05-09 00:00:00", DateUtils.getDateString(ts))
    }

}