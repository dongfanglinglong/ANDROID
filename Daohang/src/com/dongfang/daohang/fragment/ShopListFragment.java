package com.dongfang.daohang.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.dongfang.daohang.R;
import com.dongfang.daohang.beans.AreaBean;
import com.dongfang.daohang.beans.AreaEntity;
import com.dongfang.daohang.fragment.adp.ShopsListAdapter;
import com.dongfang.daohang.interf.OnSelectAreaListener;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.daohang.params.ComParams;
import com.dongfang.utils.DFException;
import com.dongfang.utils.JsonAnalytic;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseFragment;
import com.dongfang.views.PullToRefreshView;
import com.dongfang.views.PullToRefreshView.OnFooterRefreshListener;
import com.dongfang.views.PullToRefreshView.OnHeaderRefreshListener;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ShopListFragment extends BaseFragment {

	@ViewInject(R.id.fragment_shoplist_listview)
	private ListView listView;
	@ViewInject(R.id.fragment_shoplist_pulltorefreshview)
	private PullToRefreshView pulltoRefreshView;

	private ShopsListAdapter searchAdp;
	/** 每次请求长度 */
	public static final int LIMIT = 10;
	/** 每次请求页数 */
	private int pageStart = 1;
	private int lastTotal = LIMIT;
	private String searchValue = "";
	private List<AreaBean> listData;// 显示列表

	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_shoplist, container, false);
		ViewUtils.inject(this, v);

		context = getActivity();

		pulltoRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
			@Override
			public void onHeaderRefresh(PullToRefreshView view) {
				getSearchResult(searchValue, 1, LIMIT);
			}
		});
		pulltoRefreshView.setOnFooterRefreshListener(new OnFooterRefreshListener() {
			@Override
			public void onFooterRefresh(PullToRefreshView view) {
				getSearchResult(searchValue, pageStart, LIMIT);
			}
		});

		listData = new ArrayList<AreaBean>();
		searchAdp = new ShopsListAdapter(getActivity(), listData);
		listView.setAdapter(searchAdp);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ULog.d(new Gson().toJson(listData.get(position)));
				if (onSelectAreaListener != null) {
					onSelectAreaListener.onSelected(new Gson().toJson(listData.get(position)),
							ShopListFragment.class.getSimpleName());

				}
			}
		});

		if (null != getArguments() && getArguments().containsKey("name"))
			getSearchResult(getArguments().getString("name"), pageStart, LIMIT);
		return v;
	}

	public void refresh(Context context, Bundle data) {
		this.context = (this.context == null) ? context : this.context;
		getSearchResult(data.containsKey("name") ? data.getString("name") : "", 1, LIMIT);
	}

	private void getSearchResult(final String searchName, final int start, final int limit) {

		if (1 == start) {
			lastTotal = 0;
			listData.clear();
		}

		if (start > 1 && limit > lastTotal) {
			// Toast.makeText(getActivity(), "没有更多内容啦O(∩_∩)O", Toast.LENGTH_LONG).show();
			pulltoRefreshView.onFooterRefreshComplete();
			return;
		}

		if (null == context)
			ULog.e("------------------------ null = context --------------------");

		String url = HttpActions.searchArea(context, ComParams.BASE_PLACEID, searchName, start, limit);
		ULog.d(url);
		new HttpUtils().send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				pageStart = 1 + start;

				if (1 == start) {
					pulltoRefreshView.onHeaderRefreshComplete();
				}
				else {
					pulltoRefreshView.onFooterRefreshComplete();
				}

				ULog.d(responseInfo.result);

				try {
					AreaEntity areaEntity = JsonAnalytic.getInstance().analyseJsonTInfoDF(responseInfo.result,
							AreaEntity.class);
					if (null == areaEntity)
						return;

					ULog.d(areaEntity.toString());

					lastTotal = areaEntity.getList().size();

					listData.addAll(areaEntity.getList());
					searchAdp.setList(listData);
					searchAdp.notifyDataSetChanged();
				} catch (DFException e) {
					e.printStackTrace();
					Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				if (1 == start) {
					pulltoRefreshView.onHeaderRefreshComplete();
				}
				else {
					pulltoRefreshView.onFooterRefreshComplete();
				}
			}

		});

	}

	private OnSelectAreaListener onSelectAreaListener;

	public OnSelectAreaListener getOnSelectAreaListener() {
		return onSelectAreaListener;
	}

	public void setOnSelectAreaListener(OnSelectAreaListener onSelectAreaListener) {
		this.onSelectAreaListener = onSelectAreaListener;
	}

	@Override
	public void onClick(View v) {}

}
