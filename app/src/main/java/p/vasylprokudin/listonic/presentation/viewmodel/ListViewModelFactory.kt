package p.vasylprokudin.listonic.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import p.vasylprokudin.listonic.domain.repository.Repository
import p.vasylprokudin.listonic.util.RowInfoProvider

class ListViewModelFactory(
    private val rowInfoProvider: RowInfoProvider,
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(rowInfoProvider, repository) as T
    }
}