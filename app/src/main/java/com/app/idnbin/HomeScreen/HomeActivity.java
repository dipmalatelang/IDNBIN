package com.app.idnbin.HomeScreen;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.Assets.SymbolListActivity;
import com.app.idnbin.BlankFragment;
import com.app.idnbin.Chat.ChatFragment;
import com.app.idnbin.Customize.CustomizeFragment;
import com.app.idnbin.Customize.CustomizeSymbol;
import com.app.idnbin.MarketAnalysis.MarketAnalysisFragment;
import com.app.idnbin.MovesAlerts.Alerts;
import com.app.idnbin.MovesAlerts.MovesAlertsFragment;
import com.app.idnbin.Portfolio.PortfolioFragment;
import com.app.idnbin.Indicator.IndiActivity;
import com.app.idnbin.Profile.Deposit.DepositActivity;
import com.app.idnbin.Profile.ProfileFragment;
import com.app.idnbin.Profile.Withdraw.WithdrawActivity;
import com.app.idnbin.Tutorial.TutorialFragment;
import com.app.idnbin.R;
import com.app.idnbin.SymbolInfo.SymbolInfoFragment;
import com.app.idnbin.sqlite.SQLiteDatabaseHandler;
import com.app.idnbin.util.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.app.idnbin.util.GlobalConstants.customizeArrayList;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";
    RelativeLayout RLCustomize;
    private ImageView IvTab1, IvTab2, IvTab5, IvMenu, IvTab3, IvTab4, IvTab6, IvGraphTools, IvInfo, IvAddSymbol, IvAlert, IvGraph, img1,
            img2, img3, img4;
    private RelativeLayout RLFrames, RLNavigation, RLBalance, RLLeverage, RLPrice, RLTotal, RLProfit, RLBuy, RLProfitCall,
            RLSpread, RLSell, RLProfitPut, RLOperations;
    private TextView TvDeposit, TvInvestValue, TV_buyclose, TV_buyheading, TV_buyheading1, TV_digitaldowndirection, TV_digitalupdirection, TV_putclosed,
            txt_5_sec, txt_10_sec, txt_15_sec, txt_30_sec, txt_1_min, TvTimeDate, TvLeverage, TV_multipiler, TV_1m, TV_1d, TV_3h, TV_1h, TV_30m, TV_15m, TV_1mm, TvConfirm, TvAlertBuy;
    Button BtnLogin, btnoff, btnspot, btnclosest;

    RecyclerView time_view, recyclerview_symbol;
    String[] data = {"0.6150124100", "291.6465%",};
    private List<String> list = new ArrayList<String>(Arrays.asList(data));

    //AlertDialog balancedialog;
    AlertDialog signin;
    LinearLayout LLDigital, LLDigitalVisible, LLDigitalClose, LLForex, LLForexVisible, LLForexClose, LLStocks, LLStocksVisible, LLStocksClose,
            LLETF, LLETFVisible, LLEtfClose, LLComm, LLCommVisible, LLCommClose, LLCrypto, LLCryptoVisible, LLCryptoClose, LLOperations, RLInvest, RLTime, RLStrikePrice, llbuylayout, lldigitalcalllayout;
    TextView Tv_DigitalSymbol, Tv_ForexSymbol, Tv_StocksSymbol, Tv_EtfSymbol, Tv_CommSymbol, Tv_CryptoSymbol, TVAlertRepeatValue, TVAlertPriceValue,
            TVCurrentPriceValue;

    CardView CVDigital, CVForex, CVStocks, CVETF, CVComm, CVCrypto;

    AlertDialog balancedialog, realaccedialog;
    TextView Tvclosed;

    int currentValue1 = 1;
    int currentValueNew = 0;
    boolean status = false;

    FirebaseUser fuser;
    DatabaseReference databaseReference;
    Alerts alerts;
    SQLiteDatabaseHandler db;
    private ArrayList<SymbolsData> symbolsDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        TvDeposit = findViewById(R.id.TvDeposit);
        IvTab1 = findViewById(R.id.IvTab1);
        IvTab2 = findViewById(R.id.IvTab2);
        IvTab5 = findViewById(R.id.IvTab5);
        IvMenu = findViewById(R.id.IvMenu);
        IvTab3 = findViewById(R.id.IvTab3);
        IvTab4 = findViewById(R.id.IvTab4);
        IvTab6 = findViewById(R.id.IvTab6);
        IvAddSymbol = findViewById(R.id.IvAddSymbol);
        IvInfo = findViewById(R.id.IvInfo);
        RLFrames = findViewById(R.id.RLFrames);
        RLNavigation = findViewById(R.id.RLNavigation);
        RLBalance = findViewById(R.id.RLBalance);
        RLInvest = findViewById(R.id.RLInvest);
        TvInvestValue = findViewById(R.id.TvInvestValue);
        RLLeverage = findViewById(R.id.RLLeverage);
        RLPrice = findViewById(R.id.RLPrice);
        RLTime = findViewById(R.id.RLTime);
        RLStrikePrice = findViewById(R.id.RLStrikePrice);
        RLTotal = findViewById(R.id.RLTotal);
        RLProfit = findViewById(R.id.RLProfit);
        RLBuy = findViewById(R.id.RLBuy);
        RLProfitCall = findViewById(R.id.RLProfitCall);
        RLSpread = findViewById(R.id.RLSpread);
        RLSell = findViewById(R.id.RLSell);
        RLProfitPut = findViewById(R.id.RLProfitPut);
        RLOperations = findViewById(R.id.RLOperations);
        IvAlert = findViewById(R.id.IvAlert);
        IvGraph = findViewById(R.id.IvGraph);
        IvGraphTools = findViewById(R.id.IvGraphTools);
        TvTimeDate = findViewById(R.id.TvTimeDate);
        TV_multipiler = findViewById(R.id.TV_multipiler);
        //CVDigital = findViewById(R.id.CVDigital);
        //LLDigitalVisible = findViewById(R.id.LLDigitalVisible);
        //Tv_DigitalSymbol = findViewById(R.id.Tv_DigitalSymbol);
        /*LLDigitalClose = findViewById(R.id.LLDigitalClose);
        CVForex = findViewById(R.id.CVForex);
        LLForexVisible = findViewById(R.id.LLForexVisible);
        LLForexClose = findViewById(R.id.LLForexClose);
        Tv_ForexSymbol = findViewById(R.id.Tv_ForexSymbol);
        CVStocks = findViewById(R.id.CVStocks);
        LLStocksVisible = findViewById(R.id.LLStocksVisible);
        LLStocksClose = findViewById(R.id.LLStocksClose);
        Tv_StocksSymbol = findViewById(R.id.Tv_StocksSymbol);
        CVETF = findViewById(R.id.CVETF);
        LLETFVisible = findViewById(R.id.LLETFVisible);
        LLEtfClose = findViewById(R.id.LLEtfClose);
        Tv_EtfSymbol = findViewById(R.id.Tv_EtfSymbol);
        CVComm = findViewById(R.id.CVComm);
        LLCommVisible = findViewById(R.id.LLCommVisible);
        LLCommClose = findViewById(R.id.LLCommClose);
        Tv_CommSymbol = findViewById(R.id.Tv_CommSymbol);
        CVCrypto = findViewById(R.id.CVCrypto);
        LLCryptoVisible = findViewById(R.id.LLCryptoVisible);
        LLCryptoClose = findViewById(R.id.LLCryptoClose);*/
        //Tv_CryptoSymbol = findViewById(R.id.Tv_CryptoSymbol);
        LLOperations = findViewById(R.id.LLOperations);
        TvDeposit = findViewById(R.id.TvDeposit);
        TV_buyclose = findViewById(R.id.TV_buyclose);
        llbuylayout = findViewById(R.id.llbuylayout);
        lldigitalcalllayout = findViewById(R.id.lldigitalcalllayout);
        TVAlertRepeatValue = findViewById(R.id.TVAlertRepeatValue);
        TVAlertPriceValue = findViewById(R.id.TVAlertPriceValue);
        TVCurrentPriceValue = findViewById(R.id.TVCurrentPriceValue);
        RLStrikePrice = findViewById(R.id.RLStrikePrice);
        RLBuy = findViewById(R.id.RLBuy);
        RLProfitCall = findViewById(R.id.RLProfitCall);
        RLSell = findViewById(R.id.RLSell);
        RLProfitPut = findViewById(R.id.RLProfitPut);
        RLSpread = findViewById(R.id.RLSpread);
        TvConfirm = findViewById(R.id.TvConfirm);
        TV_buyheading = findViewById(R.id.TV_buyheading);
        TV_buyheading1 = findViewById(R.id.TV_buyheading1);
        TV_digitaldowndirection = findViewById(R.id.TV_digitaldowndirection);
        TV_digitalupdirection = findViewById(R.id.TV_digitalupdirection);
        TV_putclosed = findViewById(R.id.TV_putclosed);
        TvLeverage = findViewById(R.id.TvLeverage);
        TV_1m = findViewById(R.id.TV_1m);
        TV_1d = findViewById(R.id.TV_1d);
        TV_1mm = findViewById(R.id.TV_1mm);
        TV_3h = findViewById(R.id.TV_3h);
        TV_1h = findViewById(R.id.TV_1h);
        TV_30m = findViewById(R.id.TV_30m);
        TV_15m = findViewById(R.id.TV_15m);
        TvAlertBuy = findViewById(R.id.TvAlertBuy);
        recyclerview_symbol = findViewById(R.id.recyclerview_symbol);
        RLCustomize = findViewById(R.id.RLCustomize);

        db = new SQLiteDatabaseHandler(this);
        List<SymbolsData> symbols = db.allSymbols();
        if (symbols != null) {
            Log.d(TAG, "onCreate: "+symbols.size());
            setDataInRecyclerView(symbols);
            Log.d("DATATA",""+symbols.size());

        }

        // alerts
        alerts = new Alerts();
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Android/Alerts").child(fuser.getUid());

        Intent intent = getIntent();
        String info_bundle = intent.getStringExtra("info");
        String alert_bundle = intent.getStringExtra("alert");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        assert info_bundle != null;
        if (info_bundle == null) {
            fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
            clearSelectedTagImg();
        } else {
            fragmentTransaction.replace(R.id.FragmentHolder, new SymbolInfoFragment());
            RLFrames.setVisibility(View.VISIBLE);
            highLightSelectedTagImg(7);
        }
        if (alert_bundle != null) {
            LLOperations.setVisibility(View.GONE);
            RLOperations.setVisibility(View.VISIBLE);
        }
        fragmentTransaction.commit();


        if (!getPref(this, "tabList").equalsIgnoreCase("null")) {
            customizeArrayList = new Gson().fromJson(getPref(this, "tabList"), new TypeToken<ArrayList<CustomizeSymbol>>() {
            }.getType());
        }
        setTabView(customizeArrayList);

        if (getPref(this, "firstinstall").equalsIgnoreCase("null")) {
            setPref(this, "firstinstall", "first");

            setPref(this, "Switch_ExpirationPanel", "0");
            setPref(this, "Switch_TraderSentiments", "0");
            setPref(this, "Switch_LiveDeals", "0");
            setPref(this, "Switch_SharemyLiveDeals", "0");
            setPref(this, "Switch_ClosedDealsonChartOptions", "0");
            setPref(this, "Switch_ClosedDealsonChartFCC", "0");
            setPref(this, "Switch_Sound", "0");
            setPref(this, "Switch_InvestmentAmount", "0");
            setPref(this, "Switch_ShowHighLowonChart", "0");
            setPref(this, "Switch_BuyinOneClickOptions", "0");
            setPref(this, "Switch_BuywithconfirmationForex", "0");
            setPref(this, "Switch_UseBalancetokeepposition", "0");
            setPref(this, "Switch_trailingStop", "0");
            setPref(this, "Switch_ShwoNotificationaboutexecution", "0");
            setPref(this, "Switch_ShowPriceMovements", "0");

            setPref(this, "switch_promo", "0");
            setPref(this, "switch_tournament", "0");
            setPref(this, "switch_system_news", "0");
            setPref(this, "switch_analytical_rpts", "0");

            setPref(this, "switch_communication", "0");

            setPref(this, "switch_closed_trade", "0");
            setPref(this, "switch_succ_withdraw", "0");
            setPref(this, "switch_pending_order", "0");
            setPref(this, "switch_margin_trdng", "0");
            setPref(this, "switch_market_news", "0");
            setPref(this, "switch_sharp_jump", "0");
            setPref(this, "switch_rise_fall", "0");
            setPref(this, "switch_support", "0");
            setPref(this, "switch_price_alerts", "0");
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                updateTime();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();
    }
    private void setDataInRecyclerView(List<SymbolsData> listFilteredData) {

        Log.i("TAG", "setDataInRecyclerView: "+ listFilteredData.size());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerview_symbol.setLayoutManager(linearLayoutManager);
        SymbolAdapter symbolAdapter = new SymbolAdapter(HomeActivity.this, listFilteredData);
        recyclerview_symbol.setAdapter(symbolAdapter);
        symbolAdapter.notifyDataSetChanged();

    }
    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        TvTimeDate.setText(currentDateandTime);

        IvAlert.setOnClickListener(this);
        IvTab1.setOnClickListener(this);
        IvTab2.setOnClickListener(this);
        IvTab3.setOnClickListener(this);
        IvTab4.setOnClickListener(this);
        IvTab5.setOnClickListener(this);
        IvTab6.setOnClickListener(this);
        IvAddSymbol.setOnClickListener(this);
        IvMenu.setOnClickListener(this);
        IvInfo.setOnClickListener(this);
        RLBalance.setOnClickListener(this);
        TvDeposit.setOnClickListener(this);
        RLInvest.setOnClickListener(this);
        RLLeverage.setOnClickListener(this);
        RLPrice.setOnClickListener(this);
        IvGraph.setOnClickListener(this);
        IvGraphTools.setOnClickListener(this);
        TV_buyclose.setOnClickListener(this);
        TV_putclosed.setOnClickListener(this);
        RLTime.setOnClickListener(this);
        TV_1m.setOnClickListener(this);
        TV_1d.setOnClickListener(this);
        TV_3h.setOnClickListener(this);
        TV_1h.setOnClickListener(this);
        TV_30m.setOnClickListener(this);
        TV_15m.setOnClickListener(this);
        TV_1mm.setOnClickListener(this);
        TVAlertRepeatValue.setOnClickListener(this);
        TVAlertPriceValue.setOnClickListener(this);
        TvAlertBuy.setOnClickListener(this);
        IvAlert.setOnClickListener(this);
    }

    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        switch (v.getId()) {
            case R.id.IvAlert:
                setAlertVisibility();
                break;
            case R.id.TVAlertRepeatValue:
                if (status) {
                    TVAlertRepeatValue.setText("ALWAYS");
                    status = false;
                } else {
                    TVAlertRepeatValue.setText("ONCE");
                    status = true;
                }
                break;

            case R.id.TVAlertPriceValue:
                AlertDialog alertprice = new AlertDialog.Builder(this).create();
                alertprice.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertprice.setView(getLayoutInflater().inflate(R.layout.layout_repeatprice, null));
                alertprice.show();
                EditText EtAlertAmount = alertprice.findViewById(R.id.EtAlertAmount);

                int options3 = EtAlertAmount.getImeOptions();
                EtAlertAmount.setImeOptions(options3 | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

                Button BtnPriceminus = alertprice.findViewById(R.id.BtnPriceminus);
                Button BtnPriceinto = alertprice.findViewById(R.id.BtnPriceinto);
                Button BtnPriceplus = alertprice.findViewById(R.id.BtnPriceplus);
                TextView TV_currentvalue = alertprice.findViewById(R.id.TV_currentvalue);
                TextView TV_icclosed = alertprice.findViewById(R.id.TV_icclosed);
                Button Btnconfirm = alertprice.findViewById(R.id.Btnconfirm);

                TV_icclosed.setOnClickListener(v149 -> alertprice.dismiss());

                BtnPriceplus.setOnClickListener(v150 -> {
                    plusValue();
                    EtAlertAmount.setText("0.1" + currentValue1);
                    BtnPriceinto.setVisibility(View.VISIBLE);
                });

                BtnPriceminus.setOnClickListener(v151 -> {
                    minValue();
                    EtAlertAmount.setText("0.1" + currentValue1);
                    BtnPriceinto.setVisibility(View.VISIBLE);
                });

                BtnPriceinto.setOnClickListener(v152 -> {
                    if (TV_currentvalue.getVisibility() == View.VISIBLE) {
                        TV_currentvalue.setVisibility(View.GONE);
                        BtnPriceinto.setVisibility(View.GONE);
                    } else {
                        TV_currentvalue.setVisibility(View.VISIBLE);
                        BtnPriceinto.setVisibility(View.GONE);
                    }

                    TVCurrentPriceValue.setText(EtAlertAmount.getText());
                });
                Btnconfirm.setOnClickListener(v153 -> {
                    TVAlertPriceValue.setText(EtAlertAmount.getText());
                    alertprice.dismiss();
                });

                break;
            case R.id.TV_1m:
                TV_1m.setTextColor(Color.WHITE);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                TV_1mm.setTextColor(Color.GRAY);
                break;
            case R.id.TV_1d:
                TV_1d.setTextColor(Color.WHITE);
                TV_1m.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                TV_1mm.setTextColor(Color.GRAY);
                break;
            case R.id.TV_3h:
                TV_3h.setTextColor(Color.WHITE);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                TV_1mm.setTextColor(Color.GRAY);
                break;
            case R.id.TV_1h:
                TV_1mm.setTextColor(Color.GRAY);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.WHITE);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                break;
            case R.id.TV_30m:
                TV_1mm.setTextColor(Color.GRAY);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.WHITE);
                TV_15m.setTextColor(Color.GRAY);
                break;
            case R.id.TV_15m:
                TV_1mm.setTextColor(Color.GRAY);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.WHITE);
                break;
            case R.id.TV_1mm:
                TV_1mm.setTextColor(Color.WHITE);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                break;

            case R.id.IvGraphTools:
                startActivity(new Intent(HomeActivity.this, IndiActivity.class));
                break;
            case R.id.IvGraph:
                AlertDialog graphdialog = new AlertDialog.Builder(this).create();
                graphdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                graphdialog.setView(getLayoutInflater().inflate(R.layout.layout_chart_type_dialog, null));
                graphdialog.show();
                img1 = graphdialog.findViewById(R.id.img1);
                img2 = graphdialog.findViewById(R.id.img2);
                img3 = graphdialog.findViewById(R.id.img3);
                img4 = graphdialog.findViewById(R.id.img4);
                txt_5_sec = graphdialog.findViewById(R.id.txt_5_sec);
                txt_10_sec = graphdialog.findViewById(R.id.txt_10_sec);
                txt_15_sec = graphdialog.findViewById(R.id.txt_15_sec);
                txt_30_sec = graphdialog.findViewById(R.id.txt_30_sec);
                txt_1_min = graphdialog.findViewById(R.id.txt_1_min);

                TV_icclosed = graphdialog.findViewById(R.id.TV_icclosed);
                TV_icclosed.setOnClickListener(v19 -> graphdialog.dismiss());


                img1.setOnClickListener(v110 -> {
                    highLightImg(1);
                    Toast.makeText(HomeActivity.this, "1", Toast.LENGTH_SHORT).show();
                });
                img2.setOnClickListener(v111 -> {
                    highLightImg(2);
                    Toast.makeText(HomeActivity.this, "2", Toast.LENGTH_SHORT).show();
                });
                img3.setOnClickListener(v112 -> {
                    highLightImg(3);
                    Toast.makeText(HomeActivity.this, "3", Toast.LENGTH_SHORT).show();
                });
                img4.setOnClickListener(v113 -> {
                    highLightImg(4);
                    Toast.makeText(HomeActivity.this, "4", Toast.LENGTH_SHORT).show();
                });

                txt_5_sec.setOnClickListener(v114 -> {
                    highLightText(5);
                    Toast.makeText(HomeActivity.this, "5", Toast.LENGTH_SHORT).show();
                });
                txt_10_sec.setOnClickListener(v115 -> {
                    highLightText(10);
                    Toast.makeText(HomeActivity.this, "10", Toast.LENGTH_SHORT).show();
                });

                txt_30_sec.setOnClickListener(v116 -> {
                    highLightText(30);
                    Toast.makeText(HomeActivity.this, "30", Toast.LENGTH_SHORT).show();
                });
                txt_15_sec.setOnClickListener(v117 -> {
                    highLightText(15);
                    Toast.makeText(HomeActivity.this, "15", Toast.LENGTH_SHORT).show();
                });

                txt_1_min.setOnClickListener(v118 -> {
                    highLightText(1);
                    Toast.makeText(HomeActivity.this, "txt1 min", Toast.LENGTH_SHORT).show();
                });

                break;

            case R.id.RLTime:
                AlertDialog timedialog = new AlertDialog.Builder(this).create();
                timedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                timedialog.setView(getLayoutInflater().inflate(R.layout.layout_timedialog, null));
                timedialog.show();
                time_view = timedialog.findViewById(R.id.time_view);
                time_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                DigitaltimeAdapter digitaltimeAdapter = new DigitaltimeAdapter(getApplicationContext(), (ArrayList<String>) list);
                time_view.setAdapter(digitaltimeAdapter);
                TextView Tv_Off = timedialog.findViewById(R.id.Tv_Off);
                TextView Tv_Closet = timedialog.findViewById(R.id.Tv_Closet);
                TextView Tv_Spot = timedialog.findViewById(R.id.Tv_Spot);
                TV_icclosed = timedialog.findViewById(R.id.TV_icclosed);

                Tv_Off.setOnClickListener(v18 -> startActivity(new Intent(getApplicationContext(), HomeActivity.class)));
                Tv_Closet.setOnClickListener(v15 -> startActivity(new Intent(getApplicationContext(), HomeActivity.class)));
                Tv_Spot.setOnClickListener(v14 -> startActivity(new Intent(getApplicationContext(), HomeActivity.class)));
                TV_icclosed.setOnClickListener(v13 -> timedialog.dismiss());
                break;

            case R.id.TV_buyclose:
                if (llbuylayout.getVisibility() == View.VISIBLE) {
                    llbuylayout.setVisibility(View.INVISIBLE);
                }
                RLPrice.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLInvest.setVisibility(View.VISIBLE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                RLSpread.setVisibility(View.VISIBLE);
                RLLeverage.setVisibility(View.VISIBLE);
                llbuylayout.setVisibility(View.GONE);
                break;

            case R.id.TV_putclosed:
                lldigitalcalllayout.setVisibility(View.GONE);
                LLOperations.setVisibility(View.VISIBLE);
                break;

            /*case R.id.TvConfirm:
                Toast.makeText(this, "Confirmed", Toast.LENGTH_SHORT).show();
                //LLOperations.setVisibility(View.VISIBLE);
                //llbuylayout.setVisibility(View.GONE);
                break;*/

            /*case R.id.CVDigital:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                *//*if (LLDigitalVisible.getVisibility() == View.VISIBLE) {
                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLDigitalVisible.setVisibility(View.VISIBLE);
                    LLDigitalClose.setVisibility(View.VISIBLE);
                    Tv_DigitalSymbol.setVisibility(View.GONE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*//*
                RLTime.setVisibility(View.VISIBLE);
                RLStrikePrice.setVisibility(View.VISIBLE);
                RLLeverage.setVisibility(View.GONE);
                RLInvest.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.GONE);
                RLPrice.setVisibility(View.GONE);
                RLProfit.setVisibility(View.VISIBLE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.GONE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(v16 -> {
                    lldigitalcalllayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);
                });
                RLSell.setOnClickListener(v17 -> {
                    lldigitalcalllayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);

                });
                break;*/

            /*case R.id.CVForex:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                *//*if (LLForexVisible.getVisibility() == View.VISIBLE) {
                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLForexVisible.setVisibility(View.VISIBLE);
                    LLForexClose.setVisibility(View.VISIBLE);
                    Tv_ForexSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*//*
                RLTime.setVisibility(View.GONE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(v119 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);

                });
                RLSell.setOnClickListener(v120 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);
                });

                TvConfirm.setOnClickListener(v121 -> {
                    llbuylayout.setVisibility(View.GONE);
                    LLOperations.setVisibility(View.VISIBLE);
                });
                break;

            case R.id.CVStocks:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                *//*if (LLStocksVisible.getVisibility() == View.VISIBLE) {
                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLStocksVisible.setVisibility(View.VISIBLE);
                    LLStocksClose.setVisibility(View.VISIBLE);
                    Tv_StocksSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.VISIBLE);
                    Tv_DigitalSymbol.setVisibility(View.GONE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*//*
                RLTime.setVisibility(View.GONE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(v122 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);

                });
                RLSell.setOnClickListener(v123 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);
                });

                TvConfirm.setOnClickListener(v124 -> {
                    llbuylayout.setVisibility(View.GONE);
                    LLOperations.setVisibility(View.VISIBLE);
                });
                break;

            case R.id.CVETF:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                *//*if (LLETFVisible.getVisibility() == View.VISIBLE) {
                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLETFVisible.setVisibility(View.VISIBLE);
                    LLEtfClose.setVisibility(View.VISIBLE);
                    Tv_EtfSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*//*

                RLTime.setVisibility(View.GONE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(v125 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);

                });
                RLSell.setOnClickListener(v126 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);
                });

                TvConfirm.setOnClickListener(v127 -> {
                    llbuylayout.setVisibility(View.GONE);
                    LLOperations.setVisibility(View.VISIBLE);
                });
                break;

            case R.id.CVComm:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                *//*if (LLCommVisible.getVisibility() == View.VISIBLE) {
                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLCommVisible.setVisibility(View.VISIBLE);
                    LLCommClose.setVisibility(View.VISIBLE);
                    Tv_CommSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*//*
                RLTime.setVisibility(View.GONE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(v128 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);

                });
                RLSell.setOnClickListener(v129 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);
                });

                TvConfirm.setOnClickListener(v130 -> {
                    llbuylayout.setVisibility(View.GONE);
                    LLOperations.setVisibility(View.VISIBLE);
                });
                break;

            case R.id.CVCrypto:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                *//*if (LLCryptoVisible.getVisibility() == View.VISIBLE) {
                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLCryptoVisible.setVisibility(View.VISIBLE);
                    LLCryptoClose.setVisibility(View.VISIBLE);
                    Tv_CryptoSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    //LLDigitalVisible.startAnimation(slideDown);
                }*//*
                RLTime.setVisibility(View.VISIBLE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.GONE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(v131 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);

                });
                RLSell.setOnClickListener(v132 -> {
                    llbuylayout.setVisibility(View.VISIBLE);
                    LLOperations.setVisibility(View.GONE);
                });

                TvConfirm.setOnClickListener(v133 -> {
                    llbuylayout.setVisibility(View.GONE);
                    LLOperations.setVisibility(View.VISIBLE);
                });
                break;*/
            case R.id.RLPrice:
                AlertDialog pricedialog = new AlertDialog.Builder(this).create();
                pricedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pricedialog.setView(getLayoutInflater().inflate(R.layout.layout_price_dialog, null));
                pricedialog.show();

                EditText EtAmount = pricedialog.findViewById(R.id.EtAmount);
                int options = EtAmount.getImeOptions();
                EtAmount.setImeOptions(options | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

                Button BtnPriceUp = pricedialog.findViewById(R.id.BtnPriceUp);
                Button BtnPriceDown = pricedialog.findViewById(R.id.BtnPriceDown);
                Button BtnCfm = pricedialog.findViewById(R.id.BtnCfm);

                BtnCfm.setOnClickListener(v134 -> {
                    TextView TvMarket = findViewById(R.id.TvMarket);
                    TvMarket.setText(EtAmount.getText());
                    pricedialog.dismiss();
                });

                TV_icclosed = pricedialog.findViewById(R.id.TV_icclosed);
                TV_icclosed.setOnClickListener(v135 -> pricedialog.dismiss());

                BtnPriceDown.setOnClickListener(v1 -> {
                    if (TextUtils.isEmpty(EtAmount.getText())) {
                        Toast.makeText(HomeActivity.this, "Enter a Amount", Toast.LENGTH_SHORT).show();
                    } else {
                        int amountt = Integer.parseInt(EtAmount.getText().toString());
                        if (amountt >= 2) {
                            amountt--;
                        } else {
                            Toast.makeText(HomeActivity.this, "Minimum Purchase Price should be $1", Toast.LENGTH_SHORT).show();
                        }
                        EtAmount.setText(String.valueOf(amountt));
                    }
                });

                BtnPriceUp.setOnClickListener(v12 -> {
                    if (TextUtils.isEmpty(EtAmount.getText())) {
                        Toast.makeText(HomeActivity.this, "Enter a Amount", Toast.LENGTH_SHORT).show();
                    } else {
                        int amountt = Integer.parseInt(EtAmount.getText().toString());
                        amountt++;
                        EtAmount.setText(String.valueOf(amountt));
                    }
                });
                break;
            case R.id.RLLeverage:
                int[] location = new int[2];
                TvLeverage.getLocationOnScreen(location);
                final View mView = getLayoutInflater().inflate(R.layout.layout_lever, null, false);
                final PopupWindow popUp = new PopupWindow(mView, 150, 300, false);
                popUp.setTouchable(true);
                popUp.setFocusable(true);
                popUp.setOutsideTouchable(true);
                popUp.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]);

                TextView tv_Multiplierone = mView.findViewById(R.id.tv_Multiplierone);
                TextView tv_Multipliertwo = mView.findViewById(R.id.tv_Multipliertwo);
                TextView tv_Multiplierthree = mView.findViewById(R.id.tv_Multiplierthree);
                TextView tv_Multiplierfour = mView.findViewById(R.id.tv_Multiplierfour);
                TextView tv_Multiplierfive = mView.findViewById(R.id.tv_Multiplierfive);

                String Multiplierone, Multipliertwo, Multiplierthree, Multiplierfour, Multiplierfive;
                Multiplierone = tv_Multiplierone.getText().toString();
                Multipliertwo = tv_Multipliertwo.getText().toString();
                Multiplierthree = tv_Multiplierthree.getText().toString();
                Multiplierfour = tv_Multiplierfour.getText().toString();
                Multiplierfive = tv_Multiplierfive.getText().toString();

                tv_Multiplierone.setOnClickListener(v136 -> {
                    TV_multipiler.setText(Multiplierone);
                    popUp.dismiss();

                });
                tv_Multipliertwo.setOnClickListener(v137 -> {
                    TV_multipiler.setText(Multipliertwo);
                    popUp.dismiss();

                });
                tv_Multiplierthree.setOnClickListener(v138 -> {
                    TV_multipiler.setText(Multiplierthree);
                    popUp.dismiss();

                });
                tv_Multiplierfour.setOnClickListener(v139 -> {
                    TV_multipiler.setText(Multiplierfour);
                    popUp.dismiss();

                });
                tv_Multiplierfive.setOnClickListener(v140 -> {
                    TV_multipiler.setText(Multiplierfive);
                    popUp.dismiss();

                });

                break;
            case R.id.RLInvest:
                AlertDialog investdialog = new AlertDialog.Builder(this).create();
                investdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                investdialog.setView(getLayoutInflater().inflate(R.layout.layout_invest_dialog, null));
                investdialog.show();

                Button BtnConfirm, BtnMultiply, BtnDivide;
                EditText EtValue, EtCoefficient;
                final double[] total = new double[1];
                final double[] amount = new double[1];
                final double[] Coefficient = new double[1];
                RecyclerView RvPresets = investdialog.findViewById(R.id.RvPresets);
                BtnConfirm = investdialog.findViewById(R.id.BtnConfirm);

                EtValue = investdialog.findViewById(R.id.EtValue);
                EtCoefficient = investdialog.findViewById(R.id.EtCoefficient);

                int options2 = EtValue.getImeOptions();
                EtValue.setImeOptions(options2 | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

                int options1 = EtCoefficient.getImeOptions();
                EtCoefficient.setImeOptions(options1 | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

                BtnMultiply = investdialog.findViewById(R.id.BtnMultiply);
                BtnDivide = investdialog.findViewById(R.id.BtnDivide);

                TextView TV_closed = investdialog.findViewById(R.id.TV_closed);

                TV_closed.setOnClickListener(v141 -> investdialog.dismiss());

                BtnDivide.setOnClickListener(v142 -> {
                    if ((EtValue.getText().length() > 0) && (EtCoefficient.getText().length() > 0)) {
                        amount[0] = Double.parseDouble(EtValue.getText().toString());
                        Coefficient[0] = Double.parseDouble(EtCoefficient.getText().toString());
                        total[0] = amount[0] / Coefficient[0];
                        EtValue.setText(Double.toString(total[0]));
                    }

                });

                BtnMultiply.setOnClickListener(v143 -> {
                    if ((EtValue.getText().length() > 0) && (EtCoefficient.getText().length() > 0)) {
                        amount[0] = Double.parseDouble(EtValue.getText().toString());
                        Coefficient[0] = Double.parseDouble(EtCoefficient.getText().toString());
                        total[0] = amount[0] * Coefficient[0];
                        EtValue.setText(Double.toString(total[0]));
                    }
                });

                BtnConfirm.setOnClickListener(v144 -> {
                    TvInvestValue.setText(EtValue.getText());
                    investdialog.dismiss();
                });
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                RvPresets.setLayoutManager(layoutManager);


                String[] a = {"50", "100", "200", "300", "400", "500", "1000", "2000", "5000", "10000"};
                ArrayList<String> data = new ArrayList<String>(Arrays.asList(a));

                PresetsAdapter presetsAdapter = new PresetsAdapter(this, data);
                RvPresets.setAdapter(presetsAdapter);
                presetsAdapter.notifyDataSetChanged();
                presetsAdapter.onItemClickListner((list, potition) -> EtValue.setText(list.get(potition)));

                break;
            case R.id.TvDeposit:
                startActivity(new Intent(this, DepositActivity.class));
                /*signin = new AlertDialog.Builder(this).create();
                signin.requestWindowFeature(Window.FEATURE_NO_TITLE);
                signin.setView(getLayoutInflater().inflate(R.layout.layout_signin_dialog, null));
                signin.show();

                BtnLogin = signin.findViewById(R.id.BtnLogin);
                BtnLogin.setOnClickListener(this);

                Tvclosed = signin.findViewById(R.id.Tvclosed);

                Tvclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signin.dismiss();
                    }
                });*/

                break;
            case R.id.BtnLogin:
                signin.dismiss();
                break;
            case R.id.TvAddRealAccount:
                balancedialog.dismiss();
                signin = new AlertDialog.Builder(this).create();
                signin.requestWindowFeature(Window.FEATURE_NO_TITLE);
                signin.setView(getLayoutInflater().inflate(R.layout.layout_signin_dialog, null));
                signin.show();

                BtnLogin = signin.findViewById(R.id.BtnLogin);
                BtnLogin.setOnClickListener(this);

                Tvclosed = signin.findViewById(R.id.Tvclosed);

                Tvclosed.setOnClickListener(v145 -> signin.dismiss());

                break;
            case R.id.RLBalance:

                realaccedialog = new AlertDialog.Builder(this).create();
                realaccedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                realaccedialog.setView(getLayoutInflater().inflate(R.layout.layout_real_acc_balance_dialog, null));
                realaccedialog.show();
                realaccedialog.setCanceledOnTouchOutside(true);
                ImageView Iv_withdraw = realaccedialog.findViewById(R.id.Iv_withdraw);
                ImageView Iv_deposite = realaccedialog.findViewById(R.id.Iv_deposite);
                ImageView Iv_check = realaccedialog.findViewById(R.id.Iv_check);
                ImageView Iv_check1 = realaccedialog.findViewById(R.id.Iv_check1);
                ImageView Iv_refresh = realaccedialog.findViewById(R.id.Iv_refresh);
                ImageView IV_info = realaccedialog.findViewById(R.id.IV_info);
                TextView TV_info = realaccedialog.findViewById(R.id.TV_info);
                TextView TV_close = realaccedialog.findViewById(R.id.TV_close);
                TV_close.setOnClickListener(v146 -> realaccedialog.dismiss());
                TextView Tv_UsdCashValue1 = realaccedialog.findViewById(R.id.Tv_UsdCashValue1);
                IV_info.setOnClickListener(v147 -> TV_info.setVisibility(View.VISIBLE));

                Iv_refresh.setOnClickListener(v148 -> {
                    IV_info.setVisibility(View.GONE);
                    Iv_refresh.setVisibility(View.GONE);
                    Tv_UsdCashValue1.setText("$10000");

                });

                RelativeLayout RL_Realacc = realaccedialog.findViewById(R.id.RL_Realacc);
                RelativeLayout RL_practiceacc = realaccedialog.findViewById(R.id.RL_practiceacc);

                RL_Realacc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Iv_check.setVisibility(View.VISIBLE);
                        Iv_check1.setVisibility(View.GONE);
                    }
                });

                RL_practiceacc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Iv_check1.setVisibility(View.VISIBLE);
                        Iv_check.setVisibility(View.GONE);

                    }
                });

                Iv_withdraw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(HomeActivity.this, WithdrawActivity.class));
                    }
                });
                Iv_deposite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity.this, DepositActivity.class));

                    }
                });

                /*balancedialog = new AlertDialog.Builder(this).create();
                balancedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                balancedialog.setView(getLayoutInflater().inflate(R.layout.layout_balance_dialog, null));
                balancedialog.show();

                TextView TvAddRealAccount = balancedialog.findViewById(R.id.TvAddRealAccount);

                TvAddRealAccount.setOnClickListener(this);*/

                break;
            // add symbol
            case R.id.IvAddSymbol:
                startActivity(new Intent(HomeActivity.this, SymbolListActivity.class));
                startActivityForResult(new Intent(HomeActivity.this, SymbolListActivity.class), ADD_SYMBOL);
                break;
            case R.id.IvMenu:
                RLFrames.setVisibility(View.GONE);
                fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                if (RLNavigation.getVisibility() == View.VISIBLE) {
                    RLNavigation.setVisibility(View.GONE);
                    fragmentTransaction.replace(R.id.FragmentNav, new BlankFragment());
                } else {
                    fragmentTransaction.replace(R.id.FragmentNav, new ProfileFragment());
                    RLNavigation.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.IvTab1:
                if (!IvTab1.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(0).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(1);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }

                break;
            case R.id.IvTab2:
                if (!IvTab2.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(1).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(2);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }
                break;


            case R.id.IvTab3:
                if (!IvTab3.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(2).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(3);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }
                break;
            case R.id.IvTab4:
                if (!IvTab4.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(3).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(4);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }

                break;
            case R.id.IvTab5:
                if (!IvTab5.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(4).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(5);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }
                break;
            case R.id.IvTab6:
                customizeFun(fragmentTransaction);
                break;
            case R.id.IvInfo:
                if (!IvInfo.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, new SymbolInfoFragment());
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(7);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }
                break;

            // alerts
            case R.id.TvAlertBuy:
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault());
                String currentDateTime = sdf.format(new Date());

                String alertValue = TVAlertPriceValue.getText().toString().trim();
                alerts.setAlert(alertValue);
                alerts.setTime(currentDateTime);
                databaseReference.push().setValue(alerts);
                break;
        }
        fragmentTransaction.commit();
    }
    public static int ADD_SYMBOL = 1001;

    @Override
    protected void onResume() {
        super.onResume();
        List<SymbolsData> symbols = db.allSymbols();
        if (symbols != null) {
            Log.d(TAG, "onResume: "+symbols.size());

            setDataInRecyclerView(symbols);
        }}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_SYMBOL) {
            List<SymbolsData> symbols = db.allSymbols();
            if (symbols != null) {
                Log.d(TAG, "onActivityResult: "+symbols.size());


//                    Log.i("TAG", "onCreatesymbolonactivity: " + i + " sname " + symbols.get(i).getSymbolName() + " url " + symbols.get(i).getImgaeUrl() + " desc " + symbols.get(i).getDescription());

                setDataInRecyclerView(symbols);

            }
        }
    }


    private Fragment getFragmentToReplace(String customizeName) {
        Fragment returnFragment = new BlankFragment();
        if (customizeName.equalsIgnoreCase("Video Tutorial")) {
            returnFragment = new TutorialFragment();
        } else if (customizeName.equalsIgnoreCase("Chats")) {
            returnFragment = new ChatFragment();
        } else if (customizeName.equalsIgnoreCase("Portfolio")) {
            returnFragment = new PortfolioFragment();
        } else if (customizeName.equalsIgnoreCase("Market Analysis")) {
            returnFragment = new MarketAnalysisFragment();
        } else if (customizeName.equalsIgnoreCase("Price Movements")) {
            returnFragment = new MovesAlertsFragment();
        }
        return returnFragment;
    }

    public void customizeFun(FragmentTransaction fragmentTransaction) {
        RLFrames.setVisibility(View.GONE);

        setTabView(customizeArrayList);

        setPref(this, "tabList", new Gson().toJson(customizeArrayList));

        //fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
        if (!IvTab6.isSelected()) {
            fragmentTransaction.replace(R.id.FragmentCustomize, new CustomizeFragment(customizeArrayList));
            RLCustomize.setVisibility(View.VISIBLE);
            highLightSelectedTagImg(6);
        } else {
            fragmentTransaction.replace(R.id.FragmentCustomize, new BlankFragment());
            RLCustomize.setVisibility(View.GONE);
            clearSelectedTagImg();
        }
    }

    private void setCustomVisibility(int i) {
        int customView;
        if (customizeArrayList.get(i).getCustomizeView() == 1) {
            customView = View.VISIBLE;
        } else {
            customView = View.GONE;
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Video Tutorial")) {
            IvTab4.setVisibility(customView);
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Chats")) {
            IvTab5.setVisibility(customView);
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Portfolio")) {
            IvTab1.setVisibility(customView);
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Market Analysis")) {
            IvTab2.setVisibility(customView);
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Price Movements")) {
            IvTab3.setVisibility(customView);
        }
    }

    private void setTabView(ArrayList<CustomizeSymbol> customizeArrayList) {

        IvTab1.setImageDrawable(getResources().getDrawable(customizeArrayList.get(0).getCustomizeDrawable()));
        IvTab2.setImageDrawable(getResources().getDrawable(customizeArrayList.get(1).getCustomizeDrawable()));
        IvTab3.setImageDrawable(getResources().getDrawable(customizeArrayList.get(2).getCustomizeDrawable()));
        IvTab4.setImageDrawable(getResources().getDrawable(customizeArrayList.get(3).getCustomizeDrawable()));
        IvTab5.setImageDrawable(getResources().getDrawable(customizeArrayList.get(4).getCustomizeDrawable()));

        for (int i = 0; i < customizeArrayList.size(); i++) {
            setCustomVisibility(i);
        }
    }

    public void highLightSelectedTagImg(int i) {
        clearSelectedTagImg();
        switch (i) {
            case 1:
                IvTab1.setSelected(true);
                break;
            case 2:
                IvTab2.setSelected(true);
                break;
            case 3:
                IvTab3.setSelected(true);
                break;
            case 4:
                IvTab4.setSelected(true);
                break;
            case 5:
                IvTab5.setSelected(true);
                break;
            case 6:
                IvTab6.setSelected(true);
                break;
            case 7:
                IvInfo.setSelected(true);
                break;
        }

    }

    public void clearSelectedTagImg() {
        IvTab1.setSelected(false);
        IvTab2.setSelected(false);
        IvTab3.setSelected(false);
        IvTab4.setSelected(false);
        IvTab5.setSelected(false);
        IvTab6.setSelected(false);
        IvInfo.setSelected(false);
    }

    public void highLightText(int i) {
        clearSelectedText();
        switch (i) {
            case 5:
                txt_5_sec.setSelected(true);
                break;
            case 10:
                txt_10_sec.setSelected(true);
                break;
            case 15:
                txt_15_sec.setSelected(true);
                break;
            case 30:
                txt_30_sec.setSelected(true);
                break;
            case 1:
                txt_1_min.setSelected(true);
                break;
        }
    }

    private void highLightImg(int i) {
        clearSelectedImg();
        switch (i) {
            case 1:
                img1.setSelected(true);
                break;
            case 2:
                img2.setSelected(true);
                break;
            case 3:
                img3.setSelected(true);
                break;
            case 4:
                img4.setSelected(true);
                break;

        }
    }

    private void clearSelectedText() {
        txt_5_sec.setSelected(false);
        txt_10_sec.setSelected(false);
        txt_15_sec.setSelected(false);
        txt_30_sec.setSelected(false);
        txt_1_min.setSelected(false);
    }

    private void clearSelectedImg() {
        img1.setSelected(false);
        img2.setSelected(false);
        img3.setSelected(false);
        img4.setSelected(false);
    }

    public void setAlertVisibility() {
        if (LLOperations.getVisibility() == View.VISIBLE) {
            LLOperations.setVisibility(View.GONE);
            RLOperations.setVisibility(View.VISIBLE);

        } else {
            LLOperations.setVisibility(View.VISIBLE);
            RLOperations.setVisibility(View.GONE);

        }
    }

    public void plusValue() {
        if (currentValue1 <= 999) {
            currentValue1 = currentValue1 + 1;
        }
    }

    public void minValue() {
        if (currentValue1 >= 1) {
            currentValue1 = currentValue1 - 1;
        }
    }
}
