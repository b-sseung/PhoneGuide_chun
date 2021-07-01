package org.techtown.guide.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.guide.AppConstants;
import org.techtown.guide.keypad_etd.KeypadPage3;
import org.techtown.guide.keypad_jaum.KeypadPage;
import org.techtown.guide.TouchPage;
import org.techtown.guide.R;
import org.techtown.guide.keypad_moum.KeypadPage2;
import org.techtown.guide.scroll.ScrollMain;
import org.techtown.guide.typing.Typing_long;
import org.techtown.guide.typing.Typing_one;
import org.techtown.guide.typing.Typing_short;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    private AdView mAdView;

    public static MainListAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("1818", "onCreate 호출됨");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("메인 화면");
        setSupportActionBar(toolbar);

        if (AppConstants.Level[0] == AppConstants.LEVEL_FAIL){
            Intent intent = new Intent(getApplicationContext(), TouchPage.class);
            startActivity(intent);
        }


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        adapter = new MainListAdapter();
        adapter.addItem(new MainList(AppConstants.Name[0]));
        adapter.addItem(new MainList(AppConstants.Name[1]));
        adapter.addItem(new MainList(AppConstants.Name[2]));
        adapter.addItem(new MainList(AppConstants.Name[3]));
        adapter.addItem(new MainList(AppConstants.Name[4]));
        adapter.addItem(new MainList(AppConstants.Name[5]));
        adapter.addItem(new MainList(AppConstants.Name[6]));
        adapter.addItem(new MainList(AppConstants.Name[7]));


        restoreState();


        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClick(MainListAdapter.ViewHolder holder, View view, int position) {
                int a = position + 1;
                openActivity(a);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        adapterReset();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main){
            closeMenu();
            openActivity(0);
        } else if (id == R.id.nav_touch){
            closeMenu();
            openActivity(1);
        } else if (id == R.id.nav_scroll){
            closeMenu();
            openActivity(2);
        } else if (id == R.id.nav_keypad3){
            closeMenu();
            openActivity(3);
        } else if (id == R.id.nav_keypad){
            closeMenu();
            openActivity(4);
        } else if (id == R.id.nav_keypad2){
            closeMenu();
            openActivity(5);
        } else if (id == R.id.nav_typing_one) {
            closeMenu();
            openActivity(6);
        } else if (id == R.id.nav_typing_s){
            closeMenu();
            openActivity(7);
        } else if (id == R.id.nav_typing_l){
            closeMenu();
            openActivity(8);
        }
        return true;
    }

    public void closeMenu(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void openActivity(int position) {
        Intent intent = null;

        if (position == 0) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
        } else if (position == 1) {
            intent = new Intent(getApplicationContext(), TouchPage.class);
        } else if (position == 2) {
            intent = new Intent(getApplicationContext(), ScrollMain.class);
        } else if (position == 3) {
            intent = new Intent(getApplicationContext(), KeypadPage3.class);
        } else if (position == 4) {
            intent = new Intent(getApplicationContext(), KeypadPage.class);
        } else if (position == 5) {
            intent = new Intent(getApplicationContext(), KeypadPage2.class);
        } else if (position == 6) {
            intent = new Intent(getApplicationContext(), Typing_one.class);
        } else if (position == 7) {
            intent = new Intent(getApplicationContext(), Typing_short.class);
        } else if (position == 8) {
            intent = new Intent(getApplicationContext(), Typing_long.class);
        }
        startActivityForResult(intent, AppConstants.REQUEST_CODE_MAIN);
    }

    public void adapterReset(){
        if (adapter != null){
            Log.d("1818", "adapter 리셋 호출됨");
            adapter.clear();
            for(int i = 0; i < AppConstants.Level.length; i++) {
                adapter.addItem(new MainList(AppConstants.Name[i], AppConstants.Level[i]));
                Log.d("1818", "Level : " + AppConstants.Level[i]);
                Log.d("1818", "adapter : " + adapter.getItem(i).getLearn());
            }
            recyclerView.setAdapter(adapter);
        }
    }

    protected void restoreState(){
        //설정 정보에 저장된 데이터를 복원
        SharedPreferences reData = getSharedPreferences("savedData", MODE_PRIVATE);
        if ((reData != null) && (reData.contains("savedData"))){
            AppConstants.Level[0] = reData.getString("touch", "");
            AppConstants.Level[1] = reData.getString("scroll", "");
            AppConstants.Level[2] = reData.getString("etd", "");
            AppConstants.Level[3] = reData.getString("jaum", "");
            AppConstants.Level[4] = reData.getString("moum", "");
            AppConstants.Level[5] = reData.getString("one", "");
            AppConstants.Level[6] = reData.getString("short", "");
            AppConstants.Level[7] = reData.getString("long", "");
        }
        adapterReset();
    }

    protected void saveState(){
        //현재 입력상자에 입력된 데이터를 저장
        SharedPreferences saveData = getSharedPreferences("savedData", MODE_PRIVATE);
        SharedPreferences.Editor editor = saveData.edit();
        editor.putString("touch", adapter.getItem(0).getLearn());
        editor.putString("scroll", adapter.getItem(1).getLearn());
        editor.putString("etd", adapter.getItem(2).getLearn());
        editor.putString("jaum", adapter.getItem(3).getLearn());
        editor.putString("moum", adapter.getItem(4).getLearn());
        editor.putString("one", adapter.getItem(5).getLearn());
        editor.putString("short", adapter.getItem(6).getLearn());
        editor.putString("long", adapter.getItem(7).getLearn());
        editor.commit();
    }

}
