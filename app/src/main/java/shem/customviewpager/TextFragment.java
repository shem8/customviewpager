package shem.customviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by shem on 7/19/15.
 */
public class TextFragment extends Fragment {
        private static final String TEXT_KEY = "TEXT";

    public static TextFragment newInstance(String text) {
        TextFragment f = new TextFragment();
        Bundle arg = new Bundle();
        arg.putString(TEXT_KEY, text);
        f.setArguments(arg);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_fragment, container, false);
        String text = getArguments().getString(TEXT_KEY);
        ((TextView)view.findViewById(R.id.textView)).setText(text);
        return view;
    }

}
