package com.kazakago.cleanarchitecture.presentation.common.activity

import android.support.test.rule.ActivityTestRule
import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule

class AboutActivityTest {

    @Rule
    var activityRule = ActivityTestRule(AboutActivity::class.java)

    private lateinit var activity: AboutActivity

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = activityRule.activity
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

}