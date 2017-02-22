package com.firebasekorea.chat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebasekorea.chat.R;
import com.firebasekorea.chat.adpaters.MessageAdapter;
import com.firebasekorea.chat.controllers.MessageController;
import com.firebasekorea.chat.models.Message;
import com.firebasekorea.chat.utils.UserUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by namhoonkim on 17/02/2017.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = MainActivity.class.getSimpleName();

    /* View Component */
    private ListView mListView;
    private MessageAdapter mMessageAdapter;
    private EditText mInputEditText;
    private Button mSendButton;


    /* Firebase */
    private ArrayList<Message> mMessageList = new ArrayList<Message>();
    private ArrayList<String> mMessageKeyList = new ArrayList<String>();
    private DatabaseReference mMessageReference;
    private ValueEventListener mMessageListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Firebase Real-Time Chat");

        mMessageReference = FirebaseDatabase.getInstance().getReference().child("messages");

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener messageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mMessageList.clear();
                mMessageKeyList.clear();

                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Message msg = Message.parseSnapshot(child);
                    if (msg.isDeleted != null && msg.isDeleted == false) {
                        mMessageList.add(msg);
                        mMessageKeyList.add(child.getKey());
                    }
                }
                updateUserInterface();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "load Message List:onCancelled", databaseError.toException());
            }
        };

        if (mMessageReference != null) {
            mMessageReference.addValueEventListener(messageListener);
        }
        mMessageListener = messageListener;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMessageListener != null) {
            if (mMessageReference != null) {
                mMessageReference.removeEventListener(mMessageListener);
            }
        }
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.main_listview);

        mInputEditText = (EditText) findViewById(R.id.main_input_text);
        mSendButton = (Button) findViewById(R.id.main_button);
        mSendButton.setOnClickListener(this);
    }

    private void updateUserInterface() {
        mMessageAdapter = new MessageAdapter(this, mMessageList, mMessageKeyList);
        mListView.setAdapter(mMessageAdapter);
        mListView.setSelection(mMessageAdapter.getCount() - 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button:
                onClickSendButton();
                break;
        }
    }

    private void onClickSendButton() {
        String meesageBody = mInputEditText.getText().toString();
        String userName = UserUtil.loadUserName();
        Log.d(TAG, "userName is " + userName);

        String messageKey = MessageController.createMessage();

        MessageController.updateMessage(messageKey, userName, meesageBody);
    }
}
