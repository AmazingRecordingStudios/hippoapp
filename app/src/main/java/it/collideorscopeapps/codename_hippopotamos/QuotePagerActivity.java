package it.collideorscopeapps.codename_hippopotamos;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import it.collideorscopeapps.codename_hippopotamos.ui.screenslidepager.QuoteFragment;
import it.collideorscopeapps.codename_hippopotamos.ui.screenslidepager.QuoteViewModel;

public class QuotePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    private QuoteViewModel mViewModel;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_slide_pager_activity);

        getData();

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new QuotePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public int getScreenCount() {
        return this.mViewModel.getScreenCount();
    }

    private void getData() {
        ViewModelProvider viewModelProvider = ViewModelProviders.of(this);
        this.mViewModel
                = viewModelProvider.get(QuoteViewModel.class);
    }

    /**
     * A simple pager adapter that represents 5 QuoteFragment objects, in
     * sequence.
     */
    private class QuotePagerAdapter extends FragmentStateAdapter {

        QuotePagerActivity fragActivity;

        public QuotePagerAdapter(QuotePagerActivity fragActivity) {
            super(fragActivity);

            this.fragActivity = fragActivity;
        }

        @Override
        public Fragment createFragment(int position) {

            //TODO
            // code here for switching quote
            // Return a NEW fragment instance in createFragment(int)
            Log.d("QuotePagerAdapter","Creating quoteFragment at " + position);
            Fragment fragment = QuoteFragment.newInstance(position);
            Bundle args = new Bundle();
            args.putInt(QuoteFragment.SCREEN_ID_BUNDLE_FIELD, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return this.fragActivity.getScreenCount();
        }
    }
}
