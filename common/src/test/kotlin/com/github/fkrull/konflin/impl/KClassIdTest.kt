package com.github.fkrull.konflin.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class KClassIdTest {
    @Test
    fun id_should_return_equal_KClassId_with_equal_hashCode_for_the_same_KClass_object() {
        val id1 = Int::class.id
        val id2 = Int::class.id

        assertEquals(id1, id2)
        assertEquals(id1.hashCode(), id2.hashCode())
    }

    @Test
    fun id_should_return_unequal_KClassId_with_unequal_hashCode_for_different_KClass_objects() {
        val id1 = Int::class.id
        val id2 = Long::class.id

        assertNotEquals(id1, id2)
        assertNotEquals(id1.hashCode(), id2.hashCode())
    }
}
