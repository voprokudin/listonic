package p.vasylprokudin.listonic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import p.vasylprokudin.listonic.data.db.entities.RowItem
import p.vasylprokudin.listonic.domain.repository.Repository
import p.vasylprokudin.listonic.util.RowInfoProvider
import java.util.Date

class ListViewModel(
    private val rowInfoProvider: RowInfoProvider,
    private val repository: Repository
) : ViewModel() {

    val listItems: LiveData<List<RowItem>> by lazy { mutableListItems }

    private val mutableListItems: MutableLiveData<List<RowItem>> = MutableLiveData<List<RowItem>>().apply { value = emptyList() }

    fun maybeShowInitialList() {
        CoroutineScope(IO).launch {
            val list = repository.getAllRows()
            launch(Main) {
                mutableListItems.value = list
            }
        }
    }

    fun generateNewRow() {
        val color = rowInfoProvider.provideColor()
        val name = rowInfoProvider.provideRandomName()
        CoroutineScope(IO).launch {
            saveToLocalDatabase(color, name)
            val list = repository.getAllRows()
            launch(Main) {
                mutableListItems.value = list
            }
        }
    }

    fun deleteRow(item: RowItem) {
        CoroutineScope(IO).launch {
            repository.deleteRow(item)
            val list = repository.getAllRows()
            launch(Main) {
                mutableListItems.value = list
            }
        }
    }

    fun updateCounter(rowId: Int) {
        CoroutineScope(IO).launch {
            val oldRow = repository.getRowById(rowId)
            val newRow = oldRow.copy(counter = oldRow.counter + 1)
            repository.updateRow(newRow)
        }
    }

    private fun saveToLocalDatabase(color: String, name: String) {
        val dateLong = Date(System.currentTimeMillis()).time
        val rowItem = RowItem(
            name = name,
            color = color,
            createdDate = dateLong
        )
        repository.insertRow(rowItem)
    }
}