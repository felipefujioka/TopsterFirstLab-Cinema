package generic_list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.topster.topster.firstlab_cinema.BR;

/**
 * Created by felipefujioka on 1/6/16.
 */
public class TheaterViewModel extends BaseObservable {

    private String name;

    @Bindable
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }
}
