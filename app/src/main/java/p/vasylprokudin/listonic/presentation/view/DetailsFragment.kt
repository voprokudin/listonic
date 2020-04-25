package p.vasylprokudin.listonic.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import p.vasylprokudin.listonic.presentation.viewmodel.DetailsViewModel.ScreenState.ShowRealTime
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import p.vasylprokudin.listonic.Constants.BundleArgs.Companion.ROW_ID
import p.vasylprokudin.listonic.databinding.FragmentDetailsBinding
import p.vasylprokudin.listonic.extensions.argNotNull
import p.vasylprokudin.listonic.presentation.viewmodel.DetailsViewModel
import p.vasylprokudin.listonic.presentation.viewmodel.DetailsViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date

class DetailsFragment : Fragment(), KodeinAware {

    companion object {
        @JvmStatic
        fun newInstance(id: Int): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle().apply {
                putInt(ROW_ID, id)
            }
            return fragment.apply { arguments = args }
        }
    }

    override val kodein by closestKodein()

    private val viewModelFactory by instance<DetailsViewModelFactory>()

    private lateinit var viewModel: DetailsViewModel

    private lateinit var binding: FragmentDetailsBinding

    private val rowId: Int by argNotNull(ROW_ID)

    private var diff: Long = 0L

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("mm:ss")

    private var mActive = false

    private var mHandler: Handler = Handler()

    private var createdTime: Long = 0

    private val mRunnable: Runnable = object : Runnable {
        override fun run() {
            if (mActive) {
                tvClock?.text = getTime()
                mHandler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetails(rowId)
        viewModel.listItem.observe(this, Observer { binding.rowDetail = it })
        viewModel.screenState.observe(this, ScreenActionObserver())
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    private fun showRealTime(createdTime: Long) {
        this.createdTime = createdTime
        startClock()
    }

    private fun getTime(): String {
        val currentTime = Date(System.currentTimeMillis()).time
        diff = currentTime - createdTime
        return sdf.format(Date(diff))
    }

    private fun startClock() {
        mActive = true
        mHandler.post(mRunnable)
    }

    private inner class ScreenActionObserver : Observer<DetailsViewModel.ScreenState> {
        override fun onChanged(screenAction: DetailsViewModel.ScreenState?) {
            screenAction ?: return

            when (screenAction) {
                is ShowRealTime -> showRealTime(screenAction.createdTime)
            }
        }
    }
}