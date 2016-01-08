package generic_list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by felipefujioka on 1/8/16.
 */
public class DataProviderViewModel extends BaseObservable{

    private boolean shouldExpand;
    private boolean error;
    private boolean loading;

    public DataProviderViewModel(){
        shouldExpand = true;
        error = false;
        loading = false;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
    }

    @Bindable
    public boolean getShouldExpand(){
        return shouldExpand;
    }

    @Bindable
    public boolean getError(){
        return error;
    }

    @Bindable
    public boolean getLoaindg(){
        return shouldExpand;
    }

}
