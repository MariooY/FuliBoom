package com.bk.fuliboom.YoukuModule;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.bk.fuliboom.Adapters.AccountListAdapter;
import com.bk.fuliboom.R;
import com.bk.fuliboom.Repository.Beans.AccountInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YoukuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YoukuFragment extends Fragment implements View.OnClickListener , IYouKuView {
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    private IYoukuPresenter mPresenter;
    private ClipboardManager clipboard;
    private AccountListAdapter mAdapter;
    private List<AccountInfo> mDataList;
    private freshTimer timer;


    @BindView(R.id.account)
    TextView accountText;

    @BindView(R.id.psw)
    TextView passText;

    @BindView(R.id.btn_acc)
    Button btnAcc;

    @BindView(R.id.btn_psw)
    Button btnPsw;

    @BindView(R.id.btn_getVIP)
    Button btnVIP;

    @BindView(R.id.captchar)
    ImageView captcharView;

    @BindView(R.id.exist_account)
    ListView accountList;


    @BindView(R.id.input_code)
    EditText codeInput;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    public YoukuFragment() {
        mPresenter = new YoukuPresenterImpl(this);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment YoukuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YoukuFragment newInstance() {
        YoukuFragment fragment = new YoukuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext  = getActivity();

        clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        mDataList = new ArrayList<>();
        mAdapter = new AccountListAdapter(mContext,mDataList);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_youku, container, false);
        ButterKnife.bind(this,view);
        mPresenter.getCaptchar();
        mPresenter.getPastAccount();
        Log.e("btn null", (btnAcc==null)+ "");
        btnAcc.setOnClickListener(this);
        btnPsw.setOnClickListener(this);
        btnVIP.setOnClickListener(this);
        captcharView.setOnClickListener(this);
        accountList.setAdapter(mAdapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                timer = new freshTimer(3000,1000);
                timer.start();
                mPresenter.getPastAccount();
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        ClipData clip;
        switch (view.getId()){
            case R.id.btn_acc:
                clip = ClipData.newPlainText("account", accountText.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext,"已复制账号到粘贴板",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_psw:
                clip = ClipData.newPlainText("account", passText.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext,"已复制密码到粘贴板",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_getVIP:
                String captchar = codeInput.getText().toString();
                mPresenter.getNewAccount(captchar);
                break;
            case R.id.captchar:
                mPresenter.getCaptchar();
                break;
            default:
                break;
        }
    }

    @Override
    public void showCaptchar(Bitmap bitmap) {
        if (bitmap != null){
            captcharView.setImageBitmap(bitmap);
        } else {
            captcharView.setImageResource(R.mipmap.ic_launcher);
        }

    }

    @Override
    public void showNewAccount(AccountInfo accountInfo) {
        if (accountInfo == null){
            mPresenter.getPastAccount();
            accountText.setText("获取账号失败");
            passText.setText("获取密码失败");
            return;
        }
//        mDataList.add(accountInfo);
//        mAdapter.notifyDataSetChanged();
        accountText.setText(accountInfo.getmAccount());
        passText.setText(accountInfo.getmPassword());
    }

    @Override
    public void showAccountList(List<AccountInfo> accounts) {
        refreshLayout.setRefreshing(false);
        mDataList.clear();
        mDataList.addAll(accounts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String msg) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class freshTimer extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public freshTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            refreshLayout.setRefreshing(true);
        }

        @Override
        public void onFinish() {
            refreshLayout.setRefreshing(false);
        }
    }
}
