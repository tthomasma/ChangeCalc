package com.timthomasma.changecalc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {

    // gameState 0 7: (from customer) numberOfTwenties, numberOfTens,
    // numberOfFives,
    // numberOfOnes, numberOfQuarters, numberOfDimes, numberOfNickels,
    // numberOfPennies
    // gameState 8 - 9: dollarsFromCustomer, centsFromCustomer
    // gameState 10 - 11: dollarsPrice, centsPrice
    // gameState 12 - 13: dollarsToCustomer, centsToCustomer
    // gameState 14 - 21: (to customer) numberOfTwenties, numberOfTens,
    // numberOfFives,
    // numberOfOnes, numberOfQuarters, numberOfDimes, numberOfNickels,
    // numberOfPennies
    // gameState 22 - 23: dollars and cents for proposed solution
    // gameState 24 - 31: (to customer) numberOfTwenties, numberOfTens,
    // numberOfFives,
    // numberOfOnes, numberOfQuarters, numberOfDimes, numberOfNickels,
    // numberOfPennies
    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] twentiesId = {0, 0, 0, 0, 0};
    int[] tensId = {0, 0, 0, 0, 0};
    int[] fivesId = {0, 0, 0, 0, 0};
    int[] onesId = {0, 0, 0, 0, 0};
    int[] quartersId = {0, 0, 0, 0, 0};
    int[] dimesId = {0, 0, 0, 0, 0};
    int[] nickelsId = {0, 0, 0, 0, 0};
    int[] penniesId = {0, 0, 0, 0, 0};
    int theLevel;
    SharedPreferences sharedPreferences;
    ArrayList<Button> twentiesButtons = new ArrayList<Button>();
    ArrayList<Button> tensButtons = new ArrayList<Button>();
    ArrayList<Button> fivesButtons = new ArrayList<Button>();
    ArrayList<Button> onesButtons = new ArrayList<Button>();
    ArrayList<Button> quartersButtons = new ArrayList<Button>();
    ArrayList<Button> dimesButtons = new ArrayList<Button>();
    ArrayList<Button> nickelsButtons = new ArrayList<Button>();
    ArrayList<Button> penniesButtons = new ArrayList<Button>();
    EditText priceText;
    Button goButton, doneButton, newButton;
    TextView fromCustomerText, toCustomerText, levelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        priceText = (EditText) findViewById(R.id.editPriceText);
        goButton = (Button) findViewById(R.id.goButton);
        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setVisibility(View.GONE);
        newButton = (Button) findViewById(R.id.newButton);
        newButton.setVisibility(View.GONE);
        goButton.setOnClickListener(this);
        fromCustomerText = (TextView) findViewById(R.id.tenderedTextView);
        toCustomerText = (TextView) findViewById(R.id.toCustomerTextViewr);
        toCustomerText.setText("");
        levelView = (TextView) findViewById(R.id.levelView);
        loadIds();
        loadButtons();
        setOnClickListeners();
        int[] anythingInIntent = getIntent().getIntArrayExtra("gameState");
        if (anythingInIntent != null)
            gameState = anythingInIntent;
        repaint();
    }

    void loadIds() {
        twentiesId[0] = R.id.radioTwenty0;
        twentiesId[1] = R.id.radioTwenty1;
        twentiesId[2] = R.id.radioTwenty2;
        twentiesId[3] = R.id.radioTwenty3;
        twentiesId[4] = R.id.radioTwenty4;
        tensId[0] = R.id.radioTen0;
        tensId[1] = R.id.radioTen1;
        tensId[2] = R.id.radioTen2;
        tensId[3] = R.id.radioTen3;
        tensId[4] = R.id.radioTen4;
        fivesId[0] = R.id.radioFive0;
        fivesId[1] = R.id.radioFive1;
        fivesId[2] = R.id.radioFive2;
        fivesId[3] = R.id.radioFive3;
        fivesId[4] = R.id.radioFive4;
        onesId[0] = R.id.radioOne0;
        onesId[1] = R.id.radioOne1;
        onesId[2] = R.id.radioOne2;
        onesId[3] = R.id.radioOne3;
        onesId[4] = R.id.radioOne4;
        quartersId[0] = R.id.radioQuarter0;
        quartersId[1] = R.id.radioQuarter1;
        quartersId[2] = R.id.radioQuarter2;
        quartersId[3] = R.id.radioQuarter3;
        quartersId[4] = R.id.radioQuarter4;
        dimesId[0] = R.id.radioDime0;
        dimesId[1] = R.id.radioDime1;
        dimesId[2] = R.id.radioDime2;
        dimesId[3] = R.id.radioDime3;
        dimesId[4] = R.id.radioDime4;
        nickelsId[0] = R.id.radioNickel0;
        nickelsId[1] = R.id.radioNickel1;
        nickelsId[2] = R.id.radioNickel2;
        nickelsId[3] = R.id.radioNickel3;
        nickelsId[4] = R.id.radioNickel4;
        penniesId[0] = R.id.radioPenny0;
        penniesId[1] = R.id.radioPenny1;
        penniesId[2] = R.id.radioPenny2;
        penniesId[3] = R.id.radioPenny3;
        penniesId[4] = R.id.radioPenny4;
    }

    void loadButtons() {
        for (int i = 0; i < 5; i++) {
            twentiesButtons.add((Button) findViewById(twentiesId[i]));
        }
        for (int i = 0; i < 5; i++) {
            tensButtons.add((Button) findViewById(tensId[i]));
        }
        for (int i = 0; i < 5; i++) {
            fivesButtons.add((Button) findViewById(fivesId[i]));
        }
        for (int i = 0; i < 5; i++) {
            onesButtons.add((Button) findViewById(onesId[i]));
        }
        for (int i = 0; i < 5; i++) {
            quartersButtons.add((Button) findViewById(quartersId[i]));
        }
        for (int i = 0; i < 5; i++) {
            dimesButtons.add((Button) findViewById(dimesId[i]));
        }
        for (int i = 0; i < 5; i++) {
            nickelsButtons.add((Button) findViewById(nickelsId[i]));
        }
        for (int i = 0; i < 5; i++) {
            penniesButtons.add((Button) findViewById(penniesId[i]));
        }
    }

    void setOnClickListeners() {
        for (int i = 0; i < 5; i++) {
            twentiesButtons.get(i).setOnClickListener(this);
        }
        for (int i = 0; i < 5; i++) {
            tensButtons.get(i).setOnClickListener(this);
        }
        for (int i = 0; i < 5; i++) {
            fivesButtons.get(i).setOnClickListener(this);
        }
        for (int i = 0; i < 5; i++) {
            onesButtons.get(i).setOnClickListener(this);
        }
        for (int i = 0; i < 5; i++) {
            quartersButtons.get(i).setOnClickListener(this);
        }
        for (int i = 0; i < 5; i++) {
            dimesButtons.get(i).setOnClickListener(this);
        }
        for (int i = 0; i < 5; i++) {
            nickelsButtons.get(i).setOnClickListener(this);
        }
        for (int i = 0; i < 5; i++) {
            penniesButtons.get(i).setOnClickListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        theLevel = 0;
        switch (item.getItemId()) {
            case R.id.level1: {
                theLevel = 1;
                break;
            }
            case R.id.level2: {
                theLevel = 2;
                break;
            }
            case R.id.level3: {
                theLevel = 3;
                break;
            }
            case R.id.level4: {
                theLevel = 4;
                break;
            }
            case R.id.level5: {
                theLevel = 5;
                break;
            }
            case R.id.level6: {
                theLevel = 6;
                break;
            }
            case R.id.level7: {
                theLevel = 7;
                break;
            }
            case R.id.level8: {
                theLevel = 8;
                break;
            }
        }
        if (theLevel > 0) {
            levelView.setText("L" + theLevel);
            sharedPreferences = getSharedPreferences(
                    "makechange", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("level", theLevel);
            editor.commit();
            return true;
        }
        if (id == R.id.practice) {
            int a[] = readAmountText(priceText);
            if (a[2] == 0) {
                gameState[10] = 0;
                gameState[11] = 0;
            } else {
                gameState[10] = a[0];
                gameState[11] = a[1];
            }
            Intent intent = new Intent(this, PracticeActivity.class);
            intent.putExtra("gameState", gameState);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        boolean goodProblem = true;
        int vid = v.getId();
        if (vid == R.id.goButton) {
            int a[] = readAmountText(priceText);
            if (a[2] == 0) {
                goodProblem = false;
                Toast.makeText(this, "Please enter the price",
                        Toast.LENGTH_SHORT).show();
            }
            gameState[10] = a[0];
            gameState[11] = a[1];
            if (goodProblem
                    && (((100 * gameState[10]) + gameState[11]) > 14564)) {
                goodProblem = false;
                Toast.makeText(this, "Sorry, can't do prices over $145.64",
                        Toast.LENGTH_SHORT).show();
            }
            if (goodProblem
                    && (((100 * gameState[10]) + gameState[11]) > ((100 * gameState[8]) + gameState[9]))) {
                goodProblem = false;
                Toast.makeText(this, "Customer paid less than price",
                        Toast.LENGTH_SHORT).show();
            }
            if (goodProblem) {
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("gameState", gameState);
                startActivity(intent);
                finish();
            }
        } else {
            readFromButtons(vid);
            fromCustomerText.setText("Paid: $" + gameState[8] + "."
                    + printCents(gameState[9]));
            paintChangeButtons();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameState = savedInstanceState.getIntArray("gameState");
        repaint();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int a[] = readAmountText(priceText);
        gameState[10] = a[0];
        gameState[11] = a[1];
        outState.putIntArray("gameState", gameState);
    }

    void repaint() {
        fromCustomerText.setText("Paid: $" + gameState[8] + "."
                + printCents(gameState[9]));
        if ((gameState[10] + gameState[11]) > 0)
            priceText.setText("" + gameState[10] + "."
                    + printCents(gameState[11]));
        else
            priceText.setText("");
        sharedPreferences = getSharedPreferences(
                "makechange", 0);
        theLevel = sharedPreferences.getInt("level", 3);
        levelView.setText("L" + theLevel);
        paintChangeButtons();
    }

    void paintChangeButtons() {
        setOriginalColorsTwenties();
        setOriginalColorsTens();
        setOriginalColorsFives();
        setOriginalColorsOnes();
        setOriginalColorsQuarters();
        setOriginalColorsDimes();
        setOriginalColorsNickels();
        setOriginalColorsPennies();
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0: {
                    if (gameState[i] == 0) {
                        twentiesButtons.get(0).setBackgroundColor(
                                Color.parseColor("#685a56"));
                    } else {
                        twentiesButtons.get(gameState[i]).setBackgroundColor(
                                Color.parseColor("#3c8756"));
                    }
                    break;
                }
                case 1: {
                    if (gameState[i] == 0) {
                        tensButtons.get(0).setBackgroundColor(
                                Color.parseColor("#685a56"));
                    } else {
                        tensButtons.get(gameState[i]).setBackgroundColor(
                                Color.parseColor("#3c8756"));
                    }
                    break;
                }
                case 2: {
                    if (gameState[i] == 0) {
                        fivesButtons.get(0).setBackgroundColor(
                                Color.parseColor("#685a56"));
                    } else {
                        fivesButtons.get(gameState[i]).setBackgroundColor(
                                Color.parseColor("#3c8756"));
                    }
                    break;
                }
                case 3: {

                    if (gameState[i] == 0) {
                        onesButtons.get(0).setBackgroundColor(
                                Color.parseColor("#685a56"));
                        onesButtons.get(0)
                                .setTextColor(Color.parseColor("#ffffff"));
                    } else {
                        onesButtons.get(gameState[i]).setBackgroundColor(
                                Color.parseColor("#3c8756"));
                        onesButtons.get(gameState[i]).setTextColor(
                                Color.parseColor("#ffffff"));
                    }
                    break;
                }
                case 4: {
                    if (gameState[i] == 0) {
                        quartersButtons.get(0).setBackgroundColor(
                                Color.parseColor("#685a56"));
                    } else {
                        quartersButtons.get(gameState[i]).setBackgroundColor(
                                Color.parseColor("#3a5589"));
                    }
                    break;
                }
                case 5: {
                    if (gameState[i] == 0) {
                        dimesButtons.get(0).setBackgroundColor(
                                Color.parseColor("#685a56"));
                    } else {
                        dimesButtons.get(gameState[i]).setBackgroundColor(
                                Color.parseColor("#3a5589"));
                    }
                    break;
                }
                case 6: {
                    if (gameState[i] == 0) {
                        nickelsButtons.get(0).setBackgroundColor(
                                Color.parseColor("#685a56"));
                    } else {
                        nickelsButtons.get(gameState[i]).setBackgroundColor(
                                Color.parseColor("#3a5589"));
                    }
                    break;
                }
                case 7: {
                    if (gameState[i] == 0) {
                        penniesButtons.get(0).setBackgroundColor(
                                Color.parseColor("#685a56"));
                        penniesButtons.get(0).setTextColor(
                                Color.parseColor("#ffffff"));
                    } else {
                        penniesButtons.get(gameState[i]).setBackgroundColor(
                                Color.parseColor("#3a5589"));
                        penniesButtons.get(gameState[i]).setTextColor(
                                Color.parseColor("#ffffff"));
                    }
                    break;
                }
            }
        }
    }

    void setOriginalColorsTwenties() {
        twentiesButtons.get(0).setBackgroundColor(Color.parseColor("#f1efee"));
        for (int i = 1; i < 5; i++) {
            twentiesButtons.get(i).setBackgroundColor(
                    Color.parseColor("#e1f2e7"));
        }
    }

    void setOriginalColorsTens() {
        tensButtons.get(0).setBackgroundColor(Color.parseColor("#f1efee"));
        for (int i = 1; i < 5; i++) {
            tensButtons.get(i).setBackgroundColor(Color.parseColor("#cae7d4"));
        }
    }

    void setOriginalColorsFives() {
        fivesButtons.get(0).setBackgroundColor(Color.parseColor("#f1efee"));
        for (int i = 1; i < 5; i++) {
            fivesButtons.get(i).setBackgroundColor(Color.parseColor("#e1f2e7"));
        }
    }

    void setOriginalColorsOnes() {
        onesButtons.get(0).setBackgroundColor(Color.parseColor("#f1efee"));
        onesButtons.get(0).setTextColor(Color.parseColor("#000000"));
        for (int i = 1; i < 5; i++) {
            onesButtons.get(i).setBackgroundColor(Color.parseColor("#cae7d4"));
            onesButtons.get(i).setTextColor(Color.parseColor("#000000"));
        }
    }

    void setOriginalColorsQuarters() {
        quartersButtons.get(0).setBackgroundColor(Color.parseColor("#f1efee"));
        for (int i = 1; i < 5; i++) {
            quartersButtons.get(i).setBackgroundColor(
                    Color.parseColor("#e1e7f2"));
        }
    }

    void setOriginalColorsDimes() {
        dimesButtons.get(0).setBackgroundColor(Color.parseColor("#f1efee"));
        for (int i = 1; i < 5; i++) {
            dimesButtons.get(i).setBackgroundColor(Color.parseColor("#c9d3e8"));
        }
    }

    void setOriginalColorsNickels() {
        nickelsButtons.get(0).setBackgroundColor(Color.parseColor("#f1efee"));
        for (int i = 1; i < 5; i++) {
            nickelsButtons.get(i).setBackgroundColor(
                    Color.parseColor("#e1e7f2"));
        }
    }

    void setOriginalColorsPennies() {
        penniesButtons.get(0).setBackgroundColor(Color.parseColor("#f1efee"));
        penniesButtons.get(0).setTextColor(Color.parseColor("#000000"));
        for (int i = 1; i < 5; i++) {
            penniesButtons.get(i).setBackgroundColor(
                    Color.parseColor("#c9d3e8"));
            penniesButtons.get(i).setTextColor(Color.parseColor("#000000"));
        }
    }

    int[] readAmountText(TextView tv) {
        int toReturn[] = {0, 0, 0};
        String inputToParse = tv.getText().toString();
        if (inputToParse.equals(""))
            return toReturn;
        Float inputNumber = Float.parseFloat(inputToParse);
        toReturn[0] = (int) Math.floor(inputNumber);
        toReturn[1] = Math.round(inputNumber * 100) - (100 * toReturn[0]);
        toReturn[2] = 1;
        return toReturn;
    }

    public void readFromButtons(int vid) {
        gameState[8] = 0;
        gameState[9] = 0;
        for (int i = 0; i < 5; i++) {
            if (vid == twentiesId[i]) {
                gameState[0] = i;
            }
        }
        gameState[8] += (20 * gameState[0]);
        for (int i = 0; i < 5; i++) {
            if (vid == tensId[i]) {
                gameState[1] = i;
            }
        }
        gameState[8] += (10 * gameState[1]);
        for (int i = 0; i < 5; i++) {
            if (vid == fivesId[i]) {
                gameState[2] = i;
            }
        }
        gameState[8] += (5 * gameState[2]);
        for (int i = 0; i < 5; i++) {
            if (vid == onesId[i]) {
                gameState[3] = i;
            }
        }
        gameState[8] += (gameState[3]);
        for (int i = 0; i < 5; i++) {
            if (vid == quartersId[i]) {
                gameState[4] = i;
            }
        }
        gameState[9] += (25 * gameState[4]);
        for (int i = 0; i < 5; i++) {
            if (vid == dimesId[i]) {
                gameState[5] = i;
            }
        }
        gameState[9] += (10 * gameState[5]);
        for (int i = 0; i < 5; i++) {
            if (vid == nickelsId[i]) {
                gameState[6] = i;
            }
        }
        gameState[9] += (5 * gameState[6]);
        for (int i = 0; i < 5; i++) {
            if (vid == penniesId[i]) {
                gameState[7] = i;
            }
        }
        gameState[9] += (gameState[7]);
        if (gameState[9] > 99) {
            gameState[8]++;
            gameState[9] -= 100;
        }
    }

    static public String printCents(int cents) {
        String centsString = "";
        if (cents < 10)
            centsString += ("0" + cents);
        else
            centsString += cents;
        return centsString;
    }
}
