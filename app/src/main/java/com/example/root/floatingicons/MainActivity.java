package com.example.root.floatingicons;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {

    FloatingActionMenu menu;
    String NUMBER = "7431944193";
    FloatingActionButton facebookbtn, whatsappbtn, instaButton, gmailButton, siteButton, youtube;
    public static String FACEBOOK_URL = "https://www.facebook.com/udgam.nitsikkim";
    public static String FACEBOOK_PAGE_ID = "732560150098615";
    public static String YOUTUBE_URL = "https://www.youtube.com/user/NITSikkimUdgam";
    public static String YOUTUBE_PAGE_ID = "UCq-Fj5jknLsUf-MWSy4_brA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facebookbtn = findViewById(R.id.flationg_item_facebook);
        whatsappbtn = findViewById(R.id.floating_item_whatsapp);
        instaButton = findViewById(R.id.floating_item_insta);
        gmailButton = findViewById(R.id.floating_item_mail);
        siteButton = findViewById(R.id.floating_item_site);
        youtube = findViewById(R.id.floating_item_youtube);

        facebookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getApplicationContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        siteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://http://udgam.nitsikkim.ac.in/"));
                startActivity(browserIntent);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=null;
                try {
                    intent =new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.google.android.youtube");
                    intent.setData(Uri.parse(YOUTUBE_URL));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(YOUTUBE_URL));
                    startActivity(intent);
                }
            }
        });

        whatsappbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openWhatsappContact(NUMBER);

            }
        });

        gmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"b160136@nitsikkim.ac.in"});
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        instaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInstagram();

            }
        });


    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    public void openWhatsappContact(String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }

    public void openInstagram() {
        Uri uri = Uri.parse("http://instagram.com/_u/gigihadid");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/gigihadid")));
        }

    }

}
