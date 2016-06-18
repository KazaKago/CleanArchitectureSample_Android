package com.weathercock.cleanarchitecture.presentation.view.activity;

import android.support.test.rule.ActivityTestRule;

import com.squareup.spoon.Spoon;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * MainActivity Test
 * Created by tamura_k on 2016/06/16.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = activityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInitialState() throws Exception {
        Spoon.screenshot(activity, "initial_state");
    }

}