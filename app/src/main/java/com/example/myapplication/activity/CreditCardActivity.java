package com.example.myapplication.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.example.myapplication.R;
import com.example.myapplication.constant.GlobalRetro;
import com.example.myapplication.constant.Utils;
import com.example.myapplication.model.BinList;
import com.example.myapplication.model.DbHandler;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditCardActivity extends Activity {
    private ImageView imgBack;
    private TextInputEditText card_number;
    public TextView check_data;
    public EditText creditCard;
    public TextView holdernm;
    public boolean key = false;
    public LinearLayout lin_right, llsucessres;
    public LinearLayout lin_wrong;
    public String strArray;
    public TextView txtScheme, tvCetegory, txtAlpha, txtCountryName, txtCurrency, txtBankName, txtBankUrl, txtBankPhone;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Utils.ChangeStatusBar(CreditCardActivity.this);
        setContentView((int) R.layout.activity_credit_card);

        EditText editText = (EditText) findViewById(R.id.creditCard);
        this.creditCard = editText;
        editText.addTextChangedListener(new CreditCardNumberFormattingTextWatcher());
        this.holdernm = (TextView) findViewById(R.id.holdernm);
        this.lin_right = (LinearLayout) findViewById(R.id.lin_right);
        this.lin_wrong = (LinearLayout) findViewById(R.id.lin_wrong);
        TextView textView = (TextView) findViewById(R.id.check_data);
        llsucessres = findViewById(R.id.llsucessres);
        this.check_data = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int length = creditCard.length();
                if (creditCard.getText().toString().equals("")) {
                    creditCard.setError("Enter Your Card Number");
                } else if (length < 19) {
                    creditCard.setError("Enter Your Card Number");
                } else if (!key) {

                    DoBinList();
                    check_data.setVisibility(View.GONE);

                    CreditCardActivity.hideKeyboard(CreditCardActivity.this);
                    boolean unused3 = key = false;

                }
            }
        });
        txtScheme = findViewById(R.id.txtScheme);
        tvCetegory = findViewById(R.id.tvCetegory);
        txtAlpha = findViewById(R.id.txtAlpha);
        txtCountryName = findViewById(R.id.txtCountryName);
        txtCurrency = findViewById(R.id.txtCurrency);
        txtBankName = findViewById(R.id.txtBankName);
        txtBankUrl = findViewById(R.id.txtBankUrl);
        txtBankPhone = findViewById(R.id.txtBankPhone);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
                if (editable.length() == 19) {
                    CreditCardActivity creditCardActivity = CreditCardActivity.this;
                    String unused = creditCardActivity.strArray = creditCardActivity.creditCard.getText().toString().toString().replaceAll("\\s+", "");
                }
                if (editable.length() == 0) {
                    check_data.setVisibility(View.VISIBLE);
                    llsucessres.setVisibility(View.GONE);
                    lin_right.setVisibility(View.GONE);
                    lin_wrong.setVisibility(View.GONE);
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
                    lin_right.setVisibility(View.VISIBLE);
                    lin_right.setVisibility(View.VISIBLE);
                    lin_wrong.setVisibility(View.GONE);
                    holdernm.setText(response.body().getScheme());


                    try {
                        BinList binList = response.body();
                        txtScheme.setText(binList.getScheme());
                        tvCetegory.setText(binList.getCountry().getNumeric());
                        txtAlpha.setText(binList.getCountry().getAlpha2());
                        txtCountryName.setText(binList.getCountry().getName());
                        txtCurrency.setText(binList.getCountry().getCurrency());
                        txtBankName.setText(binList.getBank().getName());
                        txtBankUrl.setText(binList.getBank().getUrl());
                        txtBankPhone.setText(binList.getBank().getPhone());

                        txtBankUrl.setText(Html.fromHtml(String.format(binList.getBank().getUrl())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    new DbHandler(CreditCardActivity.this).insertUserDetails(creditCard.getText().toString(), response.body().getBrand(), response.body().getScheme());
                    return;
                }
                lin_wrong.setVisibility(View.VISIBLE);
                lin_right.setVisibility(View.GONE);
                holdernm.setText("Not Valid");
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
