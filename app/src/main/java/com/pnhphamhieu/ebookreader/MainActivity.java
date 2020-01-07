package com.pnhphamhieu.ebookreader;

import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;


import android.os.Build;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.pnhphamhieu.ebookreader.ui.home.HomeFragment;
import com.pnhphamhieu.ebookreader.ui.home.PageAdapter;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;


import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;


import android.widget.RelativeLayout;


import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    ArrayList<Product> listRecent;
    RecentListViewAdapter recentListViewAdapter;
    ListView listViewRecent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewPager pager = findViewById(R.id.viewpager_home);
//        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
//        // DealListFragment = HomeFragment
//        pageAdapter.add(HomeFragment.newInstance());
//        pageAdapter.add(HomeFragment.newInstance());
//        pager.setAdapter(pageAdapter);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.viewpager_home, HomeFragment.newInstance());
        ft.commit();

//


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

//ủa có mà ta :| tôi đéo professor như ông r

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
    private static final int REQUEST_CHOOSER = 1234;
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

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            //TextView tw2;
            //tw2 = (TextView) findViewById(R.id.textView2);
            //tw2.setText(getFileExtension(filePath));
            if (getFileExtension(filePath).equalsIgnoreCase(".pdf") == true) {
                PDFView View;
                TextView txt_view;
                View = (PDFView) findViewById(R.id.pdfView);
                txt_view = (TextView) findViewById(R.id.textview_txt);
                View.setVisibility(View.VISIBLE);
                txt_view.setVisibility(View.INVISIBLE);
                View.fromFile(new File(filePath)).load();
            }
            else if (getFileExtension(filePath).equalsIgnoreCase(".txt") == true)
            {
                PDFView View;
                TextView txt_view;
                View = (PDFView) findViewById(R.id.pdfView);
                txt_view = (TextView) findViewById(R.id.textview_txt);
                View.setVisibility(View.INVISIBLE);
                txt_view.setVisibility(View.VISIBLE);

                txt_view.setText(ReadTxt(filePath));
            }
            else Toast.makeText(this, "Xin chọn file .PDF hoặc .TXT", Toast.LENGTH_SHORT).show();
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



    // lấy dịnh dạng file
    private String getFileExtension(String filepath) {
        String name = filepath;
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
    private static String getFileName(String filepath) {
        String name = filepath;
        int lastIndexOf = name.lastIndexOf("\\");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public void Getrecentfile()
    {
        //Khoi tao ListProduct
        listRecent = new ArrayList<>();
        listRecent.add(new Product(1, "Iphone 6", 500));
        listRecent.add(new Product(2, "Iphone 7", 700));
        listRecent.add(new Product(3, "Sony Abc", 800));
        listRecent.add(new Product(4, "Samsung XYZ", 900));
        listRecent.add(new Product(5, "SP 5", 500));
        listRecent.add(new Product(6, "SP 6", 700));
        listRecent.add(new Product(7, "SP 7", 800));
        listRecent.add(new Product(8, "SP 8", 900));


        listViewRecent = findViewById(R.id.listrecent);
        recentListViewAdapter = new RecentListViewAdapter(listRecent);
        listViewRecent.setAdapter(recentListViewAdapter);
    }

//ông thong thả. tôi đi đánh răng ăn sáng :\
    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //Cần trả về số phần tử mà ListView hiện thị
            return 0;
        }

        @Override
        public Object getItem(int position) {
            //Cần trả về đối tượng dữ liệu phần tử ở vị trí position
            return null;
        }

        @Override
        public long getItemId(int position) {
            //Trả về một ID liên quan đến phần tử ở vị trí position
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //convertView là View hiện thị phần tử, nếu là null cần tạo mới
            //(có thể nạp từ layout bằng View.inflate)

            //Cuối cùng là gán dữ liệu ở vị trí possition vào View và trả về đối
            //tượng View này
//wait
            return null;
        }
    }

    public  class Product {
        String name;
        int time;
        int docID;

        public Product(int docID, String name, int time) {
            this.name = name;
            this.time = time;
            this.docID = docID;
        }

        public String getName() {
            return name;
        }

        public int getTime() {
            return time;
        }
    }

    public class RecentListViewAdapter extends BaseAdapter {

        //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
        final ArrayList<Product> listRecent;

        RecentListViewAdapter(ArrayList<Product> listProduct) {
            this.listRecent = listProduct;
        }
// đấy
        @Override
        public int getCount() {
            //Trả về tổng số phần tử, nó được gọi bởi ListView
            return listRecent.size();
        }

        @Override
        public Object getItem(int position) {
            //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
            //có chỉ số position trong listProduct
            return listRecent.get(position);
        }

        @Override
        public long getItemId(int position) {
            //Trả về một ID của phần
            return listRecent.get(position).docID;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
            //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
            //Nếu null cần tạo mới

            View viewProduct;
            if (convertView == null) {
                viewProduct = View.inflate(parent.getContext(), R.layout.recent_info, null);
            } else viewProduct = convertView;

            //Bind sữ liệu phần tử vào View
            Product product = (Product) getItem(position);
            ((TextView) viewProduct.findViewById(R.id.idrecent)).setText(String.format("ID = %d", product.docID));
            ((TextView) viewProduct.findViewById(R.id.name)).setText(String.format("Tên tài liệu : %s", product.name));
            ((TextView) viewProduct.findViewById(R.id.time)).setText(String.format("Mở lúc %d", product.time));


            return viewProduct;
        }
    }
}
