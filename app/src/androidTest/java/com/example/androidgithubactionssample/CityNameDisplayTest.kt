package com.example.androidgithubactionssample

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.SearchCondition
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

/** テキスト入力をし、都市名などが表示されるかテストする */
@RunWith(AndroidJUnit4::class)
@LargeTest
class CityNameDisplayTest {
//    @get:Rule
//    val activityRule = ActivityScenarioRule(MainActivity::class.java)
//
//    /** テキスト入力をし、都市名などが表示されるかテストする */
//    @Test
//    fun verifyCityNameDisplayedCorrectly() {
//        // デバイスの言語に応じて都市名を取得する
//        val lang = Locale.getDefault().language
//        val city = if (lang == "ja") "東京" else "Tokyo"
//
//        // InputFragmentでテキストを入力する
//        onView(withContentDescription("Search Input"))
//            .perform(replaceText(city), closeSoftKeyboard())
//            .perform(pressImeActionButton())
//
//        // ResultFragmentに遷移後、都市名が正しく表示されていることを確認する
//        onView(withId(R.id.cityTextView))
//            .waitShown(R.id.cityTextView)
//            .check(matches(withText(city)))
//        // リソースを取得する
//        val resources = InstrumentationRegistry.getInstrumentation().targetContext.resources
//
//        // 緯度が正しく表示されていることを確認する
//        val latitudeString = resources.getString(R.string.latitude)
//        val latExpected = "35.6828387"
//        onView(withId(R.id.latitudeTextView))
//            .check(matches(withText(containsString("$latitudeString$latExpected"))))
//
//        // 経度が正しく表示されていることを確認する
//        val longitudeString = resources.getString(R.string.longitude)
//        val longExpected = "139.7594549"
//        onView(withId(R.id.longitudeTextView))
//            .check(matches(withText(containsString("$longitudeString$longExpected"))))
//    }

    /**
     * 指定したViewが表示されるまで待機する
     * @param resId リソースID
     * @param timeout タイムアウト時間
     * @return ViewInteraction
     */
    private fun ViewInteraction.waitShown(
        @IdRes resId: Int,
        timeout: Long = 5_000,
    ): ViewInteraction {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val bySelector: BySelector = By.res(context.resources.getResourceEntryName(resId))
        val searchCondition: SearchCondition<UiObject2> = Until.findObject(bySelector)
        uiDevice.wait(searchCondition, timeout)
        return this
    }
}
