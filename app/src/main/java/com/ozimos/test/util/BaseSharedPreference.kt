package com.ozimos.test.util

import android.content.Context
import androidx.core.content.edit

open class BaseSharedPreference(
    private val mContext: Context,
    spName: String
) {

    private val mSharedPreference = SecurityHelper.getSharedPreference(mContext, spName)
    private fun getResourceString(resource: Int): String {
        return mContext.getString(resource)
    }

    protected fun saveContentString(mKeyName: Int, mData: String) {
        val stringResource = getResourceString(mKeyName)
        mSharedPreference.edit {
            putString(stringResource, mData)
        }

    }

    protected fun getContentString(mKeyName: Int): String {
        return mSharedPreference.getString(getResourceString(mKeyName), "") ?: ""
    }

    protected fun clearData() {
        mSharedPreference.edit {
            clear()
        }
    }

}
