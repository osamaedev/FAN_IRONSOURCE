package az.fanironsource;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.ads.AudienceNetworkAds;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class Page_1 extends AppCompatActivity {
    IronSourceBannerLayout bannerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_1);

        // ------------- Banner IronSource ------------- //

        AudienceNetworkAds.initialize(this);
        IronSource.init(this, "126cbf735");
        IronSource.setMetaData("Facebook_IS_CacheFlag" , "IMAGE");
        final FrameLayout bannerContainer = findViewById(R.id.bannerContainer2);
        IronSourceBannerLayout bannerLayout = IronSource.createBanner(this, ISBannerSize.BANNER);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        bannerContainer.addView(bannerLayout, 0, layoutParams);
        IronSource.loadBanner(bannerLayout, (String) "Home_Screen");

        // ------------- Banner IronSource ------------- //

        IronSource.loadInterstitial();
        IronSource.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onInterstitialAdReady() {


            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

            }

            @Override
            public void onInterstitialAdOpened() {

            }

            @Override
            public void onInterstitialAdClosed() {

            }

            @Override
            public void onInterstitialAdShowSucceeded() {

            }

            @Override
            public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

            }

            @Override
            public void onInterstitialAdClicked() {

            }
        });


    }

    public void onClick(View view) {
        Intent intent = new Intent(Page_1.this, Page_2.class);
        startActivity(intent);
        IronSource.showInterstitial("Home_Screen");

    }
}