package p.vasylprokudin.listonic.util.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtil {

    fun replaceFragmentAllowingStateLoss(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        @IdRes containerViewId: Int,
        addToBackStack: Boolean
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerViewId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun findFragment(fragmentManager: FragmentManager, @IdRes fragmentContainerId: Int): Fragment? = fragmentManager.findFragmentById(fragmentContainerId)
}