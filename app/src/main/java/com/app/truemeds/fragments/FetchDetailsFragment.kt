package com.app.truemeds.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.truemeds.adapter.AritcleAdapter
import com.app.truemeds.databinding.FragmentListBinding
import com.app.truemeds.model.Result
import com.app.truemeds.model.TrueMedsModel
import com.app.truemeds.utils.ApiState
import com.app.truemeds.viewmodels.FetchDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class FetchDetailsFragment : Fragment() {

    private val viewModel: FetchDetailsViewModel by viewModels()

    private lateinit var articleAdapter: AritcleAdapter

    private lateinit var binding: FragmentListBinding

    lateinit var navController: NavController


    private lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initRecyclerview()

        viewModel.getDetails()
        startTimer()

        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Fetching data....")

        lifecycleScope.launchWhenStarted {
            viewModel.articaledetails.collect {
                when (it) {
                    is ApiState.Loading -> {
                        progressDialog.show()
                    }
                    is ApiState.Failure -> {
                        progressDialog.hide()
                    }
                    is ApiState.Success -> {
                        progressDialog.hide()
                        articleAdapter.setData(it.data)
                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }

        binding.reset.setOnClickListener {
            countDownTimer.cancel()
            startTimer()
            viewModel.getDetails()
        }

    }

    fun startTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                binding.time.text = hms
            }

            override fun onFinish() {
                viewModel.getDetails()
            }
        }
        countDownTimer.start()
    }


    private fun initRecyclerview() {
        articleAdapter = AritcleAdapter(TrueMedsModel(Result(ArrayList(), ArrayList())), requireContext())

        binding.articleList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articleAdapter
        }
    }

}