// Generated by view binder compiler. Do not edit!
package com.feature.series.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.feature.series.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemSeriesBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final AppCompatTextView tvBegin;

  @NonNull
  public final AppCompatTextView tvBeginDate;

  @NonNull
  public final AppCompatTextView tvEnd;

  @NonNull
  public final AppCompatTextView tvEndDate;

  @NonNull
  public final AppCompatTextView tvName;

  @NonNull
  public final AppCompatTextView tvSeason;

  @NonNull
  public final AppCompatTextView tvSeasonText;

  private ItemSeriesBinding(@NonNull MaterialCardView rootView, @NonNull AppCompatTextView tvBegin,
      @NonNull AppCompatTextView tvBeginDate, @NonNull AppCompatTextView tvEnd,
      @NonNull AppCompatTextView tvEndDate, @NonNull AppCompatTextView tvName,
      @NonNull AppCompatTextView tvSeason, @NonNull AppCompatTextView tvSeasonText) {
    this.rootView = rootView;
    this.tvBegin = tvBegin;
    this.tvBeginDate = tvBeginDate;
    this.tvEnd = tvEnd;
    this.tvEndDate = tvEndDate;
    this.tvName = tvName;
    this.tvSeason = tvSeason;
    this.tvSeasonText = tvSeasonText;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemSeriesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemSeriesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_series, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemSeriesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.tvBegin;
      AppCompatTextView tvBegin = ViewBindings.findChildViewById(rootView, id);
      if (tvBegin == null) {
        break missingId;
      }

      id = R.id.tvBeginDate;
      AppCompatTextView tvBeginDate = ViewBindings.findChildViewById(rootView, id);
      if (tvBeginDate == null) {
        break missingId;
      }

      id = R.id.tvEnd;
      AppCompatTextView tvEnd = ViewBindings.findChildViewById(rootView, id);
      if (tvEnd == null) {
        break missingId;
      }

      id = R.id.tvEndDate;
      AppCompatTextView tvEndDate = ViewBindings.findChildViewById(rootView, id);
      if (tvEndDate == null) {
        break missingId;
      }

      id = R.id.tvName;
      AppCompatTextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tvSeason;
      AppCompatTextView tvSeason = ViewBindings.findChildViewById(rootView, id);
      if (tvSeason == null) {
        break missingId;
      }

      id = R.id.tvSeasonText;
      AppCompatTextView tvSeasonText = ViewBindings.findChildViewById(rootView, id);
      if (tvSeasonText == null) {
        break missingId;
      }

      return new ItemSeriesBinding((MaterialCardView) rootView, tvBegin, tvBeginDate, tvEnd,
          tvEndDate, tvName, tvSeason, tvSeasonText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
