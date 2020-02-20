// Generated code from Butter Knife. Do not modify!
package com.example.nested.profileactivities.accountactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyListings_ViewBinding implements Unbinder {
  private MyListings target;

  @UiThread
  public MyListings_ViewBinding(MyListings target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyListings_ViewBinding(MyListings target, View source) {
    this.target = target;

    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.myListing = Utils.findRequiredViewAsType(source, R.id.myListingsRecyclerView, "field 'myListing'", RecyclerView.class);
    target.mListingNotFound = Utils.findRequiredViewAsType(source, R.id.splashcreen_anim, "field 'mListingNotFound'", LottieAnimationView.class);
    target.txtInfo = Utils.findRequiredViewAsType(source, R.id.txtInfo, "field 'txtInfo'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyListings target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
    target.myListing = null;
    target.mListingNotFound = null;
    target.txtInfo = null;
  }
}
