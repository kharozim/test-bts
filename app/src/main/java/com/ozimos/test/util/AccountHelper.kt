package com.ozimos.test.util

import android.content.Context
import com.ozimos.test.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class AccountHelper @Inject constructor(
    @ApplicationContext
    mContext: Context
) : BaseSharedPreference(mContext, mContext.getString(R.string.preference_login)) {

    fun setAuthToken(token: String) = saveContentString(R.string.preference_login_token, token)

    val tokenAuth
        get() = getContentString(R.string.preference_login_token)

    val isLogin
        get() = tokenAuth.isNotEmpty()

    fun logOut() {
        clearData()
    }

}
