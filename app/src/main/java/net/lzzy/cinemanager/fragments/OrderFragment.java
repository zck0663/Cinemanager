package net.lzzy.cinemanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.lzzy.cinemanager.R;
import net.lzzy.cinemanager.models.Cinema;
import net.lzzy.cinemanager.models.CinemaFactory;
import net.lzzy.cinemanager.models.Order;
import net.lzzy.cinemanager.models.OrderFactory;
import net.lzzy.sqllib.GenericAdapter;
import net.lzzy.sqllib.ViewHolder;

import java.util.List;

/**
 * Created by lzzy_gxy on 2019/3/26.
 * Description:
 */
public class OrderFragment extends BaseFragment {
    private ListView lv;
    private List<Order> orders;
    private OrderFactory factory = OrderFactory.getInstance();
    private GenericAdapter<Order> adapter;


    //未重构前的写法
//    public OrderFragment(){}
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//       View view=inflater.inflate(R.layout.fragment_order,null);
//       TextView textView=view.findViewById(R.id.fragment_order_tv);
//        return view;
//    }


    @Override
    protected void populate() {

        lv = find(R.id.fragment_order_lv);
        View empty = find(R.id.activity_main_tv_none);
        lv.setEmptyView(empty);
        orders = factory.get();
        adapter = new GenericAdapter<Order>(getContext(), R.layout.order_item, orders) {
            @Override
            public void populate(ViewHolder viewHolder, Order order) {
                String location = String.valueOf(CinemaFactory.getInstance().getById(order.getCinemaId().toString()));
                viewHolder.setTextView(R.id.order_items_name, order.getMovie())
                        .setTextView(R.id.order_items_location, location);
            }

            @Override
            public boolean persistInsert(Order order) {
                return factory.addOrder(order);
            }

            @Override
            public boolean persistDelete(Order order) {
                return factory.delete(order);
            }
        };
        lv.setAdapter(adapter);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_order;
    }

    @Override
    public void search(String kw) {

    }
}
