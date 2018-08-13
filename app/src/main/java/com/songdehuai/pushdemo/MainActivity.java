package com.songdehuai.pushdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.songdehuai.pushdemo.tools.SPUtils;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.xiaomi.mipush.sdk.MiPushClient;

import org.xutils.common.task.AbsTask;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {


    Button initBtn;

    Button nextBtn;

    EditText nameEt;

    EditText tokenEt;

    String token;

    EditText miRegid;

    TextView phoneTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
        //controller
        initBtn = findViewById(R.id.init_btn);
        nameEt = findViewById(R.id.name_et);
        tokenEt = findViewById(R.id.name_token);
        nextBtn = findViewById(R.id.next_btn);
        phoneTv = findViewById(R.id.phone_tv);
        miRegid = findViewById(R.id.mi_token);

        token = SPUtils.get(getApplicationContext(), "TOKEN", "").toString();

        nameEt.setText(token);


        miRegid.setText(MiPushClient.getRegId(getApplicationContext()));
        tokenEt.setText(XGPushConfig.getToken(getApplicationContext()));


        initBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEt.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    //解绑所有账号
                    XGPushManager.delAllAccount(getApplicationContext());
                    //注册信鸽，绑定账号
                    XGPushManager.bindAccount(getApplicationContext(), name);
                    //绑定账号
                    SPUtils.put(getApplicationContext(), "TOKEN", name);
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MessageListActivity.class);
                startActivity(intent);
            }
        });
        String phone = android.os.Build.MANUFACTURER;
        phoneTv.setText(phone);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
