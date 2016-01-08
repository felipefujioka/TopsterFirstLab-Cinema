package generic_list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topster.topster.firstlab_cinema.BR;
import com.topster.topster.firstlab_cinema.LoadingScreen;
import com.topster.topster.firstlab_cinema.R;

/**
 * Created by felipefujioka on 1/5/16.
 */



@SuppressLint("ValidFragment")
public class GenericListFragment extends Fragment{

    private RecyclerView recyclerView;
    MyAdapter mAdapter;
    public ObservableList<String> model = new ObservableArrayList<>();
    public ObservableList<SessionViewModel> sessionsModel = new ObservableArrayList<SessionViewModel>();
    public int type = 0;


    public GenericListFragment(int type){
        this.type = type;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.generic_list_layout, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        switch (this.type){
            case LoadingScreen.THEATERS_LIST:
                this.model = DataProviderUtil.getSharedInstance().getTheaters();
                break;
            case LoadingScreen.MOVIES_LIST:
                this.model = DataProviderUtil.getSharedInstance().getMovies();
                break;
            case LoadingScreen.SESSIONS_LIST:
                this.sessionsModel = DataProviderUtil.getSharedInstance().getSessions();
                break;
            default:
                break;
        }

        this.mAdapter = new MyAdapter(model, sessionsModel);
        recyclerView.setAdapter(mAdapter);

        return v;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        public ObservableList<String> mDataset;
        public ObservableList<SessionViewModel> mSessionsDataSet;
        MyDataChangedCallback dataChangedCallback;

        public ObservableList<String> getmDataset() {
            return mDataset;
        }

        public void setmDataset(ObservableList<String> mDataset) {
            this.mDataset = mDataset;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private ViewDataBinding binding;

            public ViewHolder(View view) {
                super(view);
                binding = DataBindingUtil.bind(view);
            }

            public ViewDataBinding getBinding() {
                return binding;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ObservableList<String> myDataset, ObservableList<SessionViewModel> mySessionsDataSet) {
            mDataset = myDataset;
            mSessionsDataSet = mySessionsDataSet;
            dataChangedCallback = new MyDataChangedCallback(this);
            myDataset.addOnListChangedCallback(dataChangedCallback);
            mySessionsDataSet.addOnListChangedCallback(dataChangedCallback);
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            //((TextView)holder.itemView.findViewById(R.id.info_text)).setText(mDataset[position]);
            if(type == LoadingScreen.THEATERS_LIST) {
                TheaterViewModel theater = new TheaterViewModel();
                theater.setName(mDataset.get(position));
                holder.getBinding().setVariable(BR.theater, theater);
                holder.getBinding().executePendingBindings();
            }else if(type == LoadingScreen.MOVIES_LIST) {
                TheaterViewModel theater = new TheaterViewModel();
                theater.setName(mDataset.get(position));
                holder.getBinding().setVariable(BR.movie, theater);
                holder.getBinding().executePendingBindings();
            }else if(type == LoadingScreen.SESSIONS_LIST){
                SessionViewModel session = mSessionsDataSet.get(position);

                holder.getBinding().setVariable(BR.session, session);
                holder.getBinding().executePendingBindings();
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            if(GenericListFragment.this.type == LoadingScreen.SESSIONS_LIST){
                return this.mSessionsDataSet.size();
            }else {
                return this.mDataset.size();
            }

        }

    }

    public class MyDataChangedCallback extends ObservableList.OnListChangedCallback{

        MyAdapter mAdapter;

        MyDataChangedCallback(MyAdapter adapter){
            this.mAdapter = adapter;
        }

        @Override
        public void onChanged(ObservableList sender) {
            if(mAdapter!=null){
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {

            if (mAdapter != null)
            {
                mAdapter.notifyItemRangeChanged(positionStart, itemCount);
            }

        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {

            if (mAdapter != null)
            {
                mAdapter.notifyItemRangeInserted(positionStart, itemCount);
            }

        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {

            if (mAdapter != null) {
                mAdapter.notifyItemMoved(fromPosition, toPosition);
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            if (mAdapter != null)
            {
                mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
            }

        }
    }
}
