package pbrtests.restsampleapp.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pbrtests.restsampleapp.R;
import pbrtests.restsampleapp.model.GithubItem;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final String TAG = "RecyclerAdapter";
    Context mContext;
    List<GithubItem> mItemList;

    public RecyclerAdapter(Context context) {

        mContext = context;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name, tv_creator, tv_description, tv_date;
        ImageView iv_avatar;

        public ItemViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_creator = itemView.findViewById(R.id.tv_creator);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_date = itemView.findViewById(R.id.tv_date);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_github, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_name.setText(mItemList.get(position).getName());
        itemViewHolder.tv_creator.setText(mItemList.get(position).getLogin());
        itemViewHolder.tv_date.setText(dateFormatter(mItemList.get(position).getCreated_at()));
        itemViewHolder.tv_description.setText(mItemList.get(position).getDescription());

        //Use Glide library to convert url link of image to ImageView.
        Glide.with(mContext)
                .load(mItemList.get(position).getAvatarUrl())
                .into(itemViewHolder.iv_avatar);
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

    public String dateFormatter (String date){

        //This is a hack. Only use the first 9 characters of a string
        return date.substring(0,10);
    }
}
