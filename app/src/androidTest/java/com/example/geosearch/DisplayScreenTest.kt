package com.example.geosearch

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.example.geosearch.navigation.NavigationGraph
import com.example.geosearch.ui.screen.theme.MyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DisplayScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        composeTestRule.activityRule.scenario.onActivity { activity ->
            activity.setContent {
                MyTheme {
                    val navController = rememberNavController()
                    NavigationGraph(navController = navController)
                }
            }
        }
    }

    @Test
    fun verifyCityNameDisplayedCorrectlyWithEspresso() {
        val testText = "Tokyo"

        composeTestRule.onNodeWithTag("textField")
            .performTextInput(testText)

        composeTestRule.onNodeWithTag("textField")
            .assert(hasText(testText))

        composeTestRule.onNodeWithTag("textField")
            .performImeAction()

        composeTestRule.waitUntil(3000) {
            composeTestRule.onNodeWithTag("locationName").isDisplayed()
        }

        composeTestRule.onNodeWithTag("locationName").assert(hasText(testText))
    }
}
