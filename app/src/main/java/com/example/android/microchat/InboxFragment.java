package com.example.android.microchat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

/**
 * Created by surya on 12-06-2016.
 */
public class InboxFragment extends Fragment {

    ListView mListView;

    public InboxFragment() {

    }

    public static InboxFragment newInstance() {
        InboxFragment fragment = new InboxFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);

        mListView = (ListView)rootView.findViewById(R.id.inbox_listview);

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

        Firebase mesRef = new Firebase(Constants.FIREBASE_URL_MESSAGES);
        Log.e("xxxxxxxxxxxx",Constants.FIREBASE_URL_MESSAGES);
        ListAdapter adapter = new FirebaseListAdapter<User>(getActivity(),User.class,android.R.layout.two_line_list_item,mesRef) {
            @Override
            protected void populateView(View view, User user, int i) {
       //         ((TextView)view.findViewById(android.R.id.text1)).setText(user.getEmail());
         //       ((TextView)view.findViewById(android.R.id.text2)).setText(user.getMessages());
                Log.e("xxxxxxxxxxxx","xxxxxxxxxxxxxx");
            }
        };
        mListView.setAdapter(adapter);
    }
}