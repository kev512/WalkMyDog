package com.walkmydog.ui.messages

import com.walkmydog.ui.messages.MessageViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.walkmydog.R
import androidx.lifecycle.ViewModelProvider
import com.walkmydog.ui.messages.MessageFragment

class MessageFragment : Fragment() {
    private var mViewModel: MessageViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.message_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(): MessageFragment {
            return MessageFragment()
        }
    }
}