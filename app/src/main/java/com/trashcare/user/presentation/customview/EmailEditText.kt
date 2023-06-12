package com.trashcare.user.presentation.customview

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.trashcare.user.utils.EmailValidation.isEmailValid

class EmailEditText: TextInputEditText {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        addTextChangedListener(onTextChanged = {p0, _, _, _ ->
            if (!isEmailValid(p0.toString())) {
                error = "Wrong Format"
            }
        })
    }
}