package com.example.attendancetrackingsystem;

;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Patrick Haslinger on 12.01.2018.
 */

public class LstFragment extends ListFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState ){
        //Inflate the fragment layout file
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragmentlayout, container, false);
        String[] datasource = {"Heinrich", "Sebastian", "Patrick", "Serhad"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.rowlayout ,R.id.txtitem, datasource);
        setListAdapter(adapter);
        setRetainInstance(true);
        return rootView;
    }
    public void onListItemClick(ListView l, View view, int position, long id){
        ViewGroup viewGroup = (ViewGroup) view;
        TextView txt = (TextView)viewGroup.findViewById(R.id.txtitem);
        Toast.makeText(getActivity(),txt.getText().toString(), Toast.LENGTH_LONG).show();

    }
}
