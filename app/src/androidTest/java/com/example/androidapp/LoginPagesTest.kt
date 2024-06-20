package com.example.androidapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.androidapp.R // Make sure toimport your R file

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginPagesTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginPageTest() {
        onView(withId(R.id.LoginUserName))
            .perform(replaceText("Sundhar"), closeSoftKeyboard())

        onView(withId(R.id.editTextNumberPassword))
            .perform(replaceText("9999"), closeSoftKeyboard())

        onView(withId(R.id.button)).perform(click())

//        onView(withId(R.id.tvWelcomeHome))
//            .check(matches(withText("Welcome Sundhar")))
    }
}