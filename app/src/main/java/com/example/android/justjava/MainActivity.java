package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 0;

    private CheckBox checkBox;
    boolean creamAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String message = "";
        checkBox = (CheckBox) findViewById(R.id.check_cream);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    creamAdded = true;
                } else {
                    creamAdded = false;
                }
            }
        });
        if (numberOfCoffees < 0) {
            message = "Total: $0" + "\nThank you!";
            displayMessage(message);
            numberOfCoffees = 0;
            display(numberOfCoffees);
        } else {
            if (creamAdded) {
                int totalPrice = calculatePrice(numberOfCoffees) + 10;
                message = "Total: $" + totalPrice + "\nThank you!";
                displayMessage(message);
                display(numberOfCoffees);
            } else {
                message = "Total: $" + calculatePrice(numberOfCoffees) + "\nThank you!";
                displayMessage(message);
                display(numberOfCoffees);
            }
        }
    }

    public void increment(View view) {
        numberOfCoffees += 1;
        display(numberOfCoffees);
    }

    public void decrement(View view) {
        if (numberOfCoffees >= 1) {
            numberOfCoffees -= 1;
            display(numberOfCoffees);
        }
    }

    public void onClick (View view) {
        if (checkBox.isChecked()) {
            creamAdded = true;
        }
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        return price;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}