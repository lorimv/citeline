package com.example.citeline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}*/
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button testButton;
    boolean expandable = true;
    // boolean expand = true;

    // attempt at using dialog box
    private String m_Text = "";
    Integer buttonNum = 511; // for making new buttons
    // the above is just a throwaway tactic to make prototype appear functional
    // will need to make a more substantive way to access new buttons, using strings?

    private final int GALLERY_REQ_CODE = 1000;
    ImageView imager;

    // ties delete buttons to layouts
    Map<Integer, Integer> mapMain = new HashMap<Integer, Integer>();

    // ties edit buttons to subject buttons
    Map<Integer, Integer> mapEditMain = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // moves to next activity. add ability to create new, but go to activity 2 always
        Button goActivity = (Button) findViewById(R.id.goSecond);
        goActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                openActivity2();
            }
        });

        Button addNew = findViewById(R.id.addNewButton);
        // for adding extra layouts
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {


                openDialogueBox();

            }
        });


    } // outside the main func

    // called from the new button that gets rendered
    public void openNoteActivity (View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        // carry over name variable to next activity
        intent.putExtra("btnName", m_Text);
        // startActivity(intent);
        startActivityForResult(intent, 0);
    }

    // used for hiding layouts in the dom
    public void hideALayoutMain (View view) {

        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout
        Button curBtn = (Button) findViewById(currentButtonPress);

        switch (currentButtonPress){
            case R.id.deleteButton1:
                LinearLayout ll = (LinearLayout) findViewById(R.id.horizootalButtonLayout1);
                ll.setVisibility(View.GONE);
                break;
            case R.id.deleteButton2:
                LinearLayout ll2 = (LinearLayout) findViewById(R.id.horizootalButtonLayout2);
                ll2.setVisibility(View.GONE);
                break;
            case R.id.deleteButton3:
                LinearLayout ll3 = (LinearLayout) findViewById(R.id.horizootalButtonLayou3);
                ll3.setVisibility(View.GONE);
                break;
            default:
                LinearLayout ll4 = (LinearLayout) findViewById(mapMain.get(currentButtonPress));
                ll4.setVisibility(View.GONE);
        }

        if (curBtn.getVisibility() == View.VISIBLE){
            curBtn.setVisibility(View.GONE);
        } else {
            curBtn.setVisibility(View.VISIBLE);
        }

    }

    // edit buttons on start page
    public void editAaButtonMain (View view) {

        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout

        Button curBtn = (Button) findViewById(currentButtonPress);

        switch (currentButtonPress){
            case R.id.edutButton1:
                Button editingBtn = (Button) findViewById(R.id.Db_New);
                Integer curBtnIdToEditNum = R.id.Db_New;
                openDialogueBox2(curBtnIdToEditNum);
                break;
            case R.id.edutButton2:
                Integer curBtnIdToEditNum2 = R.id.goSecond;
                openDialogueBox2(curBtnIdToEditNum2);
                break;
            case R.id.edutButton3:
                Integer curBtnIdToEditNum3 = R.id.Db_Sharing;
                openDialogueBox2(curBtnIdToEditNum3);
                break;
            default:
                Integer curBtnIdToEditNum4 = mapEditMain.get(currentButtonPress); // call the below func on the subject button tied to the edit button
                openDialogueBox2(curBtnIdToEditNum4);
        }

    }


    public void goToSettings (View view) {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }


    public void openActivity2 () {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    // opens the box to type name in
    public void openDialogueBox () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Subject");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();

                // the below code ws transplanted from the on click listener above
                LinearLayout ll = (LinearLayout) findViewById(R.id.app2);
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this); // need an activity
                View row = inflater.inflate(R.layout.add_layout_one, null);
                ll.addView(row);

                // create an id for the indented once layout that will be added
                Integer getLayoutId = View.generateViewId();

                // try to set the id of the new layout after it has been placed
                LinearLayout llUnindented = (LinearLayout) findViewById(R.id.addLayoutOneHide);
                llUnindented.setId(getLayoutId);

                // generate an id for the delete button for the above layout
                Integer mainDeleteButtonId = View.generateViewId();

                // bind this id to  the once indented delete button
                Button mainDeleteButton = (Button) findViewById(R.id.deleteButtonMain);
                mainDeleteButton.setId(mainDeleteButtonId);



                Button addedTheButton = (Button) findViewById(R.id.addedButton);
                addedTheButton.setId(buttonNum);
                addedTheButton.setText(m_Text);
                // buttonNum++;

                // create id for the edit button to be added
                Integer editButtonToBeAddedId = View.generateViewId();

                // assign this id to the new edit button
                Button editButtonToBeAdded = (Button) findViewById(R.id.edutButtonAddLayoutOne);
                editButtonToBeAdded.setId(editButtonToBeAddedId);

                mapMain.put(mainDeleteButtonId, getLayoutId); // map delete buttons to layouts
                mapEditMain.put(editButtonToBeAddedId, buttonNum); // map edit buttons to already created subject buttons
                buttonNum++;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    // opens dialogue box for editing buttons
    public void openDialogueBox2 (Integer currentBtn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Subject Name");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Button setNewBtn = (Button) findViewById(currentBtn);
                setNewBtn.setText(m_Text);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }



}