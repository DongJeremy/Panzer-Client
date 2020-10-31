package org.cloud.panzer.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DetailPagerAdapter extends FragmentPagerAdapter {
    private View view;

    public int getCount() {
        return 0;
    }

    public Fragment getItem(int i) {
        return null;
    }

    public DetailPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        if (obj instanceof View) {
            this.view = (View) obj;
        } else if (obj instanceof Fragment) {
            this.view = ((Fragment) obj).getView();
        }
    }

    public View getPrimaryItem() {
        return this.view;
    }
}
