// Generated code from Butter Knife. Do not modify!
package com.example.nested.mainmenuactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RentalActivity_ViewBinding implements Unbinder {
  private RentalActivity target;

  @UiThread
  public RentalActivity_ViewBinding(RentalActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RentalActivity_ViewBinding(RentalActivity target, View source) {
    this.target = target;

    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.txtTitle = Utils.findRequiredViewAsType(source, R.id.txtRoomTitle, "field 'txtTitle'", TextView.class);
    target.rentalLayout = Utils.findRequiredViewAsType(source, R.id.rl_currentrental, "field 'rentalLayout'", RelativeLayout.class);
    target.notFound = Utils.findRequiredViewAsType(source, R.id.rental_anim_notfound, "field 'notFound'", LottieAnimationView.class);
    target.txtInfo = Utils.findRequiredViewAsType(source, R.id.txtInfo, "field 'txtInfo'", TextView.class);
    target.txtRoomPrice = Utils.findRequiredViewAsType(source, R.id.txtRoomPrice, "field 'txtRoomPrice'", TextView.class);
    target.btnCurrentRent = Utils.findRequiredView(source, R.id.currentRentPlaceholder, "field 'btnCurrentRent'");
  }

  @Override
  @CallSuper
  public void unbind() {
    RentalActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
    target.txtTitle = null;
    target.rentalLayout = null;
    target.notFound = null;
    target.txtInfo = null;
    target.txtRoomPrice = null;
    target.btnCurrentRent = null;
  }
}
