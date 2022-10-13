package github.apps;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText checkAmountValue, partySizeValue;
    TextView fifteenPercentTipValue, twentyPercentTipValue, twentyFivePercentTipValue, fifteenPercentTotalValue, twentyPercentTotalValue, twentyFivePercentTotalValue;
    Button buttonCompute;

    double billQuantity = 0.0;
    double partySizeQuantity = 0;
    double fifteenPercentTipQuantity, twentyPercentTipQuantity, twentyFivePercentTipQuantity,
            fifteenPercentTotalQuantity, twentyPercentTotalQuantity, twentyFivePercentTotalQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variables which receive data from user
        checkAmountValue = findViewById(R.id.checkAmountValue);
        partySizeValue = findViewById(R.id.partySizeValue);

        // Buttons
        buttonCompute = findViewById(R.id.buttonCompute);

        // Variables showing data to user

        // Tip Percentages per person
        fifteenPercentTipValue = findViewById(R.id.fifteenPercentTipValue);
        twentyPercentTipValue = findViewById(R.id.twentyPercentTipValue);
        twentyFivePercentTipValue = findViewById(R.id.twentyFivePercentTipValue);

        // Total per person
        fifteenPercentTotalValue = findViewById(R.id.fifteenPercentTotalValue);
        twentyPercentTotalValue = findViewById(R.id.twentyPercentTotalValue);
        twentyFivePercentTotalValue = findViewById(R.id.twentyFivePercentTotalValue);

        buttonCompute.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String checkAmountValueString = checkAmountValue.getText().toString();
                String partySizeValueString = partySizeValue.getText().toString();

                // Conditions. If conditions are not satisfied a Toast message will pop up in the screen.
                if(!checkAmountValueString.isEmpty() && Double.valueOf(checkAmountValueString) > 0){
                    if(!partySizeValueString.isEmpty() && Double.valueOf(partySizeValueString) > 0) {

                        billQuantity = Double.valueOf(checkAmountValueString);
                        billQuantity = billQuantity * 100;
                        billQuantity = Math.round(billQuantity);
                        billQuantity = billQuantity / 100;

                        partySizeQuantity = Double.valueOf(partySizeValueString);

                        fifteenPercentTipQuantity = Math.round((billQuantity * .15) / partySizeQuantity);
                        twentyPercentTipQuantity = Math.round((billQuantity * .20) / partySizeQuantity);
                        twentyFivePercentTipQuantity = Math.round((billQuantity * .25) / partySizeQuantity);

                        fifteenPercentTotalQuantity = Math.round((fifteenPercentTipQuantity) + (billQuantity / partySizeQuantity));
                        twentyPercentTotalQuantity = Math.round((twentyPercentTipQuantity) + (billQuantity / partySizeQuantity));
                        twentyFivePercentTotalQuantity = Math.round((twentyFivePercentTipQuantity) + (billQuantity / partySizeQuantity));

                        fifteenPercentTipValue.setText(String.format(Locale.US, "%.2f", fifteenPercentTipQuantity));
                        twentyPercentTipValue.setText(String.format(Locale.US, "%.2f", twentyPercentTipQuantity));
                        twentyFivePercentTipValue.setText(String.format(Locale.US, "%.2f", twentyFivePercentTipQuantity));

                        fifteenPercentTotalValue.setText(String.format(Locale.US, "%.2f", fifteenPercentTotalQuantity));
                        twentyPercentTotalValue.setText(String.format(Locale.US, "%.2f", twentyPercentTotalQuantity));
                        twentyFivePercentTotalValue.setText(String.format(Locale.US, "%.2f", twentyFivePercentTotalQuantity));
                    }
                    else{
                        displayToast();
                    }
                }
                else{
                    displayToast();
                }
            }

            public void displayToast(){
                Toast.makeText(MainActivity.this,"Empty or Incorrect Value(s)!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}