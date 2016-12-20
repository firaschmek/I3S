package com.example.firas.i3s_android.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.fragments.BaseFragment;
import com.example.firas.i3s_android.utils.DialogUtils;

/**
 * Created by Chmek Firas
 */
public class BaseActivity extends AppCompatActivity {

    private boolean isInFront = false;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isInFront = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        isInFront = true;
    }

    @Override
    protected void onResume() {
        isInFront = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isInFront = false;
        super.onPause();
    }

    protected void onBackClick() {
        if (canBack()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStack();
        } else {
            exitApp();
        }
    }

    /**
     * return the last fragment on the backStack
     *
     * @return Fragment the last fragment
     */
    public BaseFragment getLastFragment() {
        final FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            int lastIndex = fm.getBackStackEntryCount() - 1;
            if (lastIndex < 0)
                lastIndex = 0;
            final String lastTag = fm.getBackStackEntryAt(lastIndex).getName();
            return (BaseFragment) fm.findFragmentByTag(lastTag);
        }
        return null;
    }

    /**
     * Get Fragment by TAG
     *
     * @param Object Fragment Class Name
     * @return
     */
    public Fragment getFragment(Class Object) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.findFragmentByTag(Object.getName());
    }

    /**
     * Return the number of entries currently in the back stack.
     *
     * @return the number of entries.
     */
    public int getBackStackEntryCount() {
        final FragmentManager fm = getSupportFragmentManager();
        if (fm != null)
            return fm.getBackStackEntryCount();
        return 0;
    }

    /**
     * Replace an existing fragment that was added to a container.
     *
     * @param fragment The new fragment to place in the container.
     * @param frameId  Identifier of the container whose fragment(s) are to be replaced.
     */
    public void replaceFragment(Fragment fragment, int frameId) {
        if (!(fragment.getClass().isInstance(getLastFragment())) && isActivityRunning()) {
            final String className = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
//            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            if (!fragment.isAdded()) {
                ft.replace(frameId, fragment, className);
                ft.addToBackStack(className);
                ft.commit();
            } else {
                ft.show(getFragment(fragment.getClass()));
                ft.commit();
            }
        }
    }


    /**
     * Replace an existing fragment that was added to a container.
     *
     * @param fragment The new fragment to place in the container.
     * @param frameId  Identifier of the container whose fragment(s) are to be replaced.
     */
    public void replaceFragmentWithoutAnimation(Fragment fragment, int frameId) {
        if (!(fragment.getClass().isInstance(getLastFragment())) && isActivityRunning()) {
            final String className = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (!fragment.isAdded()) {
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.replace(frameId, fragment, className);
                ft.addToBackStack(className);
                ft.commit();
            } else {
                ft.show(getFragment(fragment.getClass()));
                ft.commit();
            }
        }
    }


    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, R.id.activity_main_FrameLayout);
    }

    public void replaceFragmentWithNoAnimation(Fragment fragment) {
        replaceFragmentWithoutAnimation(fragment, R.id.activity_main_FrameLayout);
    }

    /**
     * Replace an existing fragment without add to stack.
     *
     * @param fragment The new fragment to place in the container.
     * @param frameId  Identifier of the container whose fragment(s) are to be replaced.
     */
    public void replaceFragmentNoAddToStack(Fragment fragment, int frameId, Boolean withAnim) {
        if (!(fragment.getClass().isInstance(getLastFragment()))) {
            final String className = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (withAnim)
//                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                if (!fragment.isAdded()) {
                    ft.replace(frameId, fragment, className);
                    ft.commit();
                } else {
                    ft.show(getFragment(fragment.getClass()));
                    ft.commit();
                }
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.show(getFragment(fragment.getClass()));
            ft.commit();
        }

    }

    public void replaceFragmentNoAddToStack(Fragment fragment, Boolean withAnim) {
        replaceFragmentNoAddToStack(fragment, R.id.activity_main_FrameLayout, withAnim);
    }

    /**
     * Add a fragment to activity state
     *
     * @param fragment The fragment to be added. This fragment must not already be added to the activity.
     * @param frameId  Optional identifier of the container this fragment is to be placed in.
     */
    public void addFragment(Fragment fragment, int frameId) {
        if (!(fragment.getClass().isInstance(getLastFragment()))) {
            final String className = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (!fragment.isAdded()) {
                ft.add(frameId, fragment, className);
                ft.addToBackStack(className);
                ft.commit();
            } else {
                ft.show(getFragment(fragment.getClass()));
                ft.commit();
            }
        }
    }

    public void addFragment(Fragment fragment) {
        addFragment(fragment, R.id.activity_main_FrameLayout);
    }

    /**
     * Add a fragment to activity state
     *
     * @param fragment The fragment to be added. This fragment must not already be added to the activity.
     * @param frameId  Optional identifier of the container this fragment is to be placed in.
     */
    public void addFragmentWithoutAddToStack(Fragment fragment, int frameId) {
        if (!(fragment.getClass().isInstance(getLastFragment()))) {
            final String className = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (!fragment.isAdded()) {
                ft.add(frameId, fragment, className);
                ft.commit();
            } else {
                ft.show(getFragment(fragment.getClass()));
                ft.commit();
            }
        }
    }

    public void addFragmentWithoutAddToStack(Fragment fragment) {
        addFragmentWithoutAddToStack(fragment, R.id.activity_main_FrameLayout);
    }

    public void removeFragment(Fragment fragment) {
        if (isActivityRunning()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }
    }

    /**
     * Clear current stack and replace an existing fragment that was added to a container.
     *
     * @param fragment The new fragment to place in the container.
     * @param frameId  Identifier of the container whose fragment(s) are to be replaced.
     */
    public void addFragmentToClearStack(Fragment fragment, int frameId) {
        // clear current stack fragment before adding new one
        clearFullStack();
        if (isActivityRunning()) {
            final String className = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (!fragment.isAdded()) {
                // add new Fragment
                ft.replace(frameId, fragment, className);
                ft.addToBackStack(className);
                ft.commit();
            } else {
                ft.show(getFragment(fragment.getClass()));
                ft.commit();
            }
        }
    }

    public void addFragmentToClearStack(Fragment fragment) {
        addFragmentToClearStack(fragment, R.id.activity_main_FrameLayout);
    }

    /**
     * Clear all fragment stack
     */
    public void clearFullStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (isActivityRunning() && getBackStackEntryCount() > 0)
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public boolean canBack() {
        return getBackStackEntryCount() > 0;
    }

    /**
     * Check is fragment run on foreground
     *
     * @return fragment status
     */
    public boolean isActivityRunning() {
        return isInFront && !isFinishing();
    }

    /**
     * Method used to exit Application
     */
    public void exitApp() {
        // Exit PopUp
        DialogUtils.showConfirmDialog(this,
                getResources().getString(R.string.app_name),
                getResources().getString(R.string.dialog_fragment_exit_popup_body),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        //  android.os.Process.killProcess(android.os.Process.myPid());
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                },
                R.string.dialog_OK,
                R.string.dialog_CANCEL
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result to the fragment, which will then pass the result to the login
        // button.
        Fragment fragment = getLastFragment();
        if (fragment != null)
            fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            BaseFragment baseFragment = getLastFragment();
            if (baseFragment != null) {
                if (baseFragment.onBackPressed()) {
                    onBackClick();
                } else {
                    exitApp();
                }
            } else {
                exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
  public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public Toolbar getToolbar() {
        return toolbar;
    }
}
