// Generated code from Butter Knife. Do not modify!
package com.example.nested;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target, View source) {
    this.target = target;

    target.btnListing = Utils.findRequiredViewAsType(source, R.id.btnListing, "field 'btnListing'", ImageView.class);
    target.btnRental = Utils.findRequiredViewAsType(source, R.id.btnRental, "field 'btnRental'", ImageView.class);
    target.btnPaymentRecords = Utils.findRequiredViewAsType(source, R.id.btnPaymentRecords, "field 'btnPaymentRecords'", ImageView.class);
    target.btnProfile = Utils.findRequiredViewAsType(source, R.id.imgUser, "field 'btnProfile'", ImageView.class);
    target.imgRoom = Utils.findRequiredViewAsType(source, R.id.imgRoom, "field 'imgRoom'", RoundedImageView.class);
    target.imgRoommate = Utils.findRequiredViewAsType(source, R.id.imgRoommate, "field 'imgRoommate'", RoundedImageView.class);
    target.imgUnit = Utils.findRequiredViewAsType(source, R.id.imgUnit, "field 'imgUnit'", RoundedImageView.class);
    target.txtGreetings = Utils.findRequiredViewAsType(source, R.id.txtGreetings, "field 'txtGreetings'", TextView.class);
    target.txtCurrentTime = Utils.findRequiredViewAsType(source, R.id.txtCurrentTime, "field 'txtCurrentTime'", TextView.class);
    target.btnRoom = Utils.findRequiredView(source, R.id.btnRoomList, "field 'btnRoom'");
    target.btnRoommate = Utils.findRequiredView(source, R.id.btnRoommateList, "field 'btnRoommate'");
    target.btnUnit = Utils.findRequiredView(source, R.id.btnUnitList, "field 'btnUnit'");
    target.parentLayout = Utils.findRequiredViewAsType(source, R.id.parent_layout, "field 'parentLayout'", ScrollView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnListing = null;
    target.btnRental = null;
    target.btnPaymentRecords = null;
    target.btnProfile = null;
    target.imgRoom = null;
    target.imgRoommate = null;
    target.imgUnit = null;
    target.txtGreetings = null;
    target.txtCurrentTime = null;
    target.btnRoom = null;
    target.btnRoommate = null;
    target.btnUnit = null;
    target.parentLayout = null;
  }
}
