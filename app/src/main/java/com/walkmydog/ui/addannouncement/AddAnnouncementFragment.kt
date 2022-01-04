package com.walkmydog.ui.addannouncement

import com.walkmydog.ui.addannouncement.AddAnnouncementViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.walkmydog.R
import androidx.lifecycle.ViewModelProvider
import com.walkmydog.ui.addannouncement.AddAnnouncementFragment

class AddAnnouncementFragment : Fragment() {
    private var mViewModel: AddAnnouncementViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_announcement_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(AddAnnouncementViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(): AddAnnouncementFragment {
            return AddAnnouncementFragment()
        }
    }
}