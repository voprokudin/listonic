package p.vasylprokudin.listonic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import p.vasylprokudin.listonic.presentation.viewmodel.DetailsViewModel.ScreenState.ShowRealTime
import p.vasylprokudin.listonic.data.db.entities.RowItem
import p.vasylprokudin.listonic.domain.repository.Repository

class DetailsViewModel(
    private val repository: Repository
) : ViewModel() {

    val listItem: LiveData<RowItem> by lazy { mutableListItem }

    private val mutableListItem: MutableLiveData<RowItem> = MutableLiveData()

    val screenState: LiveData<ScreenState> by lazy { mutableScreenState }

    private val mutableScreenState: MutableLiveData<ScreenState> = MutableLiveData()

    fun getDetails(rowId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val rowItem = repository.getRowById(rowId)
            launch(Dispatchers.Main) {
                mutableListItem.value = rowItem
                mutableScreenState.value = ShowRealTime(rowItem.createdDate)
            }
        }
    }

    sealed class ScreenState {
        class ShowRealTime(val createdTime: Long) : ScreenState()
    }
}