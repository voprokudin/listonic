package p.vasylprokudin.listonic.presentation.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import p.vasylprokudin.listonic.R
import p.vasylprokudin.listonic.presentation.view.DetailsFragment
import p.vasylprokudin.listonic.util.fragment.FragmentUtil

class Navigator(
    private val fragmentUtil: FragmentUtil
) {

    companion object {
        private const val FRAGMENT_CONTAINER = R.id.details_container
    }

    private lateinit var fragmentManager: FragmentManager

    fun showDetailsFragment(activity: AppCompatActivity, id: Int) {
        fragmentManager = activity.supportFragmentManager
        if (isFragmentAlreadyShown<DetailsFragment>()) return

        fragmentUtil.replaceFragmentAllowingStateLoss(
            fragmentManager = fragmentManager,
            fragment = DetailsFragment.newInstance(id),
            containerViewId = FRAGMENT_CONTAINER,
            addToBackStack = true
        )
    }

    private inline fun <reified T> isFragmentAlreadyShown() = fragmentUtil.findFragment(fragmentManager, FRAGMENT_CONTAINER) is T
}