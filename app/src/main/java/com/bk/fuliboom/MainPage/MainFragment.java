package com.bk.fuliboom.MainPage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.bk.fuliboom.Utils.Hints;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements  IMainView{
    private FragmentManager manager;

    private List<Result> data;
    private IMainPresenter mPresenter;
    private VideoRecyclerAdapter mAdapter;

//    @BindView(R.id.view_pager)
//    ViewPager mViewPager;

    @BindView(R.id.video_list)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_video)
    SwipeRefreshLayout videoRefresher;




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
    public void onAttach(Context context) {
        super.onAttach(context);
        manager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        mAdapter = new VideoRecyclerAdapter(data, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        mPresenter.getVideoList(10,1,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setOnItemClickListener(new VideoRecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClicked(int position, String url) {
                browseWeb(url);

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                Uri content_url = Uri.parse(url);
//                intent.setData(content_url);
//                startActivity(Intent.createChooser(intent, "请选择浏览器"));
            }
        });
        recyclerView.setAdapter(mAdapter);

        videoRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                videoRefresher.setRefreshing(true);
                mPresenter.getVideoList(100,1, true);
            }
        });

        return view;
    }


    public void browseWeb(String url){
        WebFragment webFragment = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        webFragment.setArguments(bundle);
        manager.beginTransaction().replace(R.id.content_main,webFragment).addToBackStack(null).commit();
//        Log.e("count", manager.getBackStackEntryCount() + "");
    }



    @Override
    public void appendVideoList(List<Result> results) {
        videoRefresher.setRefreshing(false);
        for (Result result:results){
            Log.e("result", result.toString());
        }
        data.addAll(results);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getVideoList(int amount, int page) {

    }

    @Override
    public void showError(String msg) {
        videoRefresher.setRefreshing(false);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpdate(List<Result> results) {
        videoRefresher.setRefreshing(false);
        Hints.showTost("更新成功！");
        data.clear();
        data.addAll(results);
        mAdapter.notifyDataSetChanged();
    }


}
