package com.example.android.quakereport;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

/**
 * Created by kvaib on 17-10-2017.
 */

public class IntroActivity extends MaterialIntroActivity {

   @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.slide1_bg)
                        .buttonsColor(R.color.slide1_btn)
                        .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                        .title("Welcome")
                        .description("To the Quake Report app")
                        .build());

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.slide2_bg)
                        .buttonsColor(R.color.slide2_btn)
                        .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                        .title("Accurate and precise")
                        .description("No data error in these earthquake lists")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("Straight from the USGS servers.");
                    }
                }, "Data source?")


        );

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.slide3_bg)
                        .buttonsColor(R.color.slide3_btn)
                        .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                        .title("Dive in!")
                        .description("Checkout the earthquake details with care and inform your friends and community!")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("We donot joke :')");
                    }
                }, "Nothing here")


        );

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.slide4_bg)
                        .buttonsColor(R.color.slide4_btn)
                        .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                        .title("Big hug!")
                        .description("Thank you for following up the app tutorial")
                        .build());
        
        enableLastSlideAlphaExitTransition(true);
    }
}
