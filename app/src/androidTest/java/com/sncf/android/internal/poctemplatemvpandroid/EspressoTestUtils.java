package com.sncf.android.internal.poctemplatemvpandroid;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.ListView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

class EspressoTestUtils {

    /**
     * Perform action of waiting for a specific time.
     */
    ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }

    Matcher<View> withListSize (final int size) {
        return new ListeSizeMatcher(size);
    }

    class ListeSizeMatcher extends TypeSafeMatcher<View> {

        private int sizeActual;
        private int sizeExpected;

        ListeSizeMatcher(int sizeExpected) {
            this.sizeExpected = sizeExpected;
        }

        @Override
        protected boolean matchesSafely(View item) {
            sizeActual = ((ListView) item).getCount();
            return sizeActual == sizeExpected;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText ("ListView size expected " + sizeExpected + " actual " + sizeActual);
        }
    }
}
