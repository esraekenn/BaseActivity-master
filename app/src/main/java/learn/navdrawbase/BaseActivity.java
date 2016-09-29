package learn.navdrawbase;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity
        {


    private Toolbar mActionBarToolbar;
    private DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected boolean useToolbar() {
        return true;
    }



    protected boolean useDrawerToggle() {
        return true;
    }




    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        getActionBarToolbar();

        setupNavDrawer();

        navigationController();
    }

    private void navigationController() {

        findViewById(R.id.activity1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }


        });
        findViewById(R.id.activity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }


        });
        findViewById(R.id.activity3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }


        });
        findViewById(R.id.activity4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }


        });
    }

    private void openActivity() {
        createBackStack(new Intent(this, Activity1.class));
    }
            private void openActivity2() {
                createBackStack(new Intent(this, Activity2.class));
            }
            private void openActivity3() {
                createBackStack(new Intent(this, Activity3.class));
            }
            private void openActivity4() {
                createBackStack(new Intent(this, Activity4.class));
            }
    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mActionBarToolbar != null) {

                mActionBarToolbar.setNavigationContentDescription(getResources()
                        .getString(R.string.navdrawer_description_a11y));
                setSupportActionBar(mActionBarToolbar);

                if (useToolbar()) { setSupportActionBar(mActionBarToolbar);
                } else { mActionBarToolbar.setVisibility(View.GONE); }

            }
        }

        return mActionBarToolbar;
    }



    private void setupNavDrawer() {


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout == null) {
            return;
        }

        if( useDrawerToggle()) {
            mToggle = new ActionBarDrawerToggle(
                    this, mDrawerLayout, mActionBarToolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            mDrawerLayout.setDrawerListener(mToggle);
            mToggle.syncState();
        }
        else if(useToolbar() && getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(ContextCompat
                    .getDrawable(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        }


        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void createBackStack(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder builder = TaskStackBuilder.create(this);
            builder.addNextIntentWithParentStack(intent);
            builder.startActivities();
        } else {
            startActivity(intent);
            finish();
        }
    }


}
