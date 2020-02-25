package com.kazakago.cleanarchitecture.domain.model.global.state

import com.os.operando.guild.kt.to
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StateContentZipperTest {

    @Test
    fun zipStoredStored() {
        val stateContent1 = StateContent.Stored(0)
        val stateContent2 = StateContent.Stored(2)
        val zippedStateContent = stateContent1.zip(stateContent2) { content1, content2 -> content1 to content2 }
        assertThat(zippedStateContent, `is`(instanceOf(StateContent.Stored::class.java)))
        assertThat((zippedStateContent as StateContent.Stored).rawContent.first, `is`(0))
        assertThat(zippedStateContent.rawContent.second, `is`(2))
    }

    @Test
    fun zipStoredNotStored() {
        val stateContent1 = StateContent.Stored(0)
        val stateContent2 = StateContent.NotStored<Int>()
        val zippedStateContent = stateContent1.zip(stateContent2) { content1, content2 -> content1 to content2 }
        assertThat(zippedStateContent, `is`(instanceOf(StateContent.NotStored::class.java)))
    }

    @Test
    fun zipNotStoredStored() {
        val stateContent1 = StateContent.NotStored<Int>()
        val stateContent2 = StateContent.Stored(2)
        val zippedStateContent = stateContent1.zip(stateContent2) { content1, content2 -> content1 to content2 }
        assertThat(zippedStateContent, `is`(instanceOf(StateContent.NotStored::class.java)))
    }

    @Test
    fun zipNotStoredNotStored() {
        val stateContent1 = StateContent.NotStored<Int>()
        val stateContent2 = StateContent.NotStored<Int>()
        val zippedStateContent = stateContent1.zip(stateContent2) { content1, content2 -> content1 to content2 }
        assertThat(zippedStateContent, `is`(instanceOf(StateContent.NotStored::class.java)))
    }

    @Test
    fun zipManyStored() {
        val stateContent1 = StateContent.Stored(0)
        val stateContent2 = StateContent.Stored(2)
        val stateContent3 = StateContent.Stored(4)
        val stateContent4 = StateContent.Stored(6)
        val stateContent5 = StateContent.Stored(8)
        val zippedStateContent = stateContent1.zip(stateContent2, stateContent3, stateContent4, stateContent5) { content1, content2, content3, content4, content5 -> content1 to content2 to content3 to content4 to content5 }
        assertThat(zippedStateContent, `is`(instanceOf(StateContent.Stored::class.java)))
        assertThat((zippedStateContent as StateContent.Stored).rawContent.first, `is`(0))
        assertThat(zippedStateContent.rawContent.second, `is`(2))
        assertThat(zippedStateContent.rawContent.third, `is`(4))
        assertThat(zippedStateContent.rawContent.fourth, `is`(6))
        assertThat(zippedStateContent.rawContent.five, `is`(8))
    }

    @Test
    fun zipManyStoredNotStored() {
        val stateContent1 = StateContent.Stored(0)
        val stateContent2 = StateContent.NotStored<Int>()
        val stateContent3 = StateContent.Stored(4)
        val stateContent4 = StateContent.Stored(6)
        val stateContent5 = StateContent.NotStored<Int>()
        val zippedStateContent = stateContent1.zip(stateContent2, stateContent3, stateContent4, stateContent5) { content1, content2, content3, content4, content5 -> content1 to content2 to content3 to content4 to content5 }
        assertThat(zippedStateContent, `is`(instanceOf(StateContent.NotStored::class.java)))
    }

}