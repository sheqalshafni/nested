// Generated code from Butter Knife. Do not modify!
package com.example.nested;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ViewSelectedRoommateActivity_ViewBinding implements Unbinder {
  private ViewSelectedRoommateActivity target;

  @UiThread
  public ViewSelectedRoommateActivity_ViewBinding(ViewSelectedRoommateActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ViewSelectedRoommateActivity_ViewBinding(ViewSelectedRoommateActivity target,
      View source) {
    this.target = target;

    target.txtUsername = Utils.findRequiredViewAsType(source, R.id.txtUsername, "field 'txtUsername'", TextView.class);
    target.txtContact = Utils.findRequiredViewAsType(source, R.id.txtContact, "field 'txtContact'", TextView.class);
    target.txtUserType = Utils.findRequiredViewAsType(source, R.id.txtUserType, "field 'txtUserType'", TextView.class);
    target.txtRoomTitle = Utils.findRequiredViewAsType(source, R.id.txtRoomTitle, "field 'txtRoomTitle'", TextView.class);
    target.txtGender = Utils.findRequiredViewAsType(source, R.id.txtPreferredGender, "field 'txtGender'", TextView.class);
    target.txtProfession = Utils.findRequiredViewAsType(source, R.id.txtPreferredProfession, "field 'txtProfession'", TextView.class);
    target.txtRoomPrice = Utils.findRequiredViewAsType(source, R.id.txtRoomPrice, "field 'txtRoomPrice'", TextView.class);
    target.txtRoomType = Utils.findRequiredViewAsType(source, R.id.txtRoomType, "field 'txtRoomType'", TextView.class);
    target.txtRoomState = Utils.findRequiredViewAsType(source, R.id.txtRoomState, "field 'txtRoomState'", TextView.class);
    target.mRoomImage = Utils.findRequiredViewAsType(source, R.id.roomImage, "field 'mRoomImage'", ImageView.class);
    target.btnEnquiry = Utils.findRequiredViewAsType(source, R.id.btnEnquiry, "field 'btnEnquiry'", Button.class);
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ViewSelectedRoommateActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtUsername = null;
    target.txtContact = null;
    target.txtUserType = null;
    target.txtRoomTitle = null;
    target.txtGender = null;
    target.txtProfession = null;
    target.txtRoomPrice = null;
    target.txtRoomType = null;
    target.txtRoomState = null;
    target.mRoomImage = null;
    target.btnEnquiry = null;
    target.btnBack = null;
  }
}
