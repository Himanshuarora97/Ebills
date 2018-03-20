package com.ebills.alphamind.ebills;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ebills.alphamind.ebills.Adapters.ShopPageActivity_HorizontalProductsAdapter;
import com.ebills.alphamind.ebills.Adapters.Shop_Page_Vertical;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.DefaultSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;



public class ShopPageActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    private SliderLayout sliderLayout;
    private RecyclerView horizontal,vertical;
    private RecyclerView.LayoutManager l1,l2;
    private RecyclerView.Adapter a1,a2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shop_page_activity);

        /*

        1. https://www.freelogodesign.org/img/logo-ex-4.png
        2. https://www.freelogodesign.org/img/logo-ex-7.png
        3. http://www.merayarnett.com/wp-content/uploads/2017/11/Awesome-Bussiness-Logos-54-With-Additional-Best-Fonts-For-Logos-with-Bussiness-Logos.jpg
        4. https://www.freelogodesign.org/img/logo-ex-6.png

         */


        // Intialize

        Initialize();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        arrayList.add("https://www.freelogodesign.org/img/logo-ex-7.png");
        arrayList.add("http://www.merayarnett.com/wp-content/uploads/2017/11/Awesome-Bussiness-Logos-54-With-Additional-Best-Fonts-For-Logos-with-Bussiness-Logos.jpg");
        arrayList.add("https://www.freelogodesign.org/img/logo-ex-6.png");

        for (int i=0 ; i<4 ; i++){
            DefaultSliderView sliderView = new DefaultSliderView(this);
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView
                    .image(arrayList.get(i));

            //add your extra information
            sliderView.bundle(new Bundle());
            sliderLayout.addSlider(sliderView);

        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
        sliderLayout.addOnPageChangeListener(this);


        // Horizontal
        horizontal.setNestedScrollingEnabled(false);
        l1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontal.setLayoutManager(l1);
        horizontal.setAdapter(new ShopPageActivity_HorizontalProductsAdapter(ShopPageActivity.this));

        // Vertical
        vertical.setNestedScrollingEnabled(false);
        l2 = new LinearLayoutManager(this);
        vertical.setLayoutManager(l2);
        vertical.setAdapter(new Shop_Page_Vertical(ShopPageActivity.this));

    }

    //Initialize
    private void Initialize(){
        sliderLayout = findViewById(R.id.Offers);
        horizontal = findViewById(R.id.RVHorizontalProducts);
        vertical = findViewById(R.id.RVVerticalProducts);
    }


    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
