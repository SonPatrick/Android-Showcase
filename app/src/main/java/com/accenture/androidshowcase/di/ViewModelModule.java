package com.accenture.androidshowcase.di;

import com.accenture.androidshowcase.ui.act_bottombar.frag.BottomBarViewModel;
import com.accenture.androidshowcase.ui.act_rest.frag.RestViewModel;
import com.accenture.androidshowcase.viewmodel.ViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract ViewModelProvider.Factory viewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(BottomBarViewModel.class)
    abstract ViewModel bottomBarViewModel(BottomBarViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RestViewModel.class)
    abstract ViewModel restViewModel(RestViewModel viewModel);

}