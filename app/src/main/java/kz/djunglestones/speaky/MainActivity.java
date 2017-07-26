package kz.djunglestones.speaky;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import devlight.io.library.ntb.NavigationTabBar;

import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity{
    private static CircleImageView easyCircle,mediumCircle,hardCoreCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        easyCircle = (CircleImageView)findViewById(R.id.easyCircleImageView);
        mediumCircle = (CircleImageView)findViewById(R.id.mediumCircleImageView);
        hardCoreCircle = (CircleImageView)findViewById(R.id.hardCoreCircleImageView);
        initUI();

//        View view1 =getLayoutInflater().inflate(R.layout.item_vp,null);
//        CircleImageView easyCircle1 = (CircleImageView)view1.findViewById(R.id.easyCircleImageView);

//        easyCircle1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),EasyTwisterActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void easyCircleClicked(){
        Intent intent = new Intent(getApplicationContext(),EasyTwisterActivity.class);
        startActivity(intent);
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view.equals(object);
            }

            @Override
            public Object instantiateItem(View container, int position) {
                final View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp, null, false);

                CircleImageView easycircleImageView = (CircleImageView) view.findViewById(R.id.easyCircleImageView);
                easycircleImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        easyCircleClicked();
                    }
                });

//                final View view1 = LayoutInflater.from(
//                        getBaseContext()).inflate(R.layout.item_vp1,null,false);


//                final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
//                txtPage.setText(String.format("Page #%d", position));

                viewPager.addView(view);

                return view;
            }


            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager) container).removeView((View) object);
            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview1);
        final NavigationTabBar navigationTabBar = (NavigationTabBar)findViewById(R.id.nav__tab_bar_horizontal);
        navigationTabBar.setBackgroundColor(Color.parseColor(colors[0]));
        navigationTabBar.setBadgeBgColor(Color.parseColor(colors[0]));
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.mainmenu128),
                        Color.parseColor(colors[3]))
                        .selectedIcon(getResources().getDrawable(R.drawable.mainmenu_active128))
                        .title("Learn")
                        .badgeTitle("to Speak")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.usermale128),
                        Color.parseColor(colors[3]))
                        .selectedIcon(getResources().getDrawable(R.drawable.usermaleselected128))
                        .title("Profile")
                        .badgeTitle("Info")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.train_inactive128),
                        Color.parseColor(colors[3]))
                        .selectedIcon(getResources().getDrawable(R.drawable.train128))
                        .title("Train")
                        .badgeTitle("0")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.settings128),
                        Color.parseColor(colors[3]))
                        .selectedIcon(getResources().getDrawable(R.drawable.settings_selected128))
                        .title("Settings")
                        .badgeTitle("To set")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager,0);

        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                Log.i("TAG","message "+position);

                if (position==1){
                    final View view1 = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_vp1,null,false);
                }

            }

            @Override
            public void onPageSelected(int position) {
                navigationTabBar.getModels().get(position).hideBadge();
                Log.i("TAG","message "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("TAG","message "+state);
            }
        });






    }
}
