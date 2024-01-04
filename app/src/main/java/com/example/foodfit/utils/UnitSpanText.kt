package com.example.foodfit.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan

object UnitSpanText {
    fun spanUnitText(text: CharSequence): SpannableString {
        val spannableString = SpannableString(text)
        val italicSpan = StyleSpan(Typeface.ITALIC)
        val smallerTextSpan = RelativeSizeSpan(0.5f)
        val fontUnit = TypefaceSpan("cursive")
        var startSpanIndex = 0

        for (index in text.indices) {
            val char = text[index]
            // Example: print the index and character
            println("Index: $index, Character: $char")

            if (char.isDigit().not()) {
                startSpanIndex = index
                break
            }
        }

        spannableString.setSpan(italicSpan, startSpanIndex, text.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        spannableString.setSpan(smallerTextSpan, startSpanIndex, text.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        spannableString.setSpan(fontUnit, startSpanIndex, text.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)

        return spannableString
    }
}