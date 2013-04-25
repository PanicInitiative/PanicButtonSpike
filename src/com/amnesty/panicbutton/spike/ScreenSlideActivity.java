package com.amnesty.panicbutton.spike;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlideActivity extends FragmentActivity {
    private ViewPagerWithoutSwipe viewPager;
    private Button previousButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wizard);

        previousButton = (Button) findViewById(R.id.previous);
        nextButton = (Button) findViewById(R.id.next);

        viewPager = (ViewPagerWithoutSwipe) findViewById(R.id.pager);
        final PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        previousButton.setEnabled(false);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                previousButton.setEnabled(position != 0);
                nextButton.setEnabled(position != (pageAdapter.getCount() - 1));
            }
        });
    }

    public void next(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    public void previous(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    private class PageAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fragments = new ArrayList<Fragment>();

        public PageAdapter(FragmentManager fm) {
            super(fm);
            for (int i = 0; i < 10; i++) {
                fragments.add(new SimpleFragment(i));
            }
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    private class SimpleFragment extends Fragment {
        private int index;

        public SimpleFragment(int index) {
            this.index = index +1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ViewGroup parentViewGroup = (ViewGroup) inflater.inflate(R.layout.test_fragment, container, false);
            TextView textView = (TextView) parentViewGroup.findViewById(R.id.text_field);
            textView.setText("Page " + index);
            return parentViewGroup;
        }
    }
}
