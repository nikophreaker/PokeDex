package com.nikoprayogaw.pokedex.view

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nikoprayogaw.pokedex.databinding.FragmentSearchBinding


class SearchFragment : BottomSheetDialogFragment() {
    private lateinit var viewBinding: FragmentSearchBinding
    interface OnInputListener {
        fun sendInput(input: String?)
    }
    var mOnInputListener: OnInputListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSearchBinding.inflate(inflater, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mOnInputListener?.sendInput(viewBinding.query.text.toString())
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        mOnInputListener?.sendInput(viewBinding.query.text.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnInputListener = activity as OnInputListener?
        } catch (e: ClassCastException) {
            Log.e(
                "TAG", "onAttach: ClassCastException: "
                        + e.message
            )
        }
    }
}
