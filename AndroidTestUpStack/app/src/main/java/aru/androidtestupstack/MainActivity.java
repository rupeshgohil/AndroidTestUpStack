package aru.androidtestupstack;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import aru.androidtestupstack.Adapter.GphyAdapter;
import aru.androidtestupstack.App.App;
import aru.androidtestupstack.Interface.OnClickPrasenter;
import aru.androidtestupstack.Modal.Data;
import aru.androidtestupstack.Modal.GiphyImageInfo;
import aru.androidtestupstack.Modal.LickeUnLicke;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import aru.androidtestupstack.Modal.GiphyResponse;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import aru.androidtestupstack.Constant.MyDividerItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
   //private ApiService apiService;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private static final int GIF_IMAGE_HEIGHT_PIXELS = 128;
    private static final int GIF_IMAGE_WIDTH_PIXELS = GIF_IMAGE_HEIGHT_PIXELS;
    private static final int PORTRAIT_COLUMNS = 3;
   // public ActivityMainBinding mainBinding;
    GphyAdapter mAdapter;
    public int NumberofColum = 2;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.edtsearch)
    EditText edtSearch;
    String search;
    Context mContext;
    private Box<LickeUnLicke> notesBox;
    private Query<LickeUnLicke> notesQuery;
    ArrayList<GiphyImageInfo> mGphyArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext=this;
      //  BoxStore boxStore = ((App) getApplication()).getBoxStore();
        recyclerView.setLayoutManager(new GridLayoutManager(this,NumberofColum));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setItemViewCacheSize(24);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, PORTRAIT_COLUMNS + PORTRAIT_COLUMNS);
        mAdapter = new GphyAdapter(MainActivity.this,mGphyArray, new OnClickPrasenter() {
            @Override
            public void clickLicke(int position) {
                GiphyImageInfo mGiphyImageInfo = mGphyArray.get(position);
                Toast.makeText(MainActivity.this, "Licke", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clickDisLicke(int position) {
                GiphyImageInfo mGiphyImageInfo = mGphyArray.get(position);
                Toast.makeText(MainActivity.this, "UnLicke", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    @OnClick(R.id.imgSearch)
    public void OnSearch() {
        LoadGiphyData(GiphyService.getInstance().getSearchResults(edtSearch.getText().toString()));
    }

    private void LoadGiphyData(Observable<GiphyResponse> observable) {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait... ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
        mCompositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                        for (final Data datum : response.getData())
                        {
                        final String url = datum.getImages().getFixedwidth().getMp4();
                            final String urlgif = datum.getImages().getFixedwidth().getGif();
                            mGphyArray.add(new GiphyImageInfo().withUrl(url,urlgif));
                            mAdapter.notifyDataSetChanged();
                           // mAdapter.add(new GiphyImageInfo().withUrl(url,urlgif));

                        if (Log.isLoggable(TAG, Log.INFO)) {
                            Log.i(TAG, "ORIGINAL_IMAGE_URL\t" + url);
                        }
                    }
                    progress.dismiss();
                }, error -> {
                    // onError
                    if (Log.isLoggable(TAG, Log.ERROR)) {
                        Log.e(TAG, "onError", error);
                        progress.dismiss();
                    }
                }, () -> {
                    // onComplete
                    if (Log.isLoggable(TAG, Log.INFO)) {
                        Log.i(TAG, "Done loading!");
                        progress.dismiss();
                    }
                }));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
