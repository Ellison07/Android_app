package com.example.myapplication;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    private EditText etPrincipal, etRate, etMonths, etStart_date, etEnd_date;
    private Button calculateButton1, calculateButton2,dataBtn,btnData;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPrincipal = findViewById(R.id.principalEditText);
        etRate = findViewById(R.id.interestRateEditText);
        etMonths = findViewById(R.id.monthsEditText);
        calculateButton1 = findViewById(R.id.calculateButton1);
        tvResult = findViewById(R.id.resultTextView);
        etStart_date = findViewById(R.id.start_date_input);
        etEnd_date = findViewById(R.id.end_date_input);
        calculateButton2 = findViewById(R.id.calculateButton2);
        dataBtn = findViewById(R.id.dataBtn);
        btnData = findViewById(R.id.btnData);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDateTime = dateFormat.format(calendar.getTime());
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);

        calculateButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String principalText = etPrincipal.getText().toString();
                String rateText = etRate.getText().toString();
                String monthsText = etMonths.getText().toString();

                if (TextUtils.isEmpty(principalText) || TextUtils.isEmpty(rateText) || TextUtils.isEmpty(monthsText)) {
                    Toast.makeText(MainActivity.this, "Please fill Principal, Interest, Months", Toast.LENGTH_SHORT).show();
                    return;
                }
                double principle = Double.parseDouble(principalText);
                double rate = Double.parseDouble(rateText);
                double months = Double.parseDouble(monthsText);
                String result = "";
                double interest =0.00;
                if (months != 0) {
                     interest = (principle * rate * months) / 100;
                    double total = interest + principle;
                    result = String.format(Locale.getDefault(), "Interest only: %.2f\n Total Amount: %.2f", interest,total);
                }
                dbHelper.insertData(principle, rate, months,interest, currentDateTime);
                tvResult.setText(result);
            }
        });
        calculateButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String principalText = etPrincipal.getText().toString();
                    String rateText = etRate.getText().toString();
                    String startDateText = etStart_date.getText().toString();
                    String endDateText = etEnd_date.getText().toString();
                    if (principalText.isEmpty() || rateText.isEmpty() || startDateText.isEmpty() || endDateText.isEmpty()) {
                        Toast.makeText(getApplicationContext(),"Please fill Principal, Interest, StartDate, EndDate ",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        double principal = Double.parseDouble(principalText);
                        double rateOriginal = Double.parseDouble(rateText);
                        String start[] = startDateText.split("/");
                        String end[] = endDateText.split("/");
                        double years = Math.abs(Double.parseDouble(end[0]) - Double.parseDouble(start[0]));
                        double months = Math.abs(Double.parseDouble(end[1]) - Double.parseDouble(start[1]));
                        double days = Math.abs(Double.parseDouble(end[2]) - Double.parseDouble(start[2]));
                        double daysInYears = years * 360;
                        double daysInMonths = months * 30.44;
                        double totalDays = daysInMonths + daysInYears + days;
                        double totalMonths = totalDays / 30.44;
                        double interest = (principal * rateOriginal * totalMonths) / 100.00 ;
                        double res = principal + interest;
                        String result1 =String.format(Locale.getDefault(), "Interest only: %.2f\n Total Amount: %.2f", interest, res );
                        dbHelper.insertData(principal, rateOriginal, totalMonths,interest,currentDateTime);
                        tvResult.setText(result1);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Please enter valid input", Toast.LENGTH_SHORT).show();
                    }
                }
        });
        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity1.class);
                startActivity(intent);
            }
        });
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });
        }
}


