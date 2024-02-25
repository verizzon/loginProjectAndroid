package com.example.hitmatala2.fragments;
import com.example.hitmatala2.classes.User;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hitmatala2.R;
import com.example.hitmatala2.activities.MainActivity;


import java.io.Serializable;
import java.util.ArrayList;


public class FragmentOne extends Fragment {
    private User curUser;



    private EditText usernameEditText, passwordEditText;
    private Button regBtn, logBtn;
    private ArrayList<User> registeredUsers = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        usernameEditText = view.findViewById(R.id.loginInput);
        passwordEditText = view.findViewById(R.id.passInput);
        regBtn = view.findViewById(R.id.btnReg);
        logBtn = view.findViewById(R.id.btnLogin);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        return view;
    }

    private void register(){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //check if user exist
        for(User user : registeredUsers) {
            if(user.getUsername().equals(username)){
                Toast.makeText(getActivity(), "Username already exists!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //create a new user
        User newUser = new User(username,password);
        registeredUsers.add(newUser);
        // Pass username and product list to FragmentTwo
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putSerializable("product", newUser.getProductList());
        Navigation.findNavController(requireView()).navigate(R.id.action_fragmentOne_to_fragmentTwo, bundle);
    }

    private void login(){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        boolean loggedIn = false;

        for(User user : registeredUsers) {
            if((user.getUsername().equals(username)) && (user.getPassword().equals(password))){
                loggedIn = true;
                curUser = user;
                break;

            }
        }
        if(loggedIn) {
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putSerializable("product",curUser.getProductList());
            Toast.makeText(getActivity(), "Logged in", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentOne_to_fragmentTwo, bundle);
        } else {
            Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button button1 = view.findViewById(R.id.button1);
//        Button button2 = view.findViewById(R.id.button2);
//
//        button1.setOnClickListener(FragmentOne -> {
//            MainActivity.navController.navigate(R.id.fragmentTwo, null,MainActivity.options);
//        });
//
//        button2.setOnClickListener(FragmentOne -> {
//            MainActivity.navController.navigate(R.id.fragmentThree, null,MainActivity.options);
//        });
    }
}