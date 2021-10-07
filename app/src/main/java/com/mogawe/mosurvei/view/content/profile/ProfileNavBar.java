package com.mogawe.mosurvei.view.content.profile;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;

public class ProfileNavBar {
    private BaseActivity baseActivity;
    // item menu for history
    private LinearLayout menuHistory;
    private LinearLayout menuFoo;

    public ProfileNavBar(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        setNavbar();
    }

    private void setNavbar() {
        LinearLayout linearLayout = baseActivity.findViewById(R.id.nav_bar);

        // menu history
        menuHistory = linearLayout.findViewById(R.id.menu_history);
        menuHistory.setClickable(true);

        // menu foo
        menuFoo = linearLayout.findViewById(R.id.menu_foo);
        menuFoo.setClickable(true);

        setOnMenuHistorySelected();
        setOnMenuFooSelected();
    }

    private void setOnMenuHistorySelected() {
        menuHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("selected menu history");
                // un-select everything else
                unSelectMenuFoo();

                selectMenuHistory();

                // show HistoryFragment
                HistoryFragment.showFragment(baseActivity);
            }
        });
    }

    private void setOnMenuFooSelected() {
        menuFoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("selected menu foo");
                //un-select everything else
                unSelectMenuHistory();

                selectMenuFoo();
            }
        });
    }

    private void unSelectMenuFoo() {
        ImageView icon = menuFoo.findViewById(R.id.menu_foo_icon);
        TextView title = menuFoo.findViewById(R.id.menu_foo_title);
        System.out.println("un-select menu foo");
        // tinting the icon
        icon.setColorFilter(ContextCompat.getColor(baseActivity, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
        // coloring the title text
        title.setTextColor(baseActivity.getColor(R.color.white));
        // set the item menu color
        menuFoo.setBackground(ContextCompat.getDrawable(baseActivity, R.drawable.round_corner_20dip_red_600));
    }

    private void unSelectMenuHistory() {
        ImageView icon = menuHistory.findViewById(R.id.menu_history_icon);
        TextView title = menuHistory.findViewById(R.id.menu_history_title);
        System.out.println("un-select menu history");
        // tinting the icon
//        icon.setColorFilter(ContextCompat.getColor(baseActivity, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
        icon.setColorFilter(ContextCompat.getColor(baseActivity, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        // coloring the title text
        title.setTextColor(baseActivity.getColor(R.color.white));
        // set the item menu color
        menuHistory.setBackground(ContextCompat.getDrawable(baseActivity, R.drawable.round_corner_20dip_red_600));
    }

    private void selectMenuHistory() {
        ImageView icon = menuHistory.findViewById(R.id.menu_history_icon);
        TextView title = menuHistory.findViewById(R.id.menu_history_title);
        System.out.println("selected menu history");
        // un-select everything else
        unSelectMenuFoo();

        // tinting the icon
//        icon.setColorFilter(ContextCompat.getColor(baseActivity, R.color.red_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        icon.setColorFilter(ContextCompat.getColor(baseActivity, R.color.red_600), android.graphics.PorterDuff.Mode.SRC_IN);
        // coloring the title text
        title.setTextColor(baseActivity.getColor(R.color.red_600));
        // set the item menu color
        menuHistory.setBackground(ContextCompat.getDrawable(baseActivity, R.drawable.round_corner_20dip));
    }

    private void selectMenuFoo() {
        ImageView icon = menuFoo.findViewById(R.id.menu_foo_icon);
        TextView title = menuFoo.findViewById(R.id.menu_foo_title);
        System.out.println("selected menu foo");
        //un-select everything else
        unSelectMenuHistory();

        // tinting the icon
        icon.setColorFilter(ContextCompat.getColor(baseActivity, R.color.red_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        // coloring the title text
        title.setTextColor(baseActivity.getColor(R.color.red_600));
        // set the item menu color
        menuFoo.setBackground(ContextCompat.getDrawable(baseActivity, R.drawable.round_corner_20dip));
    }
}
