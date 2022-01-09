package com.walkmydog.ui.addannouncement

import android.content.Intent
import com.walkmydog.ui.addannouncement.AddAnnouncementViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.walkmydog.R
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.walkmydog.ui.addannouncement.AddAnnouncementFragment
import com.walkmydog.ui.addannouncement.addnewannouncement.NewAnnouncementActivity

class AddAnnouncementFragment : Fragment() {
    private var mViewModel: AddAnnouncementViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.add_announcement_fragment, container, false)

        //create new announcement btn
        val addNewAnnouncementActivity: FloatingActionButton = rootView.findViewById(R.id.addNewAnnouncementActivityBtn)
        addNewAnnouncementActivity.setOnClickListener {
            startActivity(Intent(context, NewAnnouncementActivity::class.java))
        }

        return rootView
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