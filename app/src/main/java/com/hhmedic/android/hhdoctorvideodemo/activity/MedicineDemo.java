package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.sdk.medicine.HHMedicine;

public class MedicineDemo extends AppCompatActivity implements View.OnClickListener {

    private EditText mOrderIdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_demo);
        initUI();
    }

    private void initUI() {
        mOrderIdEdit = findViewById(R.id.order_id);
        mOrderIdEdit.setText(LocalConfig.DefaultMedicineOrderId);
        ((TextView)findViewById(R.id.title)).setText("药Demo");
        findViewById(R.id.back_btn).setOnClickListener(this);
        findViewById(R.id.order_list).setOnClickListener(this);
        findViewById(R.id.order_detail).setOnClickListener(this);
        findViewById(R.id.pay_detail).setOnClickListener(this);
        findViewById(R.id.address_list).setOnClickListener(this);
        findViewById(R.id.is_in_develop).setVisibility(LocalConfig.isDevelop(this) ? View.VISIBLE : View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.order_list:
                orderList();
                break;
            case R.id.order_detail:
                orderDetail();
                break;
            case R.id.pay_detail:
                payDetail();
                break;
            case R.id.address_list:
                addressList();
                break;
        }
    }

    private void orderList() {
        HHMedicine.orderList(this, LocalConfig.getLoginedToken(this));
    }

    private void orderDetail() {
        String orderId = mOrderIdEdit.getText().toString();
        if (orderId.isEmpty()) {
            Toast.makeText(this, "请填写orderID再重试", Toast.LENGTH_SHORT).show();
            return;
        }
        HHMedicine.orderDetail(this, orderId, LocalConfig.getLoginedToken(this), orderId1 -> Toast.makeText(this, "支付成功了" + orderId1, Toast.LENGTH_SHORT).show());
    }

    private void payDetail() {
        HHMedicine.payDetail(this, LocalConfig.getLoginedToken(this));
    }

    private void addressList() {
        HHMedicine.addressList(this, LocalConfig.getLoginedToken(this));
    }
}
