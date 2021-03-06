package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.ReminderBroadcast
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.DetailsViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AdvertisementDetailFragment : BaseFragment() {

    private var listener: HomeScreenActions? = null
    private lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    private lateinit var ivBackButton: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvInitialBetVal: TextView
    private lateinit var tvBetStepVal: TextView
    private lateinit var tvDescriptionVal: TextView
    private lateinit var tvStartDate: TextView
    private lateinit var tvEndOfRegistrationDate: TextView
    private lateinit var btnRegister: Button
    private lateinit var viewPagerContainer: LinearLayout
    private lateinit var adsImages: ViewPager2
    private lateinit var state: TextView

    private var adId: String? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeScreenActions) {
            listener = context
        }
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advertisement_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
        setUpViewModel()
    }

    override fun onStart() {
        super.onStart()
        adId = arguments?.getString(KEY_AD_ID)
        Log.d("myLogs", "adId = $adId")
        adId?.let { viewModel.loadAd(it) }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setupViews(view: View) {
        ivBackButton = view.findViewById(R.id.back_button)
        tvTitle = view.findViewById(R.id.tv_title)
        tvInitialBetVal = view.findViewById(R.id.tv_initialBetValue)
        tvBetStepVal = view.findViewById(R.id.tv_betStepValue)
        tvDescriptionVal = view.findViewById(R.id.tv_descriptionValue)
        tvStartDate = view.findViewById(R.id.tv_auctionStartDate)
        tvEndOfRegistrationDate = view.findViewById(R.id.tv_end_of_registration_date)
        btnRegister = view.findViewById(R.id.btn_auction_reg_or_betUp)
        viewPagerContainer = view.findViewById(R.id.viewpager_container)
        adsImages = view.findViewById(R.id.ads_images)
        state = view.findViewById(R.id.state)
    }

    private fun setupListeners() {
        //back button navigation back
        ivBackButton.setOnClickListener {
            listener?.backPageTransaction()
        }
        btnRegister.setOnClickListener {
            adId?.let { id ->
                viewModel.registerOnAd(id)
                val intent = Intent(requireContext(), ReminderBroadcast::class.java)

                val contentUri = "com.mycodeflow.lancelotteamauctionhackathonapp://ad/${id}".toUri()
                intent.setAction(Intent.ACTION_VIEW)
                    .setData(contentUri)
                val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)
                val alarmManager: AlarmManager =
                    requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

                val currentTime = System.currentTimeMillis()

                val tenSeconds = 5000L

                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    currentTime + tenSeconds,
                    pendingIntent
                )
            }
            btnRegister.text = getString(R.string.registration_text)
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        viewModel.ad.observe(this.viewLifecycleOwner, {
            showAd(it)
            Log.d("myLogs", it.toString())
        })
    }

    private fun showAd(ad: Advertisement) {
        adsImages.adapter = AdvDetailsViewPagerAdapter().also { it.bindImages(ad.images) }
        tvTitle.text = ad.title
        tvInitialBetVal.text = ad.price.toInt().toString()
        tvBetStepVal.text = ad.betStep.toInt().toString()
        tvDescriptionVal.text = ad.description
        val startDate = requireContext().getString(R.string.tv_date_pattern, ad.date, ad.time)
        tvStartDate.text = startDate
        val endRegDate = getEndRegistrationTime(ad.date)
        val endDate = requireContext().getString(R.string.tv_date_pattern, endRegDate, ad.time)
        tvEndOfRegistrationDate.text = endDate

        if (startDate == "") {
            state.text = "In progress"
        } else {
            state.text = "In waiting"
        }


        btnRegister.visibility = if (firebaseAuth.currentUser.uid != ad.ownerUid) View.VISIBLE else View.INVISIBLE

    }

    private fun getEndRegistrationTime(date: String): String {
        val sdf: DateFormat = SimpleDateFormat("dd MMMM yyyy")
        val date: Date = sdf.parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        val calDayAgo = calendar.time
        return sdf.format(calDayAgo)
    }

    companion object {
        fun newInstance(adId: String): AdvertisementDetailFragment {
            val args = Bundle()
            args.putString(KEY_AD_ID, adId)
            val fragment = AdvertisementDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

const val KEY_AD_ID = "ad_id"