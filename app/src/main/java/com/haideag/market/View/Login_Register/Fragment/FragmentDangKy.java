package com.haideag.market.View.Login_Register.Fragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;


import com.haideag.market.DataBase.DbHelper;
import com.haideag.market.R;
import com.haideag.market.View.Login_Register.ViewRegister;


public class FragmentDangKy extends Fragment implements ViewRegister, View.OnClickListener, View.OnFocusChangeListener {

    Button btnRegister,btnFBRegister;

    EditText edHoTen,edDiaChiEmail,edMK,edNhapLaiMK;
    SwitchCompat SCSendEmail;

    TextInputLayout input_HoTen,input_DiaChiEmail,input_MK,input_NhapLaiMK;
    
    private DbHelper dbHelper;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_register,container,false); // We suppose the layout file for this fragment is "layout_fragmentdangky.xml"

        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        edHoTen = (EditText) view.findViewById(R.id.edHoTenRegister);
        edDiaChiEmail = (EditText) view.findViewById(R.id.edDiaChiEmailregister);
        edMK = (EditText) view.findViewById(R.id.edMKRegister);
        edNhapLaiMK = (EditText) view.findViewById(R.id.edNhapLaiMKRegister);

        SCSendEmail = (SwitchCompat) view.findViewById(R.id.SCSendEmail);

        input_HoTen = (TextInputLayout) view.findViewById(R.id.input_HoTenregister);
        input_DiaChiEmail = (TextInputLayout) view.findViewById(R.id.input_DiaChiEmailregister);
        input_MK = (TextInputLayout) view.findViewById(R.id.input_MKRegister);
        input_NhapLaiMK = (TextInputLayout) view.findViewById(R.id.input_NhapLaiMKRegister);





        btnRegister.setOnClickListener(this);

        edHoTen.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);

        edNhapLaiMK.setOnFocusChangeListener(this);

        btnRegister = (Button) view.findViewById(R.id.btnRegister);




        btnFBRegister = (Button) view.findViewById(R.id.btnFBRegister);
        btnFBRegister.setOnClickListener(this);

        this.dbHelper = new DbHelper(getActivity());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoten = edHoTen.getText().toString();
                String email = edDiaChiEmail.getText().toString();
                String password = edMK.getText().toString();
                Boolean checkinsertdata = dbHelper.insertUser(hoten, email, password);
                if(checkinsertdata) {
                    Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void Registersuccessful() {

    }

    @Override
    public void RegisterFail() {

    }

    @Override
    public void onClick(View v) {
        int id = getView().getId();
        if (id == R.id.btnRegister) {
            btnRegister();
            ;
        }
    }


    String  sendemail = "";
    private void btnRegister(){
        String hoten = edHoTen.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String matkhau = edMK.getText().toString();
        String nhaplaimatkhau = edNhapLaiMK.getText().toString();

        SCSendEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                sendemail = b + "";
            }
        });





    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        if (id == R.id.edHoTenRegister) {
            if (!b){
                String chain = ((EditText)view).getText().toString();
                if (chain.trim().equals("") || chain.equals(null)){
                    input_HoTen.setErrorEnabled(true);
                    input_HoTen.setError("Bạn chưa nhập mục này");
                }else {
                    input_HoTen.setErrorEnabled(false);
                    input_HoTen.setError("");
                }
            }

        }if (id == R.id.edDiaChiEmailregister) {
            if (!b){

                String chain = ((EditText)view).getText().toString();
                Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chain).matches();
                if (chain.trim().equals("") || chain.equals(null)){
                    input_DiaChiEmail.setErrorEnabled(true);
                    input_DiaChiEmail.setError("Bạn chưa nhập mục này");
                }else {
                    if (!kiemtraemail) {
                        input_DiaChiEmail.setErrorEnabled(true);
                        input_DiaChiEmail.setError("Đây không phải địa chỉ EMAIL!");
                    }else {
                        input_DiaChiEmail.setErrorEnabled(false);
                        input_DiaChiEmail.setError("");
                    }
                }
            }
        }if (id == R.id.edMKRegister) {
            ;
        }if (id == R.id.edNhapLaiMKRegister) {
            if (!b){
                String chain = ((EditText)view).getText().toString();
                String matkhau = edMK.getText().toString();
                if (chain.equalsIgnoreCase(matkhau)){
                    input_NhapLaiMK.setErrorEnabled(true);
                    input_NhapLaiMK.setError("Mật khẩu không trùng khớp !");
                }else {
                    input_NhapLaiMK.setErrorEnabled(false);
                    input_NhapLaiMK.setError("");
                }
            }


        }
    }



}