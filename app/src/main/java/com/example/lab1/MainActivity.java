package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public ArrayList<String> studentList;
    public ArrayAdapter studentAdapter;
    EditText edtHoTen;
    EditText edtSDT;

    RadioButton rbNam;
    Button btnAdd;
    RadioButton rbNu;

    String[] courses = { "Quê quán", "Hà Nội", "Bắc Ninh", "Hà Nam" };
    ListView lvContact;

    String selectedCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtSDT = findViewById(R.id.edtSDT);
        rbNam = findViewById(R.id.rbNam);
        rbNu = findViewById(R.id.rbNu);
        btnAdd = findViewById(R.id.btnAdd);
        lvContact = findViewById(R.id.lvContact);


        Spinner spino = findViewById(R.id.coursesspinner);
        spino.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        spino.setAdapter(ad);

        studentList = new ArrayList<>();
        studentList.add("a - 09854444 - Hà Nội");
        studentAdapter = new ArrayAdapter<>(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, studentList);
        lvContact.setAdapter(studentAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtHoTen.getText().toString();
                String sdt = edtSDT.getText().toString();
                String gioiTinh = rbNam.isChecked() ? "Nam" : "Nữ"; // Lấy giới tính từ RadioButton

                // Kiểm tra nếu tất cả các thông tin cần thiết đều đã được nhập
                if (!hoTen.isEmpty() && !sdt.isEmpty() && selectedCourse != null && !selectedCourse.equals("Quê quán")) {
                    // Tạo chuỗi thông tin sinh viên
                    String studentInfo = hoTen + " - " + sdt + " - " + gioiTinh + " - " + selectedCourse;

                    // Thêm thông tin sinh viên vào danh sách
                    studentList.add(studentInfo);

                    // Cập nhật ListView
                    studentAdapter.notifyDataSetChanged();

                    // Xóa các ô nhập sau khi thêm
                    edtHoTen.setText("");
                    edtSDT.setText("");
                    rbNam.setChecked(false);
                    rbNu.setChecked(false);
                } else {
                    // Hiển thị thông báo nếu người dùng chưa nhập đủ thông tin
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCourse = courses[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}