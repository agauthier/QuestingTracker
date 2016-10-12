package com.home.questingtracker;

import com.home.questingtracker.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TrackerPageFragment extends Fragment {

	private static class Value {
		
		private int value = 0;
		
		public int getValue() {
			return value;
		}
		
		public void reset() {
			value = 0;
		}
		
		public void add(int valueToAdd) {
			value += valueToAdd;
		}
	}
	
	private static enum Op {
		PLUS {
			@Override public int calculate(int value) {
				return value;
			}
		},
		MINUS {
			@Override public int calculate(int value) {
				return -value;
			}
		};

		public abstract int calculate(int value);
	}

	private final String title;
	private Value threat = new Value();
	private Value will = new Value();
	
	public TrackerPageFragment(String title) {
		this.title = title;
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tracker_page, container, false);
		setText(view, R.id.title, title);
		setAllNumbersText(view);
		initOpButton(view, R.id.threat_plus_one, threat, Op.PLUS, 1);
		initOpButton(view, R.id.threat_plus_three, threat, Op.PLUS, 3);
		initOpButton(view, R.id.threat_plus_five, threat, Op.PLUS, 5);
		initOpButton(view, R.id.threat_plus_ten, threat, Op.PLUS, 10);
		initOpButton(view, R.id.threat_minus_one, threat, Op.MINUS, 1);
		initOpButton(view, R.id.threat_minus_three, threat, Op.MINUS, 3);
		initOpButton(view, R.id.threat_minus_five, threat, Op.MINUS, 5);
		initOpButton(view, R.id.threat_minus_ten, threat, Op.MINUS, 10);
		initOpButton(view, R.id.will_plus_one, will, Op.PLUS, 1);
		initOpButton(view, R.id.will_plus_three, will, Op.PLUS, 3);
		initOpButton(view, R.id.will_plus_five, will, Op.PLUS, 5);
		initOpButton(view, R.id.will_plus_ten, will, Op.PLUS, 10);
		initOpButton(view, R.id.will_minus_one, will, Op.MINUS, 1);
		initOpButton(view, R.id.will_minus_three, will, Op.MINUS, 3);
		initOpButton(view, R.id.will_minus_five, will, Op.MINUS, 5);
		initOpButton(view, R.id.will_minus_ten, will, Op.MINUS, 10);
		initClearButton(view);
		return view;
	}

	private void setAllNumbersText(View view) {
		setText(view, R.id.threat, String.valueOf(threat.getValue()));
		setText(view, R.id.will, String.valueOf(will.getValue()));
		setText(view, R.id.result, String.valueOf(will.getValue() - threat.getValue()));
	}

	private void setText(View view, int resId, String value) {
		TextView textView = (TextView) view.findViewById(resId);
		textView.setText(value);
	}

	private void initOpButton(final View view, int resId, final Value value, final Op op, final int opValue) {
		Button button = (Button) view.findViewById(resId);
		button.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View v) {
				value.add(op.calculate(opValue));
				setAllNumbersText(view);
			}
		});
	}

	private void initClearButton(final View view) {
		Button button = (Button) view.findViewById(R.id.clear);
		button.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View v) {
				threat.reset();
				will.reset();
				setAllNumbersText(view);
			}
		});
	}
}
