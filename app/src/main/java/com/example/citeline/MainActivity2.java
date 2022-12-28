package com.example.citeline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private String m_Text2 = "";
    Integer buttonNum2 = 411;
    String sendSelectedImage;

    // ties delete buttons to layouts
    Map<Integer, Integer> mapSecond = new HashMap<Integer, Integer>();

    // create a map for edit buttons on the chapter page to map to new chapter buttons
    Map<Integer, Integer> editButtonsToChapters = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button goActivity = (Button) findViewById(R.id.openContent);
        goActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                openActivity3();
            }
        });

        Button goActivity2 = (Button) findViewById(R.id.settings);
        goActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                openActivity4();
            }
        });

        ImageView imageViewBtn2 = findViewById(R.id.imageViewSecond);
        // for   uploading using imageview

        // also for uploading to imageview
        imageViewBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });


        String setterText = "";
        // get values from previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setterText = extras.getString("btnName");
        }

        // set chap name n to value from previous activity
        Button setChapterName = (Button) findViewById(R.id.chapName);
        setChapterName.setText(setterText);
    }

    // used for hiding layouts in the dom
    public void hideALayoutSecond (View view) {

        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout
        Button curBtn = (Button) findViewById(currentButtonPress);

        switch (currentButtonPress){
            case R.id.deleteButton1v:
                LinearLayout ll = (LinearLayout) findViewById(R.id.horizootalButtonLayout11);
                ll.setVisibility(View.GONE);
                break;
            case R.id.deleteButton11:
                LinearLayout ll2 = (LinearLayout) findViewById(R.id.horizootalButtonLayout12);
                ll2.setVisibility(View.GONE);
                break;
            case R.id.deleteButton12:
                LinearLayout ll3 = (LinearLayout) findViewById(R.id.horizootalButtonLayout13);
                ll3.setVisibility(View.GONE);
                break;
            case R.id.deleteButton13:
                LinearLayout ll5 = (LinearLayout) findViewById(R.id.horizootalButtonLayout14);
                ll5.setVisibility(View.GONE);
                break;
            case R.id.deleteButt14:
                LinearLayout ll6 = (LinearLayout) findViewById(R.id.horizootalButtonLayout15);
                ll6.setVisibility(View.GONE);
                break;
            default:
                LinearLayout ll4 = (LinearLayout) findViewById(mapSecond.get(currentButtonPress));
                ll4.setVisibility(View.GONE);
        }

        if (curBtn.getVisibility() == View.VISIBLE){
            curBtn.setVisibility(View.GONE);
        } else {
            curBtn.setVisibility(View.VISIBLE);
        }

    }

    // edit buttons on start page
    public void editAaButtonChapter (View view) {

        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout
        Button curBtn = (Button) findViewById(currentButtonPress);

        switch (currentButtonPress){
            case R.id.edutButtonSubject1:
                Button editingBtn = (Button) findViewById(R.id.Db_New);
                Integer curBtnIdToEditNum = R.id.Db_Sharing;
                openDialogueBox3(curBtnIdToEditNum);
                break;
            case R.id.edutButtonSubject2:
                Integer curBtnIdToEditNum2 = R.id.Db_Save;
                openDialogueBox3(curBtnIdToEditNum2);
                break;
            case R.id.edutButtonSubject3:
                Integer curBtnIdToEditNum3 = R.id.Db_Back;
                openDialogueBox3(curBtnIdToEditNum3);
                break;
            case R.id.edutButtonSubject4:
                Integer curBtnIdToEditNum5 = R.id.Db_appInfo;
                openDialogueBox3(curBtnIdToEditNum5);
                break;
            case R.id.edutButtonSubject5:
                Integer curBtnIdToEditNum6 = R.id.Db_appInfo2;
                openDialogueBox3(curBtnIdToEditNum6);
                break;
            default:
                Integer curBtnIdToEditNum4 = editButtonsToChapters.get(currentButtonPress); // call the below func on the subject button tied to the edit button
                openDialogueBox3(curBtnIdToEditNum4);
        }

    }


    // open chapter subsections
    public void openActivity3 () {
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("imagePath", sendSelectedImage);
        intent.putExtra("btnName2", m_Text2);
        startActivity(intent);
    }

    // settings
    public void openActivity4 () {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

    public void goBackIntent (View view) {
        Intent intente = new Intent(this, MainActivity.class);
        startActivityForResult(intente, 0);
    }

    // working on uploading image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            sendSelectedImage = selectedImage.toString(); // to be send to next activity
            ImageView imageViewer = findViewById(R.id.imageViewSecond);
            imageViewer.setImageURI(selectedImage);
        }
    }

    // highlight button
    // doesn't work because it can't see the element?
    public void showSelectedBtn (View view) {
/*        Button setSelectBtn = (Button) findViewById(R.id.addedButton2);
        System.out.println("inside outside" + view.getId() + setSelectBtn);
        if (view.getId() == R.id.addedButton2) {
            view.setPressed(true);
            //view.setTex
            System.out.println("inside");
        }
        ViewGroup test = ((ViewGroup) findViewById(view.getId()));
        System.out.println("inside outside22" + test);*/


        // will need a better way to make this work in the future
/*        Button setSelectBtn = (Button) findViewById(R.id.addedButton2);
        setSelectBtn.setEnabled(true);*/
        // view.setPressed(true);
        //view.setSelected(false);
    }

    // append a new chapter
    public void addNewChapter(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Chapter");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text2 = input.getText().toString();

                // the below code ws transplanted from the on click listener above
                /// creates the button
                LinearLayout ll = (LinearLayout) findViewById(R.id.app3);
                LayoutInflater inflater = LayoutInflater.from(MainActivity2.this); // need an activity
                View row = inflater.inflate(R.layout.add_layout_two, null);
                ll.addView(row);

                ///// added stuff like in mainactivity
                // create an id for the indented once layout that will be added
                Integer getLayoutId = View.generateViewId();

                // try to set the id of the new layout after it has bean pl
                LinearLayout llUnindented = (LinearLayout) findViewById(R.id.addLayoutTwoHide);
                llUnindented.setId(getLayoutId);

                // generate an id for the delete button for the above layout
                Integer mainDeleteButtonId = View.generateViewId();

                // bind this id to  the once indented delete button
                Button mainDeleteButton = (Button) findViewById(R.id.deleteButtonSecond);
                mainDeleteButton.setId(mainDeleteButtonId);
                //////

                // setting the newly created button
                Button addedTheButton2 = (Button) findViewById(R.id.addedButton2);
                addedTheButton2.setId(buttonNum2);
                addedTheButton2.setText(m_Text2);

                //////
                // get an id to  name the new chapter edit button
                Integer chapterEditButtonId = View.generateViewId();

                // set the new chapter edit button to this id
                Button chapterEditButton = (Button) findViewById(R.id.edutButtonSubjectAdded);
                chapterEditButton.setId(chapterEditButtonId);
                //////


                mapSecond.put(mainDeleteButtonId, getLayoutId);
                editButtonsToChapters.put(chapterEditButtonId, buttonNum2);

                buttonNum2++;

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
    public void openDialogueBox3 (Integer currentBtn) {
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
                String m_Text = input.getText().toString();
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


    // back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}