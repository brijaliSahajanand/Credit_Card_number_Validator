package com.example.myapplication.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.example.myapplication.R;
import com.example.myapplication.constant.GlobalRetro;
import com.example.myapplication.constant.Utils;
import com.example.myapplication.model.BinList;
import com.example.myapplication.model.DbHandler;

import mostafa.ma.saleh.gmail.com.editcredit.EditCredit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCardActivity extends AppCompatActivity {
    public TextView BANKNAME, BANKPHONE, BANKURL, COUNTRYALPHA2, COUNTRYCURRENCY, COUNTRYLATITUDE, COUNTRYLONGITUDE, COUNTRYNAME, COUNTRYNUMERIC;
    LinearLayout llsucessres;
    public TextView SCHEME;
    public String bank_nm;
    public String bank_phone;
    public String bank_url;
    public EditCredit bin_number;
    public String brand, con_alpha2, con_currency, con_latitude, con_longtitude, con_name, con_numeric;
    public EditText creditCard;
    private String data;
    private String data1;
    public TextView holdername;
    public String number_lengh, number_luhn, prepaid, scheme, strArray, type;
    ImageView imgBack;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Utils.ChangeStatusBar(SearchCardActivity.this);
        setContentView((int) R.layout.activity_search_card2);


        EditCredit editCredit = (EditCredit) findViewById(R.id.bin_number);
        this.bin_number = editCredit;
        editCredit.setDrawableGravity(EditCredit.Gravity.RIGHT);
        EditText editText = (EditText) findViewById(R.id.creditCard);
        this.creditCard = editText;
        editText.addTextChangedListener(new CreditCardNumberFormattingTextWatcher());
        SCHEME = (TextView) findViewById(R.id.SCHEME);
        COUNTRYNUMERIC = (TextView) findViewById(R.id.COUNTRYNUMERIC);
        COUNTRYALPHA2 = (TextView) findViewById(R.id.COUNTRYALPHA2);
        COUNTRYNAME = (TextView) findViewById(R.id.COUNTRYNAME);
        COUNTRYCURRENCY = (TextView) findViewById(R.id.COUNTRYCURRENCY);
        COUNTRYLATITUDE = (TextView) findViewById(R.id.COUNTRYLATITUDE);
        COUNTRYLONGITUDE = (TextView) findViewById(R.id.COUNTRYLONGITUDE);
        BANKNAME = (TextView) findViewById(R.id.BANKNAME);
        BANKURL = (TextView) findViewById(R.id.BANKURL);
        BANKPHONE = (TextView) findViewById(R.id.BANKPHONE);
        holdername = (TextView) findViewById(R.id.holdername);
        llsucessres = findViewById(R.id.llsucessres);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        bin_number.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                int length = SearchCardActivity.this.bin_number.length();
                if (length == 6) {
                    SearchCardActivity searchCardActivity = SearchCardActivity.this;
                    String unused = searchCardActivity.strArray = searchCardActivity.bin_number.getText().toString().toString().replaceAll("\\s+", "");
                    SearchCardActivity.this.DoBinList();
                }
                if (length == 0) {

                }
            }
        });
    }

    public class CreditCardNumberFormattingTextWatcher implements TextWatcher {
        private boolean lock;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public CreditCardNumberFormattingTextWatcher() {
        }

        public void afterTextChanged(Editable editable) {
            if (!this.lock && editable.length() <= 19) {
                this.lock = true;
                for (int i = 4; i < editable.length(); i += 5) {
                    if (editable.toString().charAt(i) != ' ') {
                        editable.insert(i, " ");
                    }
                }
                if (editable.length() == 7) {
                    SearchCardActivity searchCardActivity = SearchCardActivity.this;
                    String unused = searchCardActivity.strArray = searchCardActivity.creditCard.getText().toString().toString()
                            .replaceAll("\\s+", "");
                    SearchCardActivity.this.DoBinList();
                    SearchCardActivity.hideKeyboard(SearchCardActivity.this);
                }
                if (editable.length() == 0) {
                    llsucessres.setVisibility(View.GONE);

                    // SearchCardActivity.this.update_lin.setVisibility(View.GONE);
                }
                this.lock = false;
            }
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public void DoBinList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loader));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        GlobalRetro.initRetrofit(this).DoBinList(this.strArray).enqueue(new Callback<BinList>() {
            public void onResponse(Call<BinList> call, Response<BinList> response) {
                Log.d("my", response.body() + "");
                progressDialog.dismiss();
                if (response.body() != null) {
                    llsucessres.setVisibility(View.VISIBLE);

                    // SearchCardActivity.this.update_lin.setVisibility(View.VISIBLE);
                    String unused = SearchCardActivity.this.number_lengh = response.body().getNumber().getLength();

                    String unused2 = SearchCardActivity.this.number_luhn = response.body().getNumber().getLuhn();

                    String unused3 = SearchCardActivity.this.scheme = response.body().getScheme();
                    SearchCardActivity.this.SCHEME.setText(SearchCardActivity.this.scheme);
                    SearchCardActivity.this.holdername.setText(SearchCardActivity.this.scheme);
                    String unused4 = SearchCardActivity.this.type = response.body().getType();

                    String unused5 = SearchCardActivity.this.brand = response.body().getBrand();

                    String unused6 = SearchCardActivity.this.prepaid = response.body().getPrepaid();

                    if (response.body().getCountry() == null) {
                        SearchCardActivity.this.COUNTRYNUMERIC.setVisibility(View.GONE);
                        SearchCardActivity.this.COUNTRYALPHA2.setVisibility(View.GONE);
                        SearchCardActivity.this.COUNTRYNAME.setVisibility(View.GONE);
                        SearchCardActivity.this.COUNTRYCURRENCY.setVisibility(View.GONE);
                        SearchCardActivity.this.COUNTRYLATITUDE.setVisibility(View.GONE);
                        SearchCardActivity.this.COUNTRYLATITUDE.setVisibility(View.GONE);
                    } else {
                        String unused7 = SearchCardActivity.this.con_numeric = response.body().getCountry().getNumeric();
                        SearchCardActivity.this.COUNTRYNUMERIC.setText(SearchCardActivity.this.con_numeric);
                        String unused8 = SearchCardActivity.this.con_alpha2 = response.body().getCountry().getAlpha2();
                        SearchCardActivity.this.COUNTRYALPHA2.setText(SearchCardActivity.this.con_alpha2);
                        String unused9 = SearchCardActivity.this.con_name = response.body().getCountry().getName();
                        SearchCardActivity.this.COUNTRYNAME.setText(SearchCardActivity.this.con_name);
                        String unused10 = SearchCardActivity.this.con_currency = response.body().getCountry().getCurrency();
                        SearchCardActivity.this.COUNTRYCURRENCY.setText(SearchCardActivity.this.con_currency);
                        String unused11 = SearchCardActivity.this.con_latitude = response.body().getCountry().getLatitude();
                        SearchCardActivity.this.COUNTRYLATITUDE.setText(SearchCardActivity.this.con_latitude);
                        String unused12 = SearchCardActivity.this.con_longtitude = response.body().getCountry().getLongitude();
                        SearchCardActivity.this.COUNTRYLATITUDE.setText(SearchCardActivity.this.con_latitude);
                    }
                    if (response.body().getBank() == null) {
                        SearchCardActivity.this.BANKNAME.setVisibility(View.GONE);
                        SearchCardActivity.this.BANKURL.setVisibility(View.GONE);
                        SearchCardActivity.this.BANKPHONE.setVisibility(View.GONE);
                    } else {
                        String unused13 = SearchCardActivity.this.bank_nm = response.body().getBank().getName();
                        SearchCardActivity.this.BANKNAME.setText(SearchCardActivity.this.bank_nm);
                        String unused14 = SearchCardActivity.this.bank_url = response.body().getBank().getUrl();
                        SearchCardActivity.this.BANKURL.setText(SearchCardActivity.this.bank_url);
                        String unused15 = SearchCardActivity.this.bank_phone = response.body().getBank().getPhone();
                        SearchCardActivity.this.BANKPHONE.setText(SearchCardActivity.this.bank_phone);
                    }
                    new DbHandler(SearchCardActivity.this).insertUserDetails(SearchCardActivity.this.creditCard.getText().toString(), response.body().getBrand(), response.body().getScheme());
                    return;
                }
                SearchCardActivity.this.holdername.setText("Not Valid");
                Toast.makeText(SearchCardActivity.this, "Not Valid", Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Call<BinList> call, Throwable th) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {

                finish();
            }
        });
    }
}
