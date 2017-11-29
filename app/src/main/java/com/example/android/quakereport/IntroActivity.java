package com.android.quakeInfo;

/**
 * Created by kvaib on 17-11-2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.slide1_bg)
                .buttonsColor(R.color.slide1_btn)
                .image(R.mipmap.ic_launcher_round)
                .title("Welcome")
                .description("To the Quake Info app")
                .build());

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.slide2_bg)
                        .buttonsColor(R.color.slide2_btn)
                        .image(agency.tango.materialintroscreen.R.drawable.ic_finish)
                        .title("Accurate and precise")
                        .description("No data error in these earthquake lists")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("Straight from the USGS servers");
                    }
                }, "Data source?")


        );

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.slide3_bg)
                        .buttonsColor(R.color.slide3_btn)
                        .image(agency.tango.materialintroscreen.R.drawable.abc_ic_search_api_material)
                        .title("Dive in!")
                        .description("Want to get in-depth quake info? It's simple, tap on each list to start")
                        .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.slide5_bg)
                .buttonsColor(R.color.slide5_btn)
                .image(agency.tango.materialintroscreen.R.drawable.design_ic_visibility)
                .title("Big hug!")
                .description("Thank you for keeping an \uD83D\uDC40 on the app tutorial")
                .build()


        );
        enableLastSlideAlphaExitTransition(true);
    }
}
