package kg.geektech.weatherapp.ui.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.base.BaseFragment;
import kg.geektech.weatherapp.databinding.FragmentSearchBinding;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private NavController controller;


    @Override
    protected FragmentSearchBinding bind() {
        return FragmentSearchBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObserves() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void callRequest() {

        controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
viewBinding.btnOk.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        controller.navigate((NavDirections) SearchFragmentDirections
        .actionSearchFragmentToWeatherFragment(viewBinding.editSearch.getText().toString()));
    }
});

    }
}