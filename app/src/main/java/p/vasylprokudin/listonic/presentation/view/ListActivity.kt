package p.vasylprokudin.listonic.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_list.buttonAdd
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import p.vasylprokudin.listonic.R
import org.kodein.di.generic.instance
import p.vasylprokudin.listonic.data.db.entities.RowItem
import p.vasylprokudin.listonic.databinding.ActivityListBinding
import p.vasylprokudin.listonic.presentation.navigation.Navigator
import p.vasylprokudin.listonic.presentation.view.list.listener.ItemClickedListener
import p.vasylprokudin.listonic.presentation.viewmodel.ListViewModel
import p.vasylprokudin.listonic.presentation.viewmodel.ListViewModelFactory

class ListActivity : AppCompatActivity(), KodeinAware, ItemClickedListener {

    override val kodein by closestKodein()

    private val viewModelFactory by instance<ListViewModelFactory>()

    private val navigator by instance<Navigator>()

    private lateinit var binding: ActivityListBinding

    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initViewModel()
        initView()
    }

    override fun onItemRowClicked(id: Int) {
        viewModel.updateCounter(id)
        navigator.showDetailsFragment(this, id)
    }

    override fun onItemDeleteClicked(item: RowItem) {
        viewModel.deleteRow(item)
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.listener = this
        viewModel.listItems.observe(this, Observer { binding.rows = it })
        buttonAdd.setOnClickListener { viewModel.generateNewRow() }
        maybeShowInitialList()
    }

    private fun maybeShowInitialList() {
        viewModel.maybeShowInitialList()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)
    }
}
