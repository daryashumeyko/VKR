package com.example.vetsertification.ui;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegexMaskTextWatcher implements TextWatcher {
    public RegexMaskTextWatcher(EditText editTextObject, String regexForInputToMatch) {
        editText = editTextObject;
        regex = Pattern.compile(regexForInputToMatch);
    }

    public void afterTextChanged(Editable s) {
        if (regex.matcher(s).matches()) {
            editText.setTextColor(Color.parseColor("#ff808080"));
        } else {
            editText.setTextColor(Color.parseColor("#ffff0000"));
        }

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    private Pattern regex;
    private EditText editText;
}
