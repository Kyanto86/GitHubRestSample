package pbrtests.restsampleapp.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import pbrtests.restsampleapp.list.ListAdapter;
import pbrtests.restsampleapp.model.GithubItem;

public class ListBindingAdapter {

    private static final String TAG = "ListBindingAdapter";

    @BindingAdapter("gitHubItemList")
    public static void setGitHubItemList (RecyclerView recyclerView, List<GithubItem> githubItems){

        //prevent NullPointerError
        if (githubItems == null)
            return;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //create if not created yet
        if (layoutManager == null){
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        ListAdapter listAdapter = (ListAdapter) recyclerView.getAdapter();
        //create if null
        if (listAdapter == null){

            listAdapter = new ListAdapter(recyclerView.getContext(), githubItems);
            recyclerView.setAdapter(listAdapter);
        }


    }
}
