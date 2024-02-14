package com.wyq0918dev.container_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

abstract class ContainerFragment : Fragment() {

    private lateinit var mFragmentManager: FragmentManager
    private var mFragment: Fragment? = null

    abstract fun onCreateFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragmentManager = childFragmentManager
        mFragment = mFragmentManager.findFragmentByTag(tagContainerFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentContainerView(
        context = requireContext()
    ).apply {
        id = R.id.fragment_container
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = onCreateFragment()
        if (mFragment == null) {
            if (!fragment.isAdded && mFragmentManager.findFragmentByTag(tagContainerFragment) == null) {
                mFragmentManager.commit(
                    allowStateLoss = false,
                    body = {
                        mFragment = fragment
                        add(
                            view.id, fragment, tagContainerFragment
                        )
                    }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mFragment = null
    }

    companion object {
        const val tagContainerFragment = "container_fragment"
    }
}