package az.fanironsource;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.ads.AudienceNetworkAds;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class MainActivity extends AppCompatActivity {

    IronSourceBannerLayout bannerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ------------- Banner IronSource ------------- //

        AudienceNetworkAds.initialize(this);
        IronSource.init(this, "126cbf735");
        IronSource.setMetaData("Facebook_IS_CacheFlag", "IMAGE");
        final FrameLayout bannerContainer = findViewById(R.id.bannerContainer);
        bannerLayout = IronSource.createBanner(this, ISBannerSize.BANNER);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        bannerContainer.addView(bannerLayout, 0, layoutParams);
        bannerLayout.setBannerListener(new BannerListener() {
            @Override
            public void onBannerAdLoaded() {
                Log.d("TAG", "onBannerAdLoaded: Hna banner tloadat");
                bannerLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bannerContainer.removeAllViews();
                    }
                });
                Log.d("TAG", "onBannerAdLoadFailed: Code mzn walkin banner ma tloadatch");
            }

            @Override
            public void onBannerAdClicked() {

            }

            @Override
            public void onBannerAdScreenPresented() {

            }

            @Override
            public void onBannerAdScreenDismissed() {

            }

            @Override
            public void onBannerAdLeftApplication() {

            }
        });
        IronSource.loadBanner(bannerLayout, (String) "DefaultBanner");


        // ------------- Banner IronSource ------------- //

        IronSource.loadInterstitial();
        IronSource.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onInterstitialAdReady() {
                //
            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                Log.d("TAG", "onInterstitialAdLoadFailed: Inter failed to load");
            }

            @Override
            public void onInterstitialAdOpened() {

            }

            @Override
            public void onInterstitialAdClosed() {
                Intent intent = new Intent(MainActivity.this, Page_1.class);
                startActivity(intent);
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
        if (IronSource.isInterstitialPlacementCapped("DefaultInterstitial") && IronSource.isInterstitialReady()) {
            IronSource.showInterstitial("DefaultInterstitial");
        } else {
            Intent intent = new Intent(MainActivity.this, Page_1.class);
            startActivity(intent);
        }

        // Ola t9dar dir hadi bla smit inter nichan lik can m loadi iban
//        if (IronSource.isInterstitialReady()) {
//            IronSource.showInterstitial();
//        }
    }


    @Override
    protected void onDestroy() {
        IronSource.destroyBanner(bannerLayout);
        super.onDestroy();
    }
}