package com.adu.messi.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.adu.messi.R;
import com.adu.messi.control.NavigateManager;
import com.adu.messi.ui.fragment.HistoryTodayFragment;
import com.adu.messi.ui.fragment.HomeFragment;
import com.adu.messi.ui.fragment.InquireFragment;
import com.adu.messi.ui.fragment.JokeFragment;
import com.adu.messi.ui.fragment.WeiXinFragment;
import com.adu.messi.utils.LogUtils;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences sp;
    private boolean isNight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initData();

    }


    //初始化view
    private void initView() {
        sp = getSharedPreferences("adu", MODE_PRIVATE);
    }


    //初始化数据
    private void initData() {
        //设置toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //首先加载首页
        initPagerContent(new HomeFragment());

        //设置DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //设置NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        //初始化头像，设置点击事件
        ImageView headerImg = (ImageView) navHeaderView.findViewById(R.id.headerImg);
        headerImg.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                LogUtils.showToast("我的头像");
            }
        });
        //点击姓名
        TextView headerText = (TextView) navHeaderView.findViewById(R.id.TextName);
        headerText.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                LogUtils.showToast("我的名字");
            }
        });
        //悬浮按钮控件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_frist){          //首页
            initPagerContent(new HomeFragment());
        }else if (id == R.id.nav_weixin) {      //微信精选
            getSupportActionBar().setTitle("微信精选");
            initPagerContent(new WeiXinFragment());
        } else if (id == R.id.nav_joke) {       //笑话大全
            initPagerContent(new JokeFragment());
        } else if (id == R.id.nav_historytoday) {   //历史上的今天
            initPagerContent(new HistoryTodayFragment());
        } else if (id == R.id.nav_inquire) {        //查询信息
            initPagerContent(new InquireFragment());
        } else if (id == R.id.nav_share) {      //分享

        } else if (id == R.id.nav_send) {       //发送

        } else if (id == R.id.night) {      //模式切换
            //模式切换
            isNight = sp.getBoolean("night", false);
            if (isNight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                sp.edit().putBoolean("night", false).commit();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sp.edit().putBoolean("night", true).commit();
            }
            recreate();
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * 加载fragment
     */
    private void initPagerContent(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        //会话
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.myContent, fragment);
        ft.commit();
    }
}
