package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.ItemImage
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.NewItemViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav
import javax.inject.Inject

class NewItemFirstPageFragment : BaseFragment() {

    private var listener: HomeScreenActions? = null
    private lateinit var backButton: ImageView
    private lateinit var nextButton: Button
    private lateinit var pictureRecycler: RecyclerView
    private lateinit var sliderAdapter: NewItemImageSlideAdapter

    private lateinit var title : EditText
    private lateinit var bet : EditText
    private lateinit var bet_step : EditText

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel : NewItemViewModel

    private val gallery = object : NewItemImageSlideAdapter.PicturesGallery {
        override fun openGallery() {
            selectPicture.launch("image/*")
        }
    }

    private val selectPicture =
        registerForActivityResult(ActivityResultContracts.GetContent()) { url ->
            setImageSelected(url)
        }

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
        return inflater.inflate(R.layout.fragment_new_item_first_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewItemViewModel::class.java)
    }

    private fun setupViews(view: View) {
        //back button initialization
        backButton = view.findViewById(R.id.back_button)
        nextButton = view.findViewById(R.id.continue_button)
        //field initialization
        title = view.findViewById(R.id.field_title)
        bet = view.findViewById(R.id.field_bet)
        bet_step = view.findViewById(R.id.field_bet_step)
        //initializing slider
        pictureRecycler = view.findViewById(R.id.pictures_slider)
        pictureRecycler.adapter = setupAdapter()
    }

    private fun setupAdapter(): NewItemImageSlideAdapter {
        sliderAdapter = NewItemImageSlideAdapter(gallery)
        sliderAdapter.addNewItem(ItemImage())
        return sliderAdapter
    }

    private fun setImageSelected(url: Uri) {
        val newItem = ItemImage(url)
        sliderAdapter.addNewItem(newItem)
    }

    private fun setupListeners() {
        //back button navigation back
        backButton.setOnClickListener {
            listener?.backPageTransaction()
        }
        nextButton.setOnClickListener {
            val images = sliderAdapter.getImages()
            val titleText = title.text.toString()
            val betText = bet.text.toString()
            val betStepText = bet_step.text.toString()
            when {
                TextUtils.isEmpty(titleText) -> {
                    title.error = "Please enter value"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(betText) -> {
                    bet.error = "Please enter value"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(betStepText) -> {
                    bet_step.error = "Please enter value"
                    return@setOnClickListener
                }
                else -> {
                    viewModel.setFirstPageData(
                        images,
                        titleText,
                        betText.toFloat(),
                        betStepText.toFloat()
                    )
                    Log.d("viewModel",
                        "titleText - $titleText, betText - $betText, betStepText - $betStepText")
                    listener?.forwardPageTransaction(FragsNav.NI2)
                }
            }
        }
    }

    companion object {
        fun newInstance() = NewItemFirstPageFragment()
    }
}