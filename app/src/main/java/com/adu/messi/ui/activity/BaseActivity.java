package com.adu.messi.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by adu on 2016/10/14.
 */

public class BaseActivity extends AppCompatActivity {

    @Override protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActionBar上显示返回
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //以下代码用于去除阴影
        if (Build.VERSION.SDK_INT >= 21) {
            getSupportActionBar().setElevation(0);
        }
    }
    /**
     * 菜单操作
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
