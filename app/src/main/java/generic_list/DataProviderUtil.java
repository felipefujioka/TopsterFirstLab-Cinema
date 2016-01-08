package generic_list;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.os.AsyncTask;

import com.topster.topster.firstlab_cinema.LoadingScreen;

import java.util.Random;

/**
 * Created by felipefujioka on 1/6/16.
 */
public class DataProviderUtil {

    public LoadingScreen mainActivity;

    private ObservableArrayList<String> theaters;
    private ObservableArrayList<String> movies;
    private ObservableArrayList<SessionViewModel> sessions;

    public ObservableBoolean shouldExpand;
    public ObservableBoolean loading;
    public ObservableBoolean error;

    public static DataProviderUtil sharedInstance;

    public static DataProviderUtil getSharedInstance() {
        if(sharedInstance == null){
            sharedInstance = new DataProviderUtil();
        }
        return sharedInstance;
    }

    public DataProviderUtil(){

        this.sessions = new ObservableArrayList<SessionViewModel>();
        this.theaters = new ObservableArrayList<String>();
        this.movies = new ObservableArrayList<String>();
        this.shouldExpand = new ObservableBoolean();
        this.loading = new ObservableBoolean();
        this.error = new ObservableBoolean();

    }

    public ObservableArrayList<String> getMovies() {

        movies.clear();

        new LoadListTask(mainActivity).execute(2);

        return movies;
    }

    public ObservableArrayList<SessionViewModel> getSessions() {

        sessions.clear();

        new LoadListTask(mainActivity).execute(3);

        return sessions;
    }

    public ObservableArrayList<String> getTheaters() {

        theaters.clear();

        new LoadListTask(mainActivity).execute(1);

        return theaters;
    }

    private class LoadListTask extends AsyncTask<Integer, Integer, Long>{

        public LoadingScreen mainActivity;

        public LoadListTask(LoadingScreen activity){
            this.mainActivity = activity;
        }

        @Override
        protected Long doInBackground(Integer... params) {

            int count = params.length;
            if(count > 0) {
                int type = params[0];

                try {
                    Thread.sleep(3000);
                    if (new Random().nextFloat() > 0.3) {
                        switch (type) {
                            case 1:
                                DataProviderUtil.sharedInstance.theaters.add("Cinema 1");
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.theaters.add("Cinema 2");
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.theaters.add("Cinema 3");
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.theaters.add("Cinema 4");
                                break;
                            case 2:
                                DataProviderUtil.sharedInstance.movies.add("Star Wars");
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.movies.add("Shawshank Redemption");
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.movies.add("Pulp Fiction");
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.movies.add("The Shinning");
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.movies.add("The Lord of The Rings");
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.movies.add("The Godfather");
                                break;
                            case 3:
                                DataProviderUtil.sharedInstance.sessions.add(new SessionViewModel("Star Wars", "15:00"));
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.sessions.add(new SessionViewModel("Shawshank Redemption", "16:00"));
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.sessions.add(new SessionViewModel("The Godfather", "21:00"));
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.sessions.add(new SessionViewModel("The Lord Of The Rings", "20:00"));
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.sessions.add(new SessionViewModel("The Shinning", "17:00"));
                                //Thread.sleep(500);
                                DataProviderUtil.sharedInstance.sessions.add(new SessionViewModel("Pulp Fiction", "18:00"));
                                break;
                            default:
                                break;
                        }
                    }else{
                        DataProviderUtil.getSharedInstance().error.set(true);
                    }
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                return null;
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(Long result) {
            if(DataProviderUtil.getSharedInstance().error.get()){
                DataProviderUtil.getSharedInstance().shouldExpand.set(true);
            }else{
                DataProviderUtil.getSharedInstance().shouldExpand.set(false);
            }
            DataProviderUtil.getSharedInstance().loading.set(false);

        }
    }

}
