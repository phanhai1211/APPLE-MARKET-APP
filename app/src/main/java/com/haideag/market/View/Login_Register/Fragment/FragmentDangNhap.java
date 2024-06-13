package com.haideag.market.View.Login_Register.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import com.haideag.market.DataBase.DbHelper;
import com.haideag.market.DataBase.PreferenceHelper;
import com.haideag.market.R;
import com.haideag.market.View.TrangChu.TrangChuActivity;

import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements  View.OnClickListener {
    Button btnFB, btnDangNhap;
    EditText edUserName, edPassword;
    CallbackManager callbackManager;

    private void login(String email, String password) {
        DbHelper dbHelper = new DbHelper(getContext());
        String name = dbHelper.checkUser(email, password);
        if(name != null) {
            // Save name to sharedPreferences
            new PreferenceHelper(getContext()).setName(name);
            Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
            startActivity(iTrangChu);
        } else {
            Toast.makeText(getActivity(), "Tên đăng nhập và mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmentlogin, container, false);

        edUserName = (EditText) view.findViewById(R.id.edDiaChiEmailDN);
        edPassword = (EditText) view.findViewById(R.id.edMatkhauDN);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {
                // Handle cancel action
            }

            @Override
            public void onError(FacebookException error) {
                // Handle error
            }
        });

        btnFB = (Button) view.findViewById(R.id.btnFB);
        btnFB.setOnClickListener(this);

        btnDangNhap = (Button) view.findViewById(R.id.btndangnhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edUserName.getText().toString();
                String password = edPassword.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()) {
                    login(email, password);
                } else {
                    Toast.makeText(getActivity(), "Tên đăng nhập và mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnFB) {
            LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
        }
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}