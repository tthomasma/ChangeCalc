package com.timthomasma.changecalc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticeResultActivity extends Activity implements OnClickListener {

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
	int[] gameState = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int[] twentiesId = { 0, 0, 0, 0, 0 };
	int[] tensId = { 0, 0, 0, 0, 0 };
	int[] fivesId = { 0, 0, 0, 0, 0 };
	int[] onesId = { 0, 0, 0, 0, 0 };
	int[] quartersId = { 0, 0, 0, 0, 0 };
	int[] dimesId = { 0, 0, 0, 0, 0 };
	int[] nickelsId = { 0, 0, 0, 0, 0 };
	int[] penniesId = { 0, 0, 0, 0, 0 };
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
	EditText priceEditText;
	Button goButton, doneButton, newButton;
	TextView fromCustomerText, toCustomerText, priceText, levelView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		priceEditText = (EditText) findViewById(R.id.editPriceText);
		priceEditText.setVisibility(View.GONE);
		goButton = (Button) findViewById(R.id.goButton);
		goButton.setVisibility(View.GONE);
		priceText = (TextView) findViewById(R.id.priceTextView);
		doneButton = (Button) findViewById(R.id.doneButton);
		doneButton.setOnClickListener(this);
		newButton = (Button) findViewById(R.id.newButton);
		newButton.setOnClickListener(this);
		fromCustomerText = (TextView) findViewById(R.id.tenderedTextView);
		toCustomerText = (TextView) findViewById(R.id.toCustomerTextViewr);
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
		getMenuInflater().inflate(R.menu.practiceresult, menu);
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
			sharedPreferences = getSharedPreferences("makechange", 0);
			Editor editor = sharedPreferences.edit();
			editor.putInt("level", theLevel);
			editor.commit();
			return true;
		}
		if (id == R.id.calculate) {
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("gameState", gameState);
			startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		if (vid == R.id.doneButton) {
			int correct = ((100 * gameState[8]) + gameState[9])
					- ((100 * gameState[10]) + gameState[11]);
			int proposedSolution = (100 * gameState[22]) + gameState[23];
			if (proposedSolution == correct) {
				toCustomerText.setText(" Change: $" + gameState[22] + "."
						+ MainActivity.printCents(gameState[23]));
				Toast.makeText(this, "That's it! Correct change!",
						Toast.LENGTH_SHORT).show();
			} else if (proposedSolution < correct) {
				Toast.makeText(this,
						"Sorry, give the customer more than that.",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Sorry, don't the customer too much.",
						Toast.LENGTH_SHORT).show();
			}

		} else if (vid == R.id.newButton) {
			// zero out the whole problem, generate a new one, and go to
			// PracticeActivity
			sharedPreferences = getSharedPreferences("makechange", 0);
			theLevel = sharedPreferences.getInt("level", 3);
			newProblem(gameState, theLevel);
			Intent intent = new Intent(this, PracticeActivity.class);
			intent.putExtra("gameState", gameState);
			startActivity(intent);
			finish();
		} else {
			readFromButtons(vid);
			fromCustomerText.setText("Paid: $" + gameState[8] + "."
					+ MainActivity.printCents(gameState[9]));
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
		outState.putIntArray("gameState", gameState);
	}

	void readFromButtons(int vid) {
		gameState[22] = 0;
		gameState[23] = 0;
		for (int i = 0; i < 5; i++) {
			if (vid == twentiesId[i]) {
				gameState[24] = i;
			}
		}
		gameState[22] += (20 * gameState[24]);
		for (int i = 0; i < 5; i++) {
			if (vid == tensId[i]) {
				gameState[25] = i;
			}
		}
		gameState[22] += (10 * gameState[25]);
		for (int i = 0; i < 5; i++) {
			if (vid == fivesId[i]) {
				gameState[26] = i;
			}
		}
		gameState[22] += (5 * gameState[26]);
		for (int i = 0; i < 5; i++) {
			if (vid == onesId[i]) {
				gameState[27] = i;
			}
		}
		gameState[22] += (gameState[27]);
		for (int i = 0; i < 5; i++) {
			if (vid == quartersId[i]) {
				gameState[28] = i;
			}
		}
		gameState[23] += (25 * gameState[28]);
		for (int i = 0; i < 5; i++) {
			if (vid == dimesId[i]) {
				gameState[29] = i;
			}
		}
		gameState[23] += (10 * gameState[29]);
		for (int i = 0; i < 5; i++) {
			if (vid == nickelsId[i]) {
				gameState[30] = i;
			}
		}
		gameState[23] += (5 * gameState[30]);
		for (int i = 0; i < 5; i++) {
			if (vid == penniesId[i]) {
				gameState[31] = i;
			}
		}
		gameState[23] += (gameState[31]);
		if (gameState[23] > 99) {
			gameState[22]++;
			gameState[23] -= 100;
		}
		sharedPreferences = getSharedPreferences("makechange", 0);
		theLevel = sharedPreferences.getInt("level", 3);
		if (!((theLevel == 4) || (theLevel == 8)))
			toCustomerText.setText(" Change: $" + gameState[22] + "."
					+ MainActivity.printCents(gameState[23]) + "?");
		else
			toCustomerText.setText("");
	}

	void repaint() {
		fromCustomerText.setText("Paid: $" + gameState[8] + "."
				+ MainActivity.printCents(gameState[9]));
		if ((gameState[10] + gameState[11]) > 0)
			priceText.setText("Price: $" + gameState[10] + "."
					+ MainActivity.printCents(gameState[11]));
		else
			priceText.setText("");
		sharedPreferences = getSharedPreferences("makechange", 0);
		theLevel = sharedPreferences.getInt("level", 3);
		levelView.setText("L" + theLevel);
		if (!((theLevel == 4) || (theLevel == 8)))
			toCustomerText.setText(" Change: $" + gameState[22] + "."
					+ MainActivity.printCents(gameState[23]) + "?");
		else
			toCustomerText.setText("");
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
		for (int i = 24; i < 32; i++) {
			switch (i) {
			case 24: {
				if (gameState[i] == 0) {
					twentiesButtons.get(0).setBackgroundColor(
							Color.parseColor("#685a56"));
				} else {
					twentiesButtons.get(gameState[i]).setBackgroundColor(
							Color.parseColor("#3c8756"));
				}
				break;
			}
			case 25: {
				if (gameState[i] == 0) {
					tensButtons.get(0).setBackgroundColor(
							Color.parseColor("#685a56"));
				} else {
					tensButtons.get(gameState[i]).setBackgroundColor(
							Color.parseColor("#3c8756"));
				}
				break;
			}
			case 26: {
				if (gameState[i] == 0) {
					fivesButtons.get(0).setBackgroundColor(
							Color.parseColor("#685a56"));
				} else {
					fivesButtons.get(gameState[i]).setBackgroundColor(
							Color.parseColor("#3c8756"));
				}
				break;
			}
			case 27: {

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
			case 28: {
				if (gameState[i] == 0) {
					quartersButtons.get(0).setBackgroundColor(
							Color.parseColor("#685a56"));
				} else {
					quartersButtons.get(gameState[i]).setBackgroundColor(
							Color.parseColor("#3a5589"));
				}
				break;
			}
			case 29: {
				if (gameState[i] == 0) {
					dimesButtons.get(0).setBackgroundColor(
							Color.parseColor("#685a56"));
				} else {
					dimesButtons.get(gameState[i]).setBackgroundColor(
							Color.parseColor("#3a5589"));
				}
				break;
			}
			case 30: {
				if (gameState[i] == 0) {
					nickelsButtons.get(0).setBackgroundColor(
							Color.parseColor("#685a56"));
				} else {
					nickelsButtons.get(gameState[i]).setBackgroundColor(
							Color.parseColor("#3a5589"));
				}
				break;
			}
			case 31: {
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

	static public void newProblem(int[] theGameState, int level) {
		int max = 14564;
		int price = 0;
		int price2 = 0;
		if (level == 1)
			max = 10000;
		if (level == 5)
			max = 500;
		if (level == 6)
			max = 2000;
		for (int i = 0; i < theGameState.length; i++) {
			theGameState[i] = 0;
		}
		Random numberGenerator = new Random();
		if (level > 4) {
			price = 1 + numberGenerator.nextInt(max);
			theGameState[10] = price / 100;
			theGameState[11] = price - (100 * theGameState[10]);
		} else {
			price = (1 + numberGenerator.nextInt(10)) * 10;
			price2 = (1 + numberGenerator.nextInt(100));
			if (level == 1) {
				theGameState[10] = price;
				theGameState[11] = 0;
			}
			if (level == 2) {
				theGameState[10] = 0;
				theGameState[11] = price2;
			}
			if ((level == 3) || (level == 4)) {
				theGameState[10] = price;
				theGameState[11] = price2;
			}
		}
		int[] allDenominations = { 0, 1, 2, 3, 4, 5, 6, 7 };
		ArrayList<Integer> pullFrom = new ArrayList<Integer>();
		ArrayList<Integer> putInto = new ArrayList<Integer>();
		for (int i = 0; i < allDenominations.length; i++) {
			pullFrom.add((Integer) allDenominations[i]);
		}
		if (level > 4) {
			int numberOfDenominations = 1 + numberGenerator.nextInt(8);
			for (int i = 0; i < numberOfDenominations; i++) {
				Collections.shuffle(pullFrom);
				putInto.add(pullFrom.get(0));
				pullFrom.remove(0);
			}
		} else if (level == 1) {
			putInto.add(pullFrom.get(0));
			pullFrom.remove(0);
			putInto.add(pullFrom.get(0));
			pullFrom.remove(0);
		} else if (level == 2) {
			putInto.add(pullFrom.get(7));
			pullFrom.remove(7);
			putInto.add(pullFrom.get(6));
			pullFrom.remove(6);
			putInto.add(pullFrom.get(5));
			pullFrom.remove(5);
		} else if ((level == 3) || (level == 4)) {
			putInto.add(pullFrom.get(7));
			pullFrom.remove(7);
			putInto.add(pullFrom.get(6));
			pullFrom.remove(6);
			putInto.add(pullFrom.get(5));
			pullFrom.remove(5);
			putInto.add(pullFrom.get(0));
			pullFrom.remove(0);
			putInto.add(pullFrom.get(0));
			pullFrom.remove(0);
		}
		if (level > 1) {
			Collections.sort(putInto);
			Collections.reverse(putInto);
			for (Integer theDenomination : putInto) {
				incrementDenomination(theDenomination, theGameState);
			}
			if (((100 * theGameState[8]) + theGameState[9]) >= ((100 * theGameState[10]) + theGameState[11]))
				return;
			for (int i = 0; i < 5; i++) {
				for (Integer theDenomination : putInto) {
					incrementDenomination(theDenomination, theGameState);
					if (((100 * theGameState[8]) + theGameState[9]) >= ((100 * theGameState[10]) + theGameState[11]))
						return;
				}
			}
			Collections.sort(pullFrom);
			Collections.reverse(pullFrom);
			for (int i = 0; i < 5; i++) {
				for (Integer theDenomination : pullFrom) {
					incrementDenomination(theDenomination, theGameState);
					if (((100 * theGameState[8]) + theGameState[9]) >= ((100 * theGameState[10]) + theGameState[11]))
						return;
				}
			}
		} else {
			int maxToAdd, toAdd, change, priceInTens;
			priceInTens = theGameState[10] / 10;
			maxToAdd = 11 - priceInTens;
			System.out.println("" + maxToAdd + " " + priceInTens);
			toAdd = 2 + numberGenerator.nextInt(maxToAdd);
			change = 0;
			System.out.println("" + maxToAdd + " " + toAdd + " " + change + " "
					+ priceInTens);
			while (change < (priceInTens + toAdd)) {
				if ((change + 2) > (priceInTens + toAdd)) {
					change += 1;
					incrementDenomination(1, theGameState);
					return;
				} else {
					change += 2;
					incrementDenomination(0, theGameState);
				}
			}
		}
	}

	static void incrementDenomination(int denomination, int[] theGameState) {
		for (int i = 0; i < 8; i++) {
			if (denomination == i) {
				theGameState[denomination]++;
				if (theGameState[denomination] > 4)
					theGameState[denomination] = 4;
			}
		}
		theGameState[8] = (20 * theGameState[0]) + (10 * theGameState[1])
				+ (5 * theGameState[2]) + theGameState[3];
		theGameState[9] = (25 * theGameState[4]) + (10 * theGameState[5])
				+ (5 * theGameState[6]) + theGameState[7];
		if (theGameState[9] > 99) {
			theGameState[8]++;
			theGameState[9] -= 100;
		}
	}
}
