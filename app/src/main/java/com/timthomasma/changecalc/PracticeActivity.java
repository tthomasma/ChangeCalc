package com.timthomasma.changecalc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PracticeActivity extends Activity implements OnClickListener {

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
	Button goButton;
	TextView fromCustomerTextView;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practice, menu);
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_practice);
		int[] anythingInIntent = getIntent().getIntArrayExtra("gameState");
		if (anythingInIntent != null)
			gameState = anythingInIntent;
		boolean goodProblem = true;
		if ((gameState[10] + gameState[11]) == 0)
			goodProblem = false;
		if (((100 * gameState[10]) + gameState[11]) > 14564)
			goodProblem = false;
		if (((100 * gameState[10]) + gameState[11]) > ((100 * gameState[8]) + gameState[9]))
			goodProblem = false;
		sharedPreferences = getSharedPreferences("makechange", 0);
		theLevel = sharedPreferences.getInt("level", 3);
		levelView = (TextView) findViewById(R.id.levelViewp);
		levelView.setText("L" + theLevel);
		if (!goodProblem)
			PracticeResultActivity.newProblem(gameState, theLevel);
		priceTextView = (TextView) findViewById(R.id.priceTextViewp);
		priceTextView.setText("Price: $" + gameState[10] + "."
				+ MainActivity.printCents(gameState[11]));
		goButton = (Button) findViewById(R.id.goButtonp);
		goButton.setOnClickListener(this);
		fromCustomerTextView = (TextView) findViewById(R.id.tenderedTextViewp);
		fromCustomerTextView.setText("Paid: $" + gameState[8] + "."
				+ MainActivity.printCents(gameState[9]));
		twentiesTextView = (TextView) findViewById(R.id.textViewTwentyp);
		if (gameState[0] == 0)
			twentiesTextView.setText("             $20s");
		else if (gameState[0] == 1) {
			twentiesTextView.setText("           " + gameState[0] + " $20 ");
		} else {
			twentiesTextView.setText("           " + gameState[0] + " $20s");
		}
		tensTextView = (TextView) findViewById(R.id.textViewTenp);
		if (gameState[1] == 0)
			tensTextView.setText("             $10s");
		else if (gameState[1] == 1) {
			tensTextView.setText("           " + gameState[1] + " $10 ");
		} else {
			tensTextView.setText("           " + gameState[1] + " $10s");
		}
		fivesTextView = (TextView) findViewById(R.id.textViewFivep);
		if (gameState[2] == 0)
			fivesTextView.setText("             $5s ");
		else if (gameState[2] == 1) {
			fivesTextView.setText("           " + gameState[2] + " $5  ");
		} else {
			fivesTextView.setText("           " + gameState[2] + " $5s ");
		}
		onesTextView = (TextView) findViewById(R.id.textViewOnep);
		if (gameState[3] == 0)
			onesTextView.setText("             $1s ");
		else if (gameState[3] == 1) {
			onesTextView.setText("           " + gameState[3] + " $1  ");
		} else {
			onesTextView.setText("           " + gameState[3] + " $1s ");
		}
		quartersTextView = (TextView) findViewById(R.id.textViewQuarterp);
		if (gameState[4] == 0)
			quartersTextView.setText(" Quarters:  ");
		else {
			quartersTextView.setText(" Quarters: " + gameState[4]);
		}
		dimesTextView = (TextView) findViewById(R.id.textViewDimep);
		if (gameState[5] == 0)
			dimesTextView.setText(" Dimes:     ");
		else {
			dimesTextView.setText(" Dimes:    " + gameState[5]);
		}
		nickelsTextView = (TextView) findViewById(R.id.textViewNickelp);
		if (gameState[6] == 0)
			nickelsTextView.setText(" Nickels:   ");
		else {
			nickelsTextView.setText(" Nickels:  " + gameState[6]);
		}
		penniesTextView = (TextView) findViewById(R.id.textViewPennyp);
		if (gameState[7] == 0)
			penniesTextView.setText(" Pennies:   ");
		else {
			penniesTextView.setText(" Pennies:  " + gameState[7]);
		}
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		if (vid == R.id.goButtonp) {
			Intent intent = new Intent(this, PracticeResultActivity.class);
			intent.putExtra("gameState", gameState);
			startActivity(intent);
			finish();
		}
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
}
