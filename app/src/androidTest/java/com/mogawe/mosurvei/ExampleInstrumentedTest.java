package com.mogawe.mosurvei;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.InstrumentationRegistry;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class ExampleInstrumentedTest {

//    @Rule
//    public ActivityScenarioRule<AlbumActivity> activityRule =
//            new ActivityScenarioRule<>(AlbumActivity.class);
//
//    int  LIST_ITEM_TEST = 4;
//    Album album = AlbumData.albums[LIST_ITEM_TEST];
//
//    @Test
//    public void ensureListAlbumIsPresent() throws Exception {
//        AlbumActivity activity = activityRule.getActivity();
//        View viewById = activity.findViewById(R.id.rvAlbum);
//        assertThat(viewById,notNullValue());
//        assertThat(viewById, instanceOf(RecyclerView.class));
//        RecyclerView recyclerView = (RecyclerView) viewById;
//        AlbumAdapter adapter = recyclerView.getAdapter();
//        assertThat(adapter, instanceOf(RecyclerView.Adapter.class));
//        assertThat(adapter.getCount(), greaterThan(1));
//
//        onView(withId(R.id.rvAlbum)).check(matches(isDisplayed()));
//
//    }
//
//    @Test
//    public void test_selectListAlbumItem_isDetailAlbumVisible() {
//        onView(withId(R.id.rvAlbum))
//                .perform(actionOnItemAtPosition<AlbumViewHolder>(LIST_ITEM_TEST, click()));
//
//        onView(withId(R.id.txvAlbumTitle)).check(matches(withText(album.title)));
//    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mogawe.mosurvei", appContext.getPackageName());
    }
}
