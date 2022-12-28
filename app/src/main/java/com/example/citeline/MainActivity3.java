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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    Integer buttonNum10 = 311;
    Integer layoutIdSet = 611;
    String layoutNum = "indentedLayout"; // keep track of tags for layouts that are added
    String buttonTag = "indentedButton"; // keep track of tags for buttons that are added
    String m_Text5 = "";

    Integer imageViewBeingClicked = 0; // keep tk of imageview ids for event

    //  ArrayList<Integer> ids = new ArrayList<>();
    // Dictionary ids = new Hashtable();

    // ties 'add chapter' button to unindented (level 1) layout
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    // ties label button to double_indented layout (to hide it)
    Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();

    // ties label button to indented layout (to hide it)
    Map<Integer, Integer> map3 = new HashMap<Integer, Integer>();

    // ties delete buttons to layouts
    Map<Integer, Integer> map4 = new HashMap<Integer, Integer>();

    // ties imageviews
    Map<Integer, Integer> map5 = new HashMap<Integer, Integer>();

    // ties indented once and double indented edit buttons to subsection buttons
    Map<Integer, Integer> map6 = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            Uri receivedImage = null;
            // set image to that of the chapter's
            ImageView setNewImage = (ImageView) findViewById(R.id.imageView);
            Bundle extras2 = getIntent().getExtras();
            if (extras2 != null){
                receivedImage = Uri.parse(extras2.getString("imagePath"));
                setNewImage.setImageURI(receivedImage);
            }

        }
        catch (Exception e) {

        }

        String setterText2 = "";
        // get values from previous activity
        Bundle extrasMain3 = getIntent().getExtras();
        if (extrasMain3 != null) {
            setterText2 = extrasMain3.getString("btnName2");
        }

        // set chap name n to value from previous activity
        Button setChapterName = (Button) findViewById(R.id.button);
        setChapterName.setText(setterText2);

    }

    // used for hiding layouts in the dom
    public void imageViewUploader (View view) {

        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout
        imageViewBeingClicked = currentButtonPress;

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("imageViewId", currentButtonPress);
        startActivityForResult(intent, 3);

    }

    // used to keep track of the once indented sub chapters
    // add indented by two?
    public void addIndentedSubject (View view) {

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
                m_Text5 = input.getText().toString();

                Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout
                Button checkBtn = (Button) view;
                Integer cur = checkBtn.getId();

                // get an id for the new double indented layout we are using to hide parts of the layout
                Integer getHiddenLayoutId = View.generateViewId();

                Integer layoutIdToAdd = map.get(currentButtonPress);

                LinearLayout ll = (LinearLayout) findViewById(map.get(currentButtonPress));
                LayoutInflater inflater = LayoutInflater.from(MainActivity3.this); // some context
                View row = inflater.inflate(R.layout.double_indented, null);
                ll.addView(row);
                Button but6 = (Button) findViewById(R.id.button6);

                // find newly created layout used to hide content and give it an id to map to map2
                LinearLayout llUnindented = (LinearLayout) findViewById(R.id.doubleIndentedHideLayout);
                llUnindented.setId(getHiddenLayoutId);

                // create new id for the label button to map to map2
                Integer getLabelButtonId = View.generateViewId();

                // setting the newly created button
                Button addedTheButton3 = (Button) findViewById(R.id.button6);
                addedTheButton3.setTag(buttonTag + buttonNum10.toString());
                // addedTheButton3.setId(buttonNum10); old way
                addedTheButton3.setId(getLabelButtonId);
                addedTheButton3.setText(m_Text5);
                layoutIdSet++;
                buttonNum10++;

                // create id for twice indented delete button
                Integer twiceIndentedDeleteButtonId = View.generateViewId();
                // set the button to this id
                Button twiceIndentedDeleteButton = (Button) findViewById(R.id.deleteButton2);
                twiceIndentedDeleteButton.setId(twiceIndentedDeleteButtonId);

                // create id for twice indented linearlayout to be deleted
                Integer twiceIndentedLinearLayoutToDeleteId = View.generateViewId();
                // assign the twice indented linearlayout to delete to this id
                LinearLayout twiceIndentedLinearLayoutToDelete = (LinearLayout) findViewById(R.id.firstLayoutFromTwiceIndented);
                twiceIndentedLinearLayoutToDelete.setId(twiceIndentedLinearLayoutToDeleteId);


                ///////
                // generate id for new imageviews
                Integer getOnceIndentedImageViewId = View.generateViewId();

                // assign this id to the newly grated once indented imageview
                ImageView onceIndentedImageView = findViewById(R.id.imageViewUIndentedTToBeAdded);
                onceIndentedImageView.setId(getOnceIndentedImageViewId);
                ///////

                ///////
                // generate id for new twice indented editbuttons
                Integer twiceIndentedEditButtonId = View.generateViewId();

                // set this id to the newly created twice indented edit button
                Button twiceIndentedEditButton = (Button) findViewById(R.id.edutButtonDoubleIndented);
                twiceIndentedEditButton.setId(twiceIndentedEditButtonId);
                //////


                map2.put(getLabelButtonId, getHiddenLayoutId);
                map4.put(twiceIndentedDeleteButtonId, twiceIndentedLinearLayoutToDeleteId); // add to delete dict
                map6.put(twiceIndentedEditButtonId, getLabelButtonId); // map edit button to label button
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


    // handle adding just normal subsections
    // this should add indented bye one views
    public void addNonIndentedSubject (View view) {

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
                m_Text5 = input.getText().toString();

                // create an id for the indented once layout that will be added
                Integer getLayoutId = View.generateViewId();


                // original below
                LinearLayout ll2 = (LinearLayout) findViewById(R.id.app2);

                LayoutInflater inflater = LayoutInflater.from(MainActivity3.this); // some context
                View row = inflater.inflate(R.layout.unindented_layout, null);


                ll2.addView(row);
                Button but6 = (Button) findViewById(R.id.button6);

                // try to set the id of the new layout after it has bean pl
                LinearLayout llUnindented = (LinearLayout) findViewById(R.id.doubleIndented);
                llUnindented.setId(getLayoutId);


                // generate tag for the full indented once layout to be deleted
                Integer onceIndentedLayoutToDeleteId = View.generateViewId();

                // set the once indented layout to be deleted to this id
                LinearLayout onceIndentedLayoutToDelete = (LinearLayout) findViewById(R.id.firstLayoutFromUnindented);
                onceIndentedLayoutToDelete.setId(onceIndentedLayoutToDeleteId);

                // generate an id for the delete button for the above layout
                Integer onceIndentedDeleteButtonId = View.generateViewId();

                // bind this id to  the once indented delete button
                Button onceIndentedDeleteButton = (Button) findViewById(R.id.deleteButton1);
                onceIndentedDeleteButton.setId(onceIndentedDeleteButtonId);

                // generate an   id for the 'add chapter' button that is added after adding a new indent by 1 layout
                Integer getButtonId = View.generateViewId();


                // setting the newly created button
                Button addedTheButton3 = (Button) findViewById(R.id.button6);
                // addedTheButton3.setId(buttonNum10);
                // addedTheButton3.setId(getButtonId);
                addedTheButton3.setText(m_Text5);
                layoutIdSet++;
                buttonNum10++;


                // set id of the "add new" button that was also just created
                Button addedTheButton4 = (Button) findViewById(R.id.addChapUnindented);
                addedTheButton4.setId(getButtonId);

                // generate an id for the label button that gets added one indent in
                Integer getLabelButtonOneIndent = View.generateViewId();

                addedTheButton3.setId(getLabelButtonOneIndent);

                // get an id for the new double indented layout we are using to hide parts of the layout
                Integer getHiddenOnceIndentedLayoutId = View.generateViewId();

                // find newly created once indented layout used to hide content and give it an id to map to map3
                LinearLayout llIndentedOnce = (LinearLayout) findViewById(R.id.onceIndentedHideLayout);
                llIndentedOnce.setId(getHiddenOnceIndentedLayoutId);

                ///////
                // generate id for new imageviews
                Integer getOnceIndentedImageViewId = View.generateViewId();

                // assign this id to the newly grated once indented imageview
                ImageView onceIndentedImageView = findViewById(R.id.imageViewUnindentedCheck);
                onceIndentedImageView.setId(getOnceIndentedImageViewId);
                ///////

                ///////
                // generate id for new once indented editbuttons
                Integer onceIndentedEditButtonId = View.generateViewId();

                // set this id to the newly created once indented edit button
                Button onceIndentedEditButton = (Button) findViewById(R.id.edutButtonIndentedOnce);
                onceIndentedEditButton.setId(onceIndentedEditButtonId);
                //////



                map.put(getButtonId, getLayoutId); // map 'add chapter' button to once indented layout
                map3.put(getLabelButtonOneIndent, getHiddenOnceIndentedLayoutId); // map label button to its once indented layout
                map4.put(onceIndentedDeleteButtonId, onceIndentedLayoutToDeleteId); // map once indented delete buttons to once indented layouts
                map6.put(onceIndentedEditButtonId, getLabelButtonOneIndent); // assign an edit button to the label/subsection button
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

    // edit buttons on start page
    public void editAaButtonSubsection (View view) {

        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout

        Button curBtn = (Button) findViewById(currentButtonPress);

        Integer curBtnIdToEditNum4 = map6.get(currentButtonPress); // call the below func on the subject button tied to the edit button
        openDialogueBox4(curBtnIdToEditNum4);

    }

    // opens dialogue box for editing buttons
    public void openDialogueBox4 (Integer currentBtn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Subsection Name");

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


    // from second guy working on uploading image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Bundle extras = getIntent().getExtras();
        Integer extra = getIntent().getIntExtra("imageViewId", 0);
        Integer curImageViewId = 0;
/*        if (extras != null) {
            curImageViewId = extras.getString("imageViewId");
        }*/
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            // ImageView imageViewer = findViewById(R.id.imageView);
            ImageView imageViewer = findViewById(imageViewBeingClicked);
            imageViewer.setImageURI(selectedImage);
        }
    }

    // used for hiding layouts in the dom
    public void hideALayout (View view) {

        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout
        LinearLayout ll = (LinearLayout) findViewById(map2.get(currentButtonPress));
        if (ll.getVisibility() == View.VISIBLE){
            ll.setVisibility(View.GONE);
        } else {
            ll.setVisibility(View.VISIBLE);
        }

    }

    // used for hiding layouts in the dom
    public void hideALayout2 (View view) {

        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout
        LinearLayout ll = (LinearLayout) findViewById(map3.get(currentButtonPress));
        if (ll.getVisibility() == View.VISIBLE){
            ll.setVisibility(View.GONE);
        } else if (ll.getVisibility() == View.GONE){
            ll.setVisibility(View.VISIBLE);
        } else {
            System.out.println("hide once indented not working" + currentButtonPress.toString());
        }

    }

    // used for hiding layouts in the dom
    public void deleteOnceIndentedLayout (View view) {
        Integer currentButtonPress = view.getId(); // get id of button to know where to add linearlayout
        LinearLayout ll = (LinearLayout) findViewById(map4.get(currentButtonPress));
        // ll.removeView(view);
        ll.setVisibility(View.GONE);

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