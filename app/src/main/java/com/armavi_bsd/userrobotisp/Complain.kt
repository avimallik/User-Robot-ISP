package com.armavi_bsd.userrobotisp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.armavi_bsd.DateAndTime
import com.armavi_bsd.intentRoute.IntentRoute
import com.armavi_bsd.managers.ComplainPostManager
import com.armavi_bsd.userrobotisp.databinding.ActivityComplainBinding
import com.armavi_bsd.utills.LoginPrefKey
import com.armavi_bsd.viewModel.ComplainTemplateViewModel
import com.armavi_bsd.viewModel.EmployeeViewModel


class Complain : AppCompatActivity() {

    var intentRoute = IntentRoute()
    val dateAndTime: DateAndTime = DateAndTime()
    var loginPrefKey = LoginPrefKey()

    lateinit var complainPostManager: ComplainPostManager

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    lateinit var binding: ActivityComplainBinding
    private val viewModelComplainTemplate: ComplainTemplateViewModel by viewModels()
    private val viewModelEmployee: EmployeeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_complain)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_complain)

        //Complain manager assignment
        complainPostManager = ComplainPostManager(this)

        //SharedPreferences
        sharedPreferences = getSharedPreferences(loginPrefKey.prefUserCredential, MODE_PRIVATE)

        //Template Spinner
        val adapterTemplate = ArrayAdapter<String> (this, R.layout.spinner_item_complaintemplate)
        binding.spinnerComplainTemplate.adapter = adapterTemplate

        //Employee Spinner
        val adapterEmployee = ArrayAdapter<String> (this, R.layout.spinner_item_employee)
        binding.spinnerEmployee.adapter = adapterEmployee

        viewModelComplainTemplate.templates.observe(this, { templates ->
            adapterTemplate.clear()
            adapterTemplate.addAll(templates.map { it.template })
            adapterTemplate.notifyDataSetChanged()
        })

        //Template live data
        binding.spinnerComplainTemplate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val template = viewModelComplainTemplate.templates.value?.get(position)
                template?.let {
//                    Toast.makeText(this@Complain, "ID: ${it.id}, Template: ${it.template}", Toast.LENGTH_SHORT).show()
                    binding.tempTemplateID.setText(it.id)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        viewModelComplainTemplate.loadTemplates()
        //End of template spinner live data reveal

        //Employee live data
        viewModelEmployee.employee.observe(this, { employee ->
            adapterEmployee.clear()
            adapterEmployee.addAll(employee.map { it.employee_name })
            adapterEmployee.notifyDataSetChanged()
        })

        binding.spinnerEmployee.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val employee = viewModelEmployee.employee.value?.get(position)
                employee?.let {
//                    Toast.makeText(this@Complain, "ID: ${it.id}, Employee: ${it.employee_name}", Toast.LENGTH_SHORT).show()
                    binding.tempEmployeeIDInput.setText(it.id)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        viewModelEmployee.loadEmployee()
        //End of employee spinner live data reveal

        binding.addComplainBtn.setOnClickListener {
//            Toast.makeText(applicationContext, dateAndTime.dateAndTime(this), Toast.LENGTH_SHORT).show()

            val tempDetails: String = binding.complainDetailsInput.text.toString().trim()
            val tempNote: String = binding.complainNoteInput.text.toString().trim()
//            val tempEmployeeId: String = binding.tempEmployeeIDInput.text.toString().trim()
            val tempDateTime: String = dateAndTime.dateAndTime(this)
            val tempCustomerId: String? = sharedPreferences.getString(loginPrefKey.prefAgId, "")
            val tempEmployeeId: String = "0"

            if (tempCustomerId != null) {
                complainPostManager.complainPost(tempCustomerId, tempDetails, tempNote, tempEmployeeId, tempDateTime)
            }
        }

        binding.complainViewIntentBtn.setOnClickListener {
            intentRoute.intentComplainView(this)
        }
    }
}