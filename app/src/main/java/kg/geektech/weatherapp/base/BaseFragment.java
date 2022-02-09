package kg.geektech.weatherapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public  abstract class BaseFragment <VB extends ViewBinding> extends Fragment {

    protected VB viewBinding;
    protected abstract VB bind();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = bind();
        return viewBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        setupObserves();
        setListeners();
        callRequest();
    }

    protected abstract void setupObserves();

    protected abstract void  setListeners();

    protected abstract void setupViews();

    protected abstract void callRequest();
}
