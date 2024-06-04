package com.example.androidgithubactionssample

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.androidgithubactionssample.theme.MyTheme
import com.example.androidgithubactionssample.ui.screen.input.InputScreen
import com.example.androidgithubactionssample.ui.screen.input.InputScreenViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** テキスト入力をし、都市名などが表示されるかテストする */
@RunWith(AndroidJUnit4::class)
@LargeTest
class InputScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        composeTestRule.activityRule.scenario.onActivity { activity ->
            activity.setContent {
                MyTheme {
                    InputScreen(
                        onSearch = { _ -> },
                        viewModel = InputScreenViewModel(),
                    )
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
    }
}
