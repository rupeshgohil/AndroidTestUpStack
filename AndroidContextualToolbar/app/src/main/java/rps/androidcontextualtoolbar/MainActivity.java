package rps.androidcontextualtoolbar;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rps.androidcontextualtoolbar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ClickAdapterListener{
    public RecyclerView mRecyclerView;
    RecyclerViewAdapter myAdapter;
    private ActionMode actionMode;
    private ActionModeCallback actionModeCallback;
    ArrayList<datamodal> arrayList = new ArrayList<>();
    ActivityMainBinding mainBinding;
    public boolean isselect = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(mainBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainBinding.activitycontent.recyclerView.setLayoutManager(layoutManager);
        mainBinding.activitycontent.recyclerView.setHasFixedSize(true);
        mainBinding.activitycontent.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainBinding.activitycontent.recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        //actionModeCallback = new ActionModeCallback(myAdapter,actionMode,this);
        actionModeCallback = new ActionModeCallback();
        setDataAndAdapter();
    }
    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.activity_main_menu, menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d("API123", "here");
            switch (item.getItemId()) {
                case R.id.delete:
                    deleterow();
                    mode.finish();
                    return true;

                case R.id.Color:
                    updateColoredRows();
                    mode.finish();
                    return true;

                case R.id.All:
                    SelectAll();
                    return true;

                case R.id.Refresh:
                    setDataAndAdapter();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            myAdapter.clearSelections();
            actionMode = null;
            isselect = false;
            mainBinding.toolbar.setVisibility(View.VISIBLE);
        }
    }
    public void updateColoredRows(){
        List<Integer> selectedItemPositions =
                myAdapter.getSelectedItem();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            myAdapter.updateData(selectedItemPositions.get(i));
        }
        myAdapter.notifyDataSetChanged();
        actionMode = null;
    }
    private void deleterow() {
        List<Integer> list = myAdapter.getSelectedItem();
        for (int i = list.size() - 1; i >= 0; i--) {
            myAdapter.removeData(list.get(i));
        }
        myAdapter.notifyDataSetChanged();

        if (myAdapter.getItemCount() == 0)
            // fab.setVisibility(View.VISIBLE);

            actionMode = null;
    }

    public void setDataAndAdapter(){
        arrayList = new ArrayList<>();
        arrayList.add(new datamodal("Item1",false));
        arrayList.add(new datamodal("Item2",false));
        arrayList.add(new datamodal("Item3",false));
        arrayList.add(new datamodal("Item4",false));
        arrayList.add(new datamodal("Item5",false));
        arrayList.add(new datamodal("Item6",false));
        arrayList.add(new datamodal("Item7",false));
        arrayList.add(new datamodal("Item8",false));
        arrayList.add(new datamodal("Item9",false));
        arrayList.add(new datamodal("Item10",false));
        arrayList.add(new datamodal("Item11",false));
        arrayList.add(new datamodal("Item12",false));
        arrayList.add(new datamodal("Item13",false));
        arrayList.add(new datamodal("Item14",false));
        arrayList.add(new datamodal("Item15",false));
        arrayList.add(new datamodal("Item16",false));
        arrayList.add(new datamodal("Item17",false));
        arrayList.add(new datamodal("Item18",false));
        arrayList.add(new datamodal("Item19",false));
        arrayList.add(new datamodal("Item20",false));
        arrayList.add(new datamodal("Item21",false));
        arrayList.add(new datamodal("Item22",false));
        arrayList.add(new datamodal("Item23",false));
        arrayList.add(new datamodal("Item24",false));
        arrayList.add(new datamodal("Item25",false));
        myAdapter = new RecyclerViewAdapter(this,arrayList,this);
        mainBinding.activitycontent.recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void OnRowClicked(int pos) {
        Toast.makeText(getApplicationContext(),arrayList.get(pos).getItem(),Toast.LENGTH_LONG).show();
       if(isselect){
           EnabledActionMode(pos);
       }

    }
    public void SelectAll(){
        myAdapter.SelectAll();
        int count = myAdapter.SelectedItemCount();
        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }

        actionMode = null;
    }
    @Override
    public void onRowLongClicked(int pos) {
        mainBinding.toolbar.setVisibility(View.GONE);
        EnabledActionMode(pos);
        isselect = true;
    }
    public void EnabledActionMode(int pos){
        if(actionMode == null){
            actionMode = startSupportActionMode(actionModeCallback);
        }
        tonggleselection(pos);
    }
    public void tonggleselection(int pos){
        myAdapter.TonggleSelection(pos);
        if(actionMode == null){
            actionMode = startSupportActionMode(actionModeCallback);
            int count = myAdapter.SelectedItemCount();
            if (count == 0) {
                actionMode.finish();
                actionMode = null;
            } else {
                actionMode.setTitle(String.valueOf(count));
                actionMode.invalidate();
            }
        }

    }
}
