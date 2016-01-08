package generic_list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.topster.topster.firstlab_cinema.BR;

/**
 * Created by felipefujioka on 1/6/16.
 */
public class SessionViewModel extends BaseObservable{

    private String movieName;
    private String time;

    public SessionViewModel(String movieName, String time){
        this.movieName = movieName;
        this.time = time;
    }

    @Bindable
    public String getTime() {
        return time;
    }

    @Bindable
    public String getMovieName() {
        return movieName;
    }


    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
        notifyPropertyChanged(BR.movieName);
    }
}
