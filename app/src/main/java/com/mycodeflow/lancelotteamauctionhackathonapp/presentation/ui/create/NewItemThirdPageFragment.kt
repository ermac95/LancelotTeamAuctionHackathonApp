package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.NewItemViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewItemThirdPageFragment : BaseFragment() {

    private lateinit var backButton: ImageView
    private lateinit var datePickButton: Button
    private lateinit var fieldDate: TextView
    private lateinit var timePickButton: Button
    private lateinit var fieldTime: TextView
    private lateinit var postButton: Button
    private var listener: HomeScreenActions? = null
    private val cal = Calendar.getInstance()
    private var dateOfAuction: String? = null
    private var timeOfAuction: String? = null

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory
    lateinit var viewModel : NewItemViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeScreenActions){
            listener = context
        }
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_item_third_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
        setUpViewModel()
    }

    private fun setupViews(view: View){
        //back button initialization
        backButton = view.findViewById(R.id.back_button)
        datePickButton = view.findViewById(R.id.date_pick_button)
        fieldDate = view.findViewById(R.id.field_date)
        timePickButton = view.findViewById(R.id.time_pick_button)
        fieldTime = view.findViewById(R.id.field_time)
        postButton = view.findViewById(R.id.post_button)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewItemViewModel::class.java)
    }

    private fun setupListeners() {
        //back button navigation back
        backButton.setOnClickListener{
            listener?.backPageTransaction()
        }
        //date picker
        datePickButton.setOnClickListener{
            DatePickerDialog(requireContext(),
                getDateListener(),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))
                .show()
        }
        //time picker
        timePickButton.setOnClickListener {
            TimePickerDialog(requireContext(),
                getTimeListener(),
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true)
                .show()

        }
        //post button
        postButton.setOnClickListener{
            val dateText = fieldDate.text.toString()
            val timeText = fieldTime.text.toString()
            when {
                dateText.contains("Select") -> {
                    fieldDate.error = "Select date"
                    return@setOnClickListener
                }
                timeText.contains("Select") -> {
                    fieldTime.error = "Select time"
                    return@setOnClickListener
                }
                else -> {
                    viewModel.setSecondPageDataAndPost(dateText, timeText)
                    Log.d("viewModel", "timeText - $dateText, dateText - $timeText")
                    listener?.forwardPageTransaction(FragsNav.AS)
                }
            }
        }
    }

    private fun getDateListener(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setDateSelected()
        }
    }

    private fun setDateSelected() {
        val dateFormat = "dd MMMM yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.US)
        dateOfAuction = sdf.format(cal.time)
        fieldDate.text = dateOfAuction
    }

    private fun getTimeListener(): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            setTimeSelected()
        }
    }

    private fun setTimeSelected(){
        val timeFormat = "HH:mm"
        val sdf = SimpleDateFormat(timeFormat, Locale.US)
        timeOfAuction = sdf.format(cal.time)
        fieldTime.text = timeOfAuction
    }

    companion object {
        fun newInstance() = NewItemThirdPageFragment()
    }
}