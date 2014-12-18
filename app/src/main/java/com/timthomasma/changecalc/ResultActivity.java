package com.timthomasma.changecalc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends Activity {

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
	TextView priceTextView, levelView;
	TextView fromCustomerTextView;
	TextView toCustomerTextView;
	TextView twentiesTextView;
	TextView tensTextView;
	TextView fivesTextView;
	TextView onesTextView;
	TextView quartersTextView;
	TextView dimesTextView;
	TextView nickelsTextView;
	TextView penniesTextView;
	SharedPreferences sharedPreferences;
	int theLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		int[] anythingInIntent = getIntent().getIntArrayExtra("gameState");
		if (anythingInIntent != null)
			gameState = anythingInIntent;
		priceTextView = (TextView) findViewById(R.id.priceTextViewr);
		priceTextView.setText("Price: $" + gameState[10] + "."
				+ MainActivity.printCents(gameState[11]));
		fromCustomerTextView = (TextView) findViewById(R.id.tenderedTextViewr);
		fromCustomerTextView.setText("  Paid: $" + gameState[8] + "."
				+ MainActivity.printCents(gameState[9]));
		calculateChange();
		toCustomerTextView = (TextView) findViewById(R.id.toCustomerTextViewr);
		toCustomerTextView.setText("Change: $" + gameState[12] + "."
				+ MainActivity.printCents(gameState[13]));
		SharedPreferences sharedPreferences = getSharedPreferences(
				"makechange", 0);
		int theLevel = sharedPreferences.getInt("level", 3);
		levelView = (TextView) findViewById(R.id.levelTextr);
		levelView.setText("L" + theLevel);
		twentiesTextView = (TextView) findViewById(R.id.textViewTwentyr);
		if (gameState[14] == 0)
			twentiesTextView.setText("             $20s");
		else if (gameState[14] == 1) {
			twentiesTextView.setText("           " + gameState[14] + " $20 ");
		} else {
			twentiesTextView.setText("           " + gameState[14] + " $20s");
		}
		tensTextView = (TextView) findViewById(R.id.textViewTenr);
		if (gameState[15] == 0)
			tensTextView.setText("             $10s");
		else if (gameState[15] == 1) {
			tensTextView.setText("           " + gameState[15] + " $10 ");
		} else {
			tensTextView.setText("           " + gameState[15] + " $10s");
		}
		fivesTextView = (TextView) findViewById(R.id.textViewFiver);
		if (gameState[16] == 0)
			fivesTextView.setText("             $5s ");
		else if (gameState[16] == 1) {
			fivesTextView.setText("           " + gameState[16] + " $5  ");
		} else {
			fivesTextView.setText("           " + gameState[16] + " $5s ");
		}
		onesTextView = (TextView) findViewById(R.id.textViewOner);
		if (gameState[17] == 0)
			onesTextView.setText("             $1s ");
		else if (gameState[17] == 1) {
			onesTextView.setText("           " + gameState[17] + " $1  ");
		} else {
			onesTextView.setText("           " + gameState[17] + " $1s ");
		}
		quartersTextView = (TextView) findViewById(R.id.textViewQuarterr);
		if (gameState[18] == 0)
			quartersTextView.setText(" Quarters:  ");
		else {
			quartersTextView.setText(" Quarters: " + gameState[18]);
		}
		dimesTextView = (TextView) findViewById(R.id.textViewDimer);
		if (gameState[19] == 0)
			dimesTextView.setText(" Dimes:     ");
		else {
			dimesTextView.setText(" Dimes:    " + gameState[19]);
		}
		nickelsTextView = (TextView) findViewById(R.id.textViewNickelr);
		if (gameState[20] == 0)
			nickelsTextView.setText(" Nickels:   ");
		else {
			nickelsTextView.setText(" Nickels:  " + gameState[20]);
		}
		penniesTextView = (TextView) findViewById(R.id.textViewPennyr);
		if (gameState[21] == 0)
			penniesTextView.setText(" Pennies:   ");
		else {
			penniesTextView.setText(" Pennies:  " + gameState[21]);
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
			SharedPreferences sharedPreferences = getSharedPreferences(
					"makechange", 0);
			Editor editor = sharedPreferences.edit();
			editor.putInt("level", theLevel);
			editor.commit();
			return true;
		}
		if (id == R.id.practice) {
			Intent intent = new Intent(this, PracticeActivity.class);
			intent.putExtra("gameState", gameState);
			startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		gameState = savedInstanceState.getIntArray("gameState");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putIntArray("gameState", gameState);
	}

	void calculateChange() {
		int fromCustomer = (100 * gameState[8]) + gameState[9];
		int price = (100 * gameState[10]) + gameState[11];
		int change = fromCustomer - price;
		gameState[13] = change % 100;
		gameState[12] = (change - gameState[13]) / 100;
		int whatsleft = gameState[12];
		gameState[14] = whatsleft / 20;
		whatsleft %= 20;
		gameState[15] = whatsleft / 10;
		whatsleft %= 10;
		gameState[16] = whatsleft / 5;
		whatsleft %= 5;
		gameState[17] = whatsleft;
		whatsleft = gameState[13];
		gameState[18] = whatsleft / 25;
		whatsleft %= 25;
		gameState[19] = whatsleft / 10;
		whatsleft %= 10;
		gameState[20] = whatsleft / 5;
		whatsleft %= 5;
		gameState[21] = whatsleft;
	}

}
