package com.theQuake.quakeInfo;

/**
 * Created by kvaib on 17-11-2017.
 */

import android.content.Intent;
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
                .image(R.drawable.ic_insert_emoticon_black_24dp)
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
                        .description("Want to get in-depth quake info? It's simple, tap on each list item to start")
                        .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.slide4_bg)
                .buttonsColor(R.color.slide4_btn)
                .image(R.drawable.ic_code_black_24dp)
                .title("Open \uD83D\uDC50 Source")
                .description("Are you a developer? Want to make some changes to this app? The Quake Info app's source code is listed on GitHub. Feel free to make Pull Requests, Forks or raise a new issue!")
                .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                showMessage("You need to explore the app first!");
            }
        }, "Where's the link?")


        );

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.slide5_bg)
                .buttonsColor(R.color.slide5_btn)
                .image(R.drawable.ic_update_black_24dp)
                .title("We ❤ Updates!")
                .description("We'll keep on updating this app on regular intervals adding features like voice search, more filters, updated interface and more.")
                .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), EarthquakeActivity.class);
                        startActivity(intent);
                    }
                }, "Start exploring")


        );

        enableLastSlideAlphaExitTransition(true);
    }
}
