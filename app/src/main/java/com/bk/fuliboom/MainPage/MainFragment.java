package com.bk.fuliboom.MainPage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bk.fuliboom.Adapters.VideoRecyclerAdapter;
import com.bk.fuliboom.R;
import com.bk.fuliboom.Repository.Beans.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements  IMainView{

    private List<Result> data;
    private IMainPresenter mPresenter;
    private VideoRecyclerAdapter mAdapter;

//    @BindView(R.id.view_pager)
//    ViewPager mViewPager;

    @BindView(R.id.video_list)
    RecyclerView recyclerView;




    public MainFragment() {
        // Required empty public constructor

        mPresenter = new MainPresenterImpl(this);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        mAdapter = new VideoRecyclerAdapter(data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        mPresenter.getVideoList(300,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setOnItemClickListener(new VideoRecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClicked(int position, String url) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startActivity(Intent.createChooser(intent, "请选择浏览器"));
            }
        });
        recyclerView.setAdapter(mAdapter);
        Log.e("debug ","running");


        return view;
    }





    @Override
    public void appendVideoList(List<Result> results) {
        Log.e("append", "on append");
        data.addAll(results);
        Log.e("append", results.size()+ "");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getVideoList(int amount, int page) {

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Log.e("err", msg);
    }


}
