/*
 * Copyright 2018 UGURCAN YILDIRIM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mobi.mergen.androidshowcase.ui.base;

import android.os.Bundle;
import android.os.Handler;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.support.DaggerAppCompatActivity;
import mobi.mergen.androidshowcase.R;
import mobi.mergen.androidshowcase.common.Navigator;

public abstract class BaseActivity<V extends ViewDataBinding>
        extends DaggerAppCompatActivity {

    private boolean backToExitClickedOnce = false;

    private V binding;

    public abstract int layoutRes();

    public abstract boolean doubleClickToExitEnabled();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("onCreate()", getClass().getSimpleName());

        binding = DataBindingUtil.setContentView(this, layoutRes());
    }

    public V getBinding() {
        return binding;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("onDestroy()", getClass().getSimpleName());

        binding.unbind();
        binding = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("onStart()", getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("onStop()", getClass().getSimpleName());
    }

    @Override
    public void onBackPressed() {
        if (!doubleClickToExitEnabled() || backToExitClickedOnce) {
            ToastUtils.cancel();
            Navigator.finish(this);
            return;
        }

        backToExitClickedOnce = true;
        ToastUtils.showShort(R.string.text_click_back_again);

        new Handler().postDelayed(() -> backToExitClickedOnce = false,
                2000);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
