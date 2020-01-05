package com.pnhphamhieu.ebookreader;

import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static final int REQUEST_CHOOSER = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Gui email", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_openfile, R.id.nav_recents,
                R.id.nav_favorites, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nav_home){
            Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_LONG).show();
                }
                if (destination.getId() == R.id.nav_openfile){
                    Toast.makeText(MainActivity.this, "Đã chọn Open File", Toast.LENGTH_LONG).show();
                }
                if (destination.getId() == R.id.nav_recents){
                    Toast.makeText(MainActivity.this, "Đã chọn Recents", Toast.LENGTH_LONG).show();
                }
                if (destination.getId() == R.id.nav_favorites){
                    Toast.makeText(MainActivity.this, "Đã chọn Favorites", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private Context mContext;

    public void click(View view) throws IOException {
        new MaterialFilePicker()
                .withActivity(MainActivity.this)
                .withRequestCode(1000)
                .withHiddenFiles(false) // Show hidden files and folders
                .start();


    }
    static String filePath;

    public String ReadTxt(String input)
    {
        File fl = new File(input);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fl));
            String line;
            while ((line = br.readLine())!= null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return text.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QuoteBank quoteBank = new QuoteBank(context);
        PDFView View;
        TextView txt_view;
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            View = (PDFView) findViewById(R.id.pdfView);
            View.fromFile(new File(filePath)).load();

            txt_view = (TextView) findViewById(R.id.textview_txt);

            txt_view.setText(ReadTxt(filePath));
            //try {
           //     InputStream is = mContext.getAssets().open(filePath);
        //    } catch (IOException e) {
          //      e.printStackTrace();
        //    }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 1001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "PERMISSION GRANTED!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "PERMISSION NOT GRANTED!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }
    }



}
