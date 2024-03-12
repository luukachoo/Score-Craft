// Generated by view binder compiler. Do not edit!
package com.feature.series.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.feature.series.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSeriesBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatTextView appCompatTextView;

  @NonNull
  public final AppCompatImageButton backBtn;

  @NonNull
  public final ProgressBar progress;

  @NonNull
  public final RecyclerView rvSeries;

  private FragmentSeriesBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatImageButton backBtn,
      @NonNull ProgressBar progress, @NonNull RecyclerView rvSeries) {
    this.rootView = rootView;
    this.appCompatTextView = appCompatTextView;
    this.backBtn = backBtn;
    this.progress = progress;
    this.rvSeries = rvSeries;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSeriesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSeriesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_series, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSeriesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appCompatTextView;
      AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(rootView, id);
      if (appCompatTextView == null) {
        break missingId;
      }

      id = R.id.backBtn;
      AppCompatImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.progress;
      ProgressBar progress = ViewBindings.findChildViewById(rootView, id);
      if (progress == null) {
        break missingId;
      }

      id = R.id.rvSeries;
      RecyclerView rvSeries = ViewBindings.findChildViewById(rootView, id);
      if (rvSeries == null) {
        break missingId;
      }

      return new FragmentSeriesBinding((ConstraintLayout) rootView, appCompatTextView, backBtn,
          progress, rvSeries);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
