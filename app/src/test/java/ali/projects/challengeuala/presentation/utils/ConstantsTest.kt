package ali.projects.challengeuala.presentation.utils

import org.junit.Assert.assertEquals
import org.junit.Test


class ConstantsTest {

    @Test
    fun testToConstants() {
        assertEquals("city", Constants.CITY)
        assertEquals("latitude", Constants.LATITUDE)
        assertEquals("longitude", Constants.LONGITUDE)
        assertEquals(0f, Constants.DEFAULT_ORIENTATION, 0f)
        assertEquals("city_list", Constants.Routes.CITY_LIST_SCREEN)
        assertEquals("city_map", Constants.Routes.CITY_MAP_SCREEN)
        assertEquals("loading", Constants.Routes.LOADING_SCREEN)
    }

}