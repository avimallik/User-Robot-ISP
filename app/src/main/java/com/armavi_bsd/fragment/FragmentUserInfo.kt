package com.armavi_bsd.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.userrobotisp.databinding.FragmentBillDetailsBinding
import com.armavi_bsd.userrobotisp.databinding.FragmentUserBioBinding
import com.armavi_bsd.userrobotisp.databinding.FragmentUserInfoBinding
import com.armavi_bsd.utills.LoginPrefKey

class FragmentUserInfo : Fragment() {

    lateinit var binding: FragmentUserInfoBinding
    lateinit var sharedPreferences: SharedPreferences
    var loginPrefKey = LoginPrefKey()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)

        sharedPreferences = requireActivity().getSharedPreferences(loginPrefKey.prefUserCredential,
            Context.MODE_PRIVATE
        )

        binding.infoCustomerID.text = sharedPreferences.getString(loginPrefKey.prefCusId, "")
        binding.infoCustomerName.text = sharedPreferences.getString(loginPrefKey.prefAgName, "")
        binding.infoOfficeAddress.text = sharedPreferences.getString(loginPrefKey.prefAgOfficeAddress, "")
        binding.infoCustomerMobileNo.text = sharedPreferences.getString(loginPrefKey.prefAgMobileNo, "")
        binding.infoCustomerMB.text = sharedPreferences.getString(loginPrefKey.prefMb, "")
        binding.infoCustomerZoneName.text = sharedPreferences.getString(loginPrefKey.prefZoneName, "")
        binding.infoCustomerIP.text = sharedPreferences.getString(loginPrefKey.prefIp, "")
        binding.infoConnectionDate.text = sharedPreferences.getString(loginPrefKey.prefConnectionDate, "")
        binding.infoCollectionDate.text = sharedPreferences.getString(loginPrefKey.prefCDate, "")

        return binding.root
    }
}