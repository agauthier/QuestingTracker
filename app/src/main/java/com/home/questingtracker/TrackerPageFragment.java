package com.home.questingtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

public class TrackerPageFragment extends Fragment {

    public static String TITLE_KEY = "TITLE_KEY";
    public static String THREAT_KEY = "THREAT_KEY";
    public static String WILL_KEY = "WILL_KEY";
    private static float SMALL_TEXT_SCALE = 0.4f;
    private static float LARGE_TEXT_SCALE = 0.9f;
    private static float TEXT_SIZE_STEP = 5;

    private class Value {

        private int value = 0;

        int getValue() {
            return value;
        }
        void setValue(int value) {
            this.value = value;
        }
        void add(int valueToAdd) {
            value += valueToAdd;
        }
    }

    private enum Op {
        PLUS {
            @Override
            public int calculate(int value) {
                return value;
            }
        },
        MINUS {
            @Override
            public int calculate(int value) {
                return -value;
            }
        };

        public abstract int calculate(int value);
    }

    private enum ScaleBy {
        HEIGHT_ONLY, HEIGHT_AND_WIDTH
    }

    private Value threat = new Value();
    private Value will = new Value();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tracker_page, container, false);
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

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                scaleAllText(view);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setText(R.id.title, getArguments().getString(TITLE_KEY));
        if (savedInstanceState != null) {
            threat.setValue(savedInstanceState.getInt(THREAT_KEY));
            will.setValue(savedInstanceState.getInt(WILL_KEY));
        }
        setAllNumbersText();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(THREAT_KEY, threat.getValue());
        outState.putInt(WILL_KEY, will.getValue());
        super.onSaveInstanceState(outState);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setAllNumbersText();
//    }

    private void initOpButton(final View view, int resId, final Value value, final Op op, final int opValue) {
        Button button = view.findViewById(resId);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value.add(op.calculate(opValue));
                setAllNumbersText();
            }
        });
    }

    private void initClearButton(final View view) {
        Button button = view.findViewById(R.id.clear);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                threat.setValue(0);
                will.setValue(0);
                setAllNumbersText();
            }
        });
    }

    private void setAllNumbersText() {
        setText(R.id.threat, String.valueOf(threat.getValue()));
        setText(R.id.will, String.valueOf(will.getValue()));
        setText(R.id.result, String.valueOf(will.getValue() - threat.getValue()));
    }

    private void setText(int resId, String value) {
        @SuppressWarnings("ConstantConditions") TextView textView = getView().findViewById(resId);
        textView.setText(value);
        scaleTextToView(textView, LARGE_TEXT_SCALE);
    }

    private void scaleAllText(View view) {
        scaleTextToView((TextView) view.findViewById(R.id.threat), LARGE_TEXT_SCALE);
        scaleTextToView((TextView) view.findViewById(R.id.will), LARGE_TEXT_SCALE);
        scaleTextToView((TextView) view.findViewById(R.id.result), LARGE_TEXT_SCALE);
        scaleTextToView((TextView) view.findViewById(R.id.title), SMALL_TEXT_SCALE);
        scaleTextToView((TextView) view.findViewById(R.id.clear), SMALL_TEXT_SCALE);
        scaleTextToView((TextView) view.findViewById(R.id.threat_plus_one), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.threat_plus_three), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.threat_plus_five), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.threat_plus_ten), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.threat_minus_one), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.threat_minus_three), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.threat_minus_five), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.threat_minus_ten), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.will_plus_one), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.will_plus_three), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.will_plus_five), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.will_plus_ten), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.will_minus_one), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.will_minus_three), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.will_minus_five), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
        scaleTextToView((TextView) view.findViewById(R.id.will_minus_ten), SMALL_TEXT_SCALE, ScaleBy.HEIGHT_ONLY);
    }

    private void scaleTextToView(TextView textView, float scale) {
        scaleTextToView(textView, scale, ScaleBy.HEIGHT_AND_WIDTH);
    }

    private void scaleTextToView(TextView textView, float scale, ScaleBy byHeight) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getHeight());
        float textWidth = textView.getPaint().measureText(String.valueOf(textView.getText()));
        if (byHeight == ScaleBy.HEIGHT_AND_WIDTH && textWidth > textView.getWidth()) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, floor(scale * textView.getTextSize() * textView.getWidth() / textWidth));
        } else {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, floor(scale * textView.getTextSize()));
        }
    }

    private float floor(float number) {
        return (float) (TEXT_SIZE_STEP * Math.floor(number / TEXT_SIZE_STEP));
    }
}
