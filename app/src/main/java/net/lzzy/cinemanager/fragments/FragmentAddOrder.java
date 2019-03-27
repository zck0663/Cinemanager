package net.lzzy.cinemanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.lzzy.cinemanager.R;

/**
 * Created by lzzy_gxy on 2019/3/27.
 * Description:
 */
public class FragmentAddOrder extends BaseFragment {
    @Override
    protected void populate() {
        EditText edt = find(R.id.fragment_add_order_edt);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_order;
    }
}
