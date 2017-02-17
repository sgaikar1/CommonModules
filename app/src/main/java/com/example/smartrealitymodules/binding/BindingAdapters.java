package com.example.smartrealitymodules.binding;

import android.databinding.BindingAdapter;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.utils.TextChangeListener;
import com.squareup.picasso.Picasso;

/**
 * Created by troy379 on 15.03.16.
 */
public final class BindingAdapters {
    private BindingAdapters() { throw new AssertionError(); }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("android:text")
    public static void bindEditText(EditText view,
                                    final ObservableString observableString) {
        Pair<ObservableString, TextChangeListener> pair = (Pair) view.getTag(R.id.bound_observable);

        if (pair == null || pair.first != observableString) {
            if (pair != null) view.removeTextChangedListener(pair.second);

//            TextChangeListener watcher = new TextChangeListener(
//                    (s, start, before, count) -> observableString.set(s.toString()));

            TextChangeListener watcher = new TextChangeListener(new TextChangeListener.OnTextChangedListener() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    observableString.set(s.toString());
                }
            });
            view.setTag(R.id.bound_observable, new Pair<>(observableString, watcher));
            view.addTextChangedListener(watcher);
        }
        String newValue = observableString.get();
        if (!view.getText().toString().equals(newValue))
            view.setText(newValue);
    }

    @BindingAdapter("app:onClick")
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runnable.run();
            }
        });
    }
}
