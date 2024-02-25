package com.example.hitmatala2.fragments;
import com.example.hitmatala2.classes.Product;
import com.example.hitmatala2.classes.User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitmatala2.R;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class FragmentTwo extends Fragment {
    private TableLayout tableLayout;
    private ArrayList<Product> productList;

    private EditText editTextItem, editTextNumber;
    private Button btnAdd, btnDel;
    private TextView showUsername;
    private TextView productListTextView;


    private HashMap<String, HashMap<String, Integer>> userItemsMap = new HashMap<>();


    private String currentUser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        editTextItem = view.findViewById(R.id.editTextItem);
        editTextNumber = view.findViewById(R.id.editTextNumber);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnDel = view.findViewById(R.id.btnDel);
        showUsername = view.findViewById(R.id.showUsername);
        productListTextView = view.findViewById(R.id.productListTextView);


        if(bundle!=null) {
            String username = bundle.getString("username");
            productList = (ArrayList<Product>) bundle.getSerializable("product");

            if (productList != null && !productList.isEmpty()) {
                StringBuilder productListString = new StringBuilder();
                for (Product product : productList) {
                    productListString.append("Product: ").append(product.getName())
                            .append(", Amount: ").append(product.getAmount()).append("\n");
                }
                productListTextView.setText(productListString.toString());
            } else {
                productListTextView.setText("No products added yet.");
            }

            showUsername.setText(username);
        }else{
            showUsername.setText("Ne robit");
        }
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addProduct();
                }
            });
             btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeProduct();
            }
        });


//        btnDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteProduct();
//            }
//        });
        return view;
    }
    private void addProduct() {
        String productName = editTextItem.getText().toString().trim();
        String productAmount = editTextNumber.getText().toString().trim();

        // Validate input fields
        if (productName.isEmpty() || productAmount.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter product name and amount", Toast.LENGTH_SHORT).show();
            return;
        }
        // Check if the product already exists
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                // Update the amount
                int newAmount = Integer.parseInt(product.getAmount()) + Integer.parseInt(productAmount);
                product.setAmount(String.valueOf(newAmount));
                updateProductListTextView();
                return;
            }
        }
        // Create new product and add to the list
        Product product = new Product(productName, productAmount);
        productList.add(product);

        // Update the TextView to display the updated list
        updateProductListTextView();

        // Clear input fields
        editTextItem.getText().clear();
        editTextNumber.getText().clear();

    }
    private void removeProduct() {
        String productName = editTextItem.getText().toString().trim();
        String productAmount = editTextNumber.getText().toString().trim();

        // Validate input fields
        if (productName.isEmpty() || productAmount.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter product name and amount", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the product exists
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                // Update the amount
                int newAmount = Integer.parseInt(product.getAmount()) - Integer.parseInt(productAmount);
                if (newAmount <= 0) {
                    // Remove the product if the amount becomes zero or negative
                    productList.remove(product);
                } else {
                    // Update the amount
                    product.setAmount(String.valueOf(newAmount));
                }
                updateProductListTextView();
                return;
            }
        }

        Toast.makeText(getActivity(), "Product not found", Toast.LENGTH_SHORT).show();
    }



    private void updateProductListTextView() {
        StringBuilder productListString = new StringBuilder();
        for (Product product : productList) {
            productListString.append("Product: ").append(product.getName())
                    .append(", Amount: ").append(product.getAmount()).append("\n");
        }
        productListTextView.setText(productListString.toString());
    }
}

