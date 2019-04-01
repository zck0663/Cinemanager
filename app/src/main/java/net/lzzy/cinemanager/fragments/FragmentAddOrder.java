package net.lzzy.cinemanager.fragments;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import net.lzzy.cinemanager.R;
import net.lzzy.cinemanager.models.Cinema;
import net.lzzy.cinemanager.models.CinemaFactory;
import net.lzzy.cinemanager.models.Order;
import net.lzzy.cinemanager.models.OrderFactory;
import net.lzzy.simpledatepicker.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by lzzy_gxy on 2019/3/27.
 * Description:
 */
public class FragmentAddOrder extends BaseFragment implements OnFragmentInteractionListener {
    private OnFragmentInteractionListener listener;
    private TextView tvDate;
    private EditText etName;
    private Spinner spCinema;
    private ImageView imgQRCode;
    private EditText edtPrice;
    private List<Order> orders;
    private List<Cinema> cinemas;
    private OrderFactory factory = OrderFactory.getInstance();
    private CustomDatePicker picker;

    @Override
    protected void populate() {
        listener.hideSearch();
        tvDate = find(R.id.fragment_add_tv_Time);
        etName = find(R.id.fragment_add_order_edt_name);
        edtPrice = find(R.id.fragment_add_order_price);
        spCinema = find(R.id.fragment_add_order_spinner);
        imgQRCode = find(R.id.fragment_main_Img_code);

        bindList();
        addListener();
        showAndOrders();

    }

    private void showAndOrders() {
        cinemas = CinemaFactory.getInstance().get();
        orders = factory.get();
        spCinema.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, cinemas));
        find(R.id.fragment_add_order_Time).setOnClickListener(v -> picker.show(tvDate.getText().toString()));
        initDatePicker();
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tvDate.setText(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        String end = sdf.format(calendar.getTime());
        picker = new CustomDatePicker(getContext(), s -> tvDate.setText(s), now, end);
        picker.showSpecificTime(true);
        picker.setIsLoop(true);
    }


    private void addListener() {

    }

    private void bindList() {
        factory = OrderFactory.getInstance();
        orders = factory.get();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_order;
    }

    @Override
    public void search(String kw) {

    }

    @Override
    public void hideSearch() {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            listener.hideSearch();
        }
    }

    //4 赋值
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "必须实现OnFragmentInteractionListener");
        }
    }

    // 销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }
}
