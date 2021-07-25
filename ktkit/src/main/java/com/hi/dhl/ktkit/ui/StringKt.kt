@file:OptIn(ExperimentalContracts::class)

package com.hi.dhl.ktkit.ui

import android.content.Context
import android.telephony.PhoneNumberUtils
import android.util.Patterns
import com.google.i18n.phonenumbers.PhoneNumberUtil
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/5/15
 *     desc  :
 * </pre>
 */

@SinceKotlin("1.3")
inline fun String?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }

    return this != null && !this.trim().equals("null", true) && this.trim().isNotEmpty()
}

inline fun String.isValidPhone(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.PHONE.matcher(this).matches()
}

/**
 * format Phone number
 *
 *  usage：
 *  val phontNumberStr = "044 668 18 00"
 *  phontNumberStr.formatPhoneNumber("CH")
 */
fun String.formatPhoneNumber(region: String): String? {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()
    val number = phoneNumberUtil.parse(this, region)
    if (!phoneNumberUtil.isValidNumber(number))
        return null
    return phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
}

inline fun String.isValidEmail(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

inline fun String.isIPAddress(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.IP_ADDRESS.matcher(this).matches()
}

inline fun String.isDomainName(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.DOMAIN_NAME.matcher(this).matches()
}
