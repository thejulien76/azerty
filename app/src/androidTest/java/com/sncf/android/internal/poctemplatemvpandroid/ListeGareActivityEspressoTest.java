package com.sncf.android.internal.poctemplatemvpandroid;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.view.ListeGareActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import android.support.test.espresso.intent.Intents;


@RunWith(AndroidJUnit4.class)
public class ListeGareActivityEspressoTest {

    @Rule
    public ActivityTestRule<ListeGareActivity> mActivityRule = new ActivityTestRule<>(ListeGareActivity.class);
    private EspressoTestUtils espressoTestUtils;

    @Before
    public void setup() {
        espressoTestUtils = new EspressoTestUtils();
    }

    @Test
    public void ensureTextChangesWork() {

        Intents.init();

        onView(isRoot()).perform(espressoTestUtils.waitFor(2000));
        onView(withId(R.id.listViewGares)).check(matches(isDisplayed()));
        onView(withId(R.id.listViewGares)).check(ViewAssertions.matches(espressoTestUtils.withListSize(10)));
        onData(anything()).inAdapterView(withId(R.id.listViewGares)).atPosition(0).perform(click());
        intended(toPackage("com.google.android.apps.maps"));
        Intents.release();
    }
}
