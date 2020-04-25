package p.vasylprokudin.listonic

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import p.vasylprokudin.listonic.data.db.ApplicationDatabase
import p.vasylprokudin.listonic.data.repository.RepositoryImpl
import p.vasylprokudin.listonic.presentation.navigation.Navigator
import p.vasylprokudin.listonic.presentation.viewmodel.DetailsViewModelFactory
import p.vasylprokudin.listonic.presentation.viewmodel.ListViewModelFactory
import p.vasylprokudin.listonic.util.RowInfoProvider
import p.vasylprokudin.listonic.util.fragment.FragmentUtil

class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { ApplicationDatabase(this@App) }

        bind() from provider { ListViewModelFactory(instance(), instance()) }

        bind() from provider { DetailsViewModelFactory(instance()) }

        bind() from singleton { RepositoryImpl(instance()) }

        bind() from singleton { RowInfoProvider() }

        bind() from singleton { FragmentUtil() }

        bind() from singleton { Navigator(instance()) }
    }
}