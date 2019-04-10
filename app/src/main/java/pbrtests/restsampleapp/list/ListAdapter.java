package pbrtests.restsampleapp.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pbrtests.restsampleapp.R;
import pbrtests.restsampleapp.databinding.GithubItemBinding;
import pbrtests.restsampleapp.model.GithubItem;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final String TAG = "ListAdapter";
    Context mContext;
    List<GithubItem> mItemList;

    public ListAdapter(Context context, List<GithubItem> itemList) {

        mContext = context;
        mItemList = itemList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        GithubItemBinding binding;

        public ItemViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        GithubItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.github_item, parent, false);
        return new ItemViewHolder(binding.getRoot());
        //View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_github, parent, false);
        //return new ItemViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        GithubItem githubItem = mItemList.get(position);

        ((ItemViewHolder) holder).binding.setGithubItem(githubItem);
        //((ItemViewHolder) holder).binding.setVariable(BR.githubItem, githubItem);
        ((ItemViewHolder) holder).binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (mItemList == null)
            return 0;
        return mItemList.size();
    }


    public void updateAdapter(List<GithubItem> list){

        Log.d(TAG, "updateAdapter: updating.");
        mItemList = list;
        notifyDataSetChanged();
    }
}
