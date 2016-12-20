package com.example.firas.i3s_android.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.firas.i3s_android.activities.BaseActivity;

/**
 * Created by Chmek Firas on 09/09/2015.
 *
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment {

    //region Protected
    protected BaseActivity baseActivity;
    //endregion

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fetchActivity();
    }

    private void fetchActivity() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            baseActivity = (BaseActivity) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setRetainInstance(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchActivity();
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fetchActivity();
    }

    /**
     * Check is fragment run on foreground
     *
     * @return fragment status
     */
    public boolean isFragmentRunning() {
        return isAdded() && !isDetached() && !isHidden() && !isRemoving() && !getActivity().isFinishing();
    }

    /**
     * Specifie if the fragment can go back or not
     *
     * @return <ul><li><u>TRUE</u>: <b>can't go BACK</b></li>
     * <li><u>FALSE</u>: <b>can go BACk</b></li>
     * </ul>
     */
    public abstract boolean onBackPressed();
/*
    protected Toolbar getToolbar() {
        if (baseActivity != null && baseActivity instanceof MainActivity)
            return ((MainActivity) baseActivity).getToolbar();
        return null;
    }

    protected DrawerLayout getDrawerLayout() {
        if (baseActivity != null && baseActivity instanceof MainActivity)
            return ((MainActivity) baseActivity).getDrawerLayout();
        return null;
    }

    protected CoordinatorLayout getCoordinatorLayout() {
        if (baseActivity != null && baseActivity instanceof MainActivity)
            return ((MainActivity) baseActivity).getCoordinatorLayout();
        return null;
    }

    protected AppBarLayout getAppBarLayout() {
        if (baseActivity != null && baseActivity instanceof MainActivity)
            return ((MainActivity) baseActivity).getAppBarLayout();
        return null;
    }

    protected FrameLayout getFrameLayout() {
        if (baseActivity != null && baseActivity instanceof MainActivity)
            return ((MainActivity) baseActivity).getFrameLayout();
        return null;
    }

    protected NavigationView getNavigationView() {
        if (baseActivity != null && baseActivity instanceof MainActivity)
            return ((MainActivity) baseActivity).getNavigationView();
        return null;
    }

    protected MaterialSearchView getSearchView() {
        if (baseActivity != null && baseActivity instanceof MainActivity)
            return ((MainActivity) baseActivity).getSearchView();
        return null;
    }*/
}