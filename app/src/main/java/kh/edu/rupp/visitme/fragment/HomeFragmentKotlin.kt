package kh.edu.rupp.visitme.fragment

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.visitme.activity.PlaceDetailActivity
import kh.edu.rupp.visitme.adapter.PlaceAdapter
import kh.edu.rupp.visitme.databinding.FragmentHomeBinding
import kh.edu.rupp.visitme.model.Place
import kh.edu.rupp.visitme.model.UiState
import kh.edu.rupp.visitme.model.UiStateStatus
import kh.edu.rupp.visitme.viewmodel.HomeViewModel

class HomeFragmentKotlin: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private var loadingDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("HomeFragmentKotlin", "onViewCreated")

        // Observe data
        viewModel.nearMePlacesUiState.observe(viewLifecycleOwner){ uiState ->
            onNearMePlaceResponse(uiState)
        }

        // Load home data
        viewModel.loadHomeData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun onNearMePlaceResponse(uiState: UiState<List<Place>>) {
        when(uiState.status){
            UiStateStatus.loading -> showLoading()
            UiStateStatus.error -> {
                hideLoading()
                showAlertDialog(uiState.message ?: "Unexpected error.")
            }
            UiStateStatus.success -> {
                hideLoading()
                displayNearMePlaces(uiState.data!!)
            }
        }
    }

    private fun displayNearMePlaces(places: List<Place>) {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = PlaceAdapter(places)
        adapter.setListener { index ->
            val place = adapter.getItem(index)
            startPlaceDetailActivity(place)
        }
        binding.rclNearMe.layoutManager = layoutManager
        binding.rclNearMe.adapter = adapter
    }

    private fun startPlaceDetailActivity(place: Place) {
        val intent = PlaceDetailActivity.newIntent(requireContext(), place)
        startActivity(intent)
    }

    private fun showLoading() {
        loadingDialog = ProgressDialog(requireContext())
        loadingDialog!!.show()
    }

    private fun hideLoading(){
        loadingDialog?.hide()
        loadingDialog = null
    }

    private fun showAlertDialog(message: String) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Alert")
        dialog.setMessage(message)
        dialog.setPositiveButton("OK"
        ) { p0, p1 ->

        }
        dialog.show()
    }

}