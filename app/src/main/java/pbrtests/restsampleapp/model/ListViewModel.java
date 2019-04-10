package pbrtests.restsampleapp.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import pbrtests.restsampleapp.BR;


public class ListViewModel extends BaseObservable {


    private Boolean listVisibility = false;

    @Bindable
    public Boolean getListVisibility() {
        return listVisibility;
    }

    public void setListVisibility(Boolean listVisibility) {
        this.listVisibility = listVisibility;
        notifyPropertyChanged(BR.listVisibility);
    }
}
