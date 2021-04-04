package com.news.app.ui

import android.Manifest
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.news.app.R
import com.news.app.ui.articles.ArticleActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ArticleActivityTest {

    companion object {
        private const val ITEM_BELOW_THE_FOLD = 3
    }
    @Rule
    @JvmField var mRuntimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.INTERNET)

    @Rule
    @JvmField var activityActivityTestRule = ActivityTestRule(ArticleActivity::class.java)


    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(withId(R.id.rvArticles)) // scrollTo will fail the test if no item matches.
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("not in the list"))
                )
            )
    }

    @Test
    fun scrollToItemBelowFold() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.rvArticles))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    ITEM_BELOW_THE_FOLD,
                    ViewActions.click()
                )
            )
    }

}