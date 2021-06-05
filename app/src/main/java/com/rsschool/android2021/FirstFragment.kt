package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValue: EditText? = null
    private var maxValue: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            val min = minValue!!.text.toString()
            val max = maxValue!!.text.toString()
            if (min == "") {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (max == "") {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (min.toInt() > max.toInt()) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val secondFragment: Fragment = SecondFragment.newInstance(min.toInt(), max.toInt())
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, secondFragment)
            transaction.commit()
        }

}

companion object {

    @JvmStatic
    fun newInstance(previousResult: Int): FirstFragment {
        val fragment = FirstFragment()
        val args = Bundle()
        args.putInt(PREVIOUS_RESULT_KEY, previousResult)
        fragment.arguments = args
        return fragment
    }

    private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
}
}