package com.armavi_bsd.fragment

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.userrobotisp.databinding.FragmentUserBioBinding
import com.armavi_bsd.utills.LoginPrefKey

class FragmentUserBio : Fragment() {

    lateinit var binding: FragmentUserBioBinding
    lateinit var sharedPreferences: SharedPreferences
    var loginPrefKey = LoginPrefKey()
    lateinit var agentStatusCheck: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_bio, container, false)

        sharedPreferences = requireActivity().getSharedPreferences(loginPrefKey.prefUserCredential, MODE_PRIVATE)

        binding.fragmentAgentName.text = sharedPreferences.getString(loginPrefKey.prefAgName, "")
        binding.fragmentAgentCusId.text = sharedPreferences.getString(loginPrefKey.prefCusId, "")
        binding.fragmentAgentPhonenumber.text = sharedPreferences.getString(loginPrefKey.prefAgMobileNo, "")

        agentStatusCheck = sharedPreferences.getString(loginPrefKey.prefAgStatus, "").toString()

        if(agentStatusCheck == "1"){
            binding.fragmentAgentStatus.text = "Active"
        }else if(agentStatusCheck == "0"){
            binding.fragmentAgentStatus.text = "Inactive"
        }

        return binding.root
    }
}