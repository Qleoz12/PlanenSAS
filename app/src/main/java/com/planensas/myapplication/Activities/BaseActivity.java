package com.planensas.myapplication.Activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.planensas.myapplication.MainActivity;
import com.planensas.myapplication.R;
import com.planensas.myapplication.utils.AppVault;
import com.planensas.myapplication.utils.ErrorCenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    private Drawer result;
    private AccountHeader headerResult;
    //tolbar
    @BindView(R.id.toolbar)  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //iconns
        Iconify.with(new FontAwesomeModule());
        toolbar.setTitle(R.string.app_name);
        toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.appColor)));
       //drawable menu
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem()
                                        .withIdentifier(1)
                                        .withName(R.string.LisClient)
                                        .withIcon(R.mipmap.ic_logo_gray_plannensas);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName((R.string.Logs));
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName((R.string.About));



        // Create the AccountHeader
         headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.backgroundlogin)
                .addProfiles(
                        new ProfileDrawerItem()
                                    .withName("Demo User")
                                    .withEmail(new AppVault(BaseActivity.this).getUser())
                                    .withIcon(new IconDrawable(this, FontAwesomeIcons.fa_user))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        //create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item3,
                        item2
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch ((int) drawerItem.getIdentifier())
                        {
                            case 1:
                                startActivity(new Intent(BaseActivity.this,ClientList.class));
                                break;
                            case 2:
                                startActivity(new Intent(BaseActivity.this,ClientList.class));
                            break;
                            case 3:
                                startActivity(new Intent(BaseActivity.this,LogsActivity.class));
                                break;
                            case 0:
                                    if(new AppVault(getBaseContext()).logout())
                                    {
                                        startActivity(new Intent(BaseActivity.this, MainActivity.class));
                                    }
                                    else
                                    {
                                        new ErrorCenter(getApplication()).ShowError("Error de loguot");
                                    }

                                break;
                            default:
                                return true;
                        }
                        return false;
                    }
                })
                .build();
        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Log out").withIdentifier(0));



    }

    public abstract int getLayoutId();


}
