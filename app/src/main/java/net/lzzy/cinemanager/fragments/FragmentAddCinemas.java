package net.lzzy.cinemanager.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityPicker;

import net.lzzy.cinemanager.R;
import net.lzzy.cinemanager.models.Cinema;
import net.lzzy.cinemanager.models.CinemaFactory;

import java.util.List;

/**
 * Created by lzzy_gxy on 2019/3/27.
 * Description:
 */
public class FragmentAddCinemas extends BaseFragment {

    private LinearLayout layoutAddCinema;
    private ListView lv;
    private List<Cinema> cinemas;
    private CinemaFactory factory;
    private TextView tvArea;
    private EditText edtName;
    private String province = "广西壮族自治区";
    private String city = "柳州市";
    private String area = "鱼峰区";

    @Override
    protected void populate() {
        lv = find(R.id.activity_cinema_lv);
        tvArea = find(R.id.fragment_add_tv_area);
        edtName = find(R.id.fragment_add_cinema_edt_name);
        showDialog();
        bindList();
    }

    private void bindList() {
        factory = CinemaFactory.getInstance();
        cinemas = factory.get();
    }

    private void showDialog() {
        find(R.id.fragment_add_cinema_btn_cancel)
                .setOnClickListener(v -> {

                });
        find(R.id.fragment_add_cinema_layout_area).setOnClickListener(v -> {
            JDCityPicker cityPicker = new JDCityPicker();
            cityPicker.init(getActivity());
            cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                @Override
                public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                    FragmentAddCinemas.this.province = province.getName();
                    FragmentAddCinemas.this.city = city.getName();
                    FragmentAddCinemas.this.area = district.getName();
                    String loc = province.getName() + city.getName() + district.getName();
                    tvArea.setText(loc);
                }

                @Override
                public void onCancel() {

                }
            });
            cityPicker.showCityPicker();
        });
        find(R.id.fragment_add_cinema_btn_save).setOnClickListener(v -> {
            String name = edtName.getText().toString();
            Cinema cinema = new Cinema();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getActivity(), "影院不为空", Toast.LENGTH_SHORT).show();
            } else {
                cinema.setName(name);
                cinema.setArea(area);
                cinema.setCity(city);
                cinema.setProvince(province);
                cinema.setLocation(tvArea.getText().toString());
                //    adapter.add(cinema);
                edtName.setText("");
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_cinema;
    }
}
