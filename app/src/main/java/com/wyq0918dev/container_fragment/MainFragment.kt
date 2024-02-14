package com.wyq0918dev.container_fragment

import androidx.fragment.app.Fragment

class MainFragment : ContainerFragment() {

    class DemoFragment : Fragment()

    override fun onCreateFragment(): Fragment {
        return DemoFragment()
    }
}