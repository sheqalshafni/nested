// Generated code from Butter Knife. Do not modify!
package com.example.nested;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ViewSelectedRoom_ViewBinding implements Unbinder {
  private ViewSelectedRoom target;

  @UiThread
  public ViewSelectedRoom_ViewBinding(ViewSelectedRoom target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ViewSelectedRoom_ViewBinding(ViewSelectedRoom target, View source) {
    this.target = target;

    target.mUsername = Utils.findRequiredViewAsType(source, R.id.txtUsername, "field 'mUsername'", TextView.class);
    target.mContact = Utils.findRequiredViewAsType(source, R.id.txtContact, "field 'mContact'", TextView.class);
    target.mUserType = Utils.findRequiredViewAsType(source, R.id.txtUserType, "field 'mUserType'", TextView.class);
    target.mRoomTitle = Utils.findRequiredViewAsType(source, R.id.txtRoomTitle, "field 'mRoomTitle'", TextView.class);
    target.mRoomPrice = Utils.findRequiredViewAsType(source, R.id.txtRoomPrice, "field 'mRoomPrice'", TextView.class);
    target.mRoomType = Utils.findRequiredViewAsType(source, R.id.txtRoomType, "field 'mRoomType'", TextView.class);
    target.mRoomState = Utils.findRequiredViewAsType(source, R.id.txtRoomState, "field 'mRoomState'", TextView.class);
    target.mRoomImage = Utils.findRequiredViewAsType(source, R.id.roomImage, "field 'mRoomImage'", ImageView.class);
    target.mEnquiry = Utils.findRequiredViewAsType(source, R.id.btnEnquiry, "field 'mEnquiry'", Button.class);
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.btnRent = Utils.findRequiredViewAsType(source, R.id.btnRent, "field 'btnRent'", Button.class);
    target.mStartDate = Utils.findRequiredViewAsType(source, R.id.et_dateStart, "field 'mStartDate'", EditText.class);
    target.mEndDate = Utils.findRequiredViewAsType(source, R.id.et_dateEnd, "field 'mEndDate'", EditText.class);
    target.mDocumentID = Utils.findRequiredViewAsType(source, R.id.documentID, "field 'mDocumentID'", TextView.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.roomRL, "field 'relativeLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ViewSelectedRoom target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mUsername = null;
    target.mContact = null;
    target.mUserType = null;
    target.mRoomTitle = null;
    target.mRoomPrice = null;
    target.mRoomType = null;
    target.mRoomState = null;
    target.mRoomImage = null;
    target.mEnquiry = null;
    target.btnBack = null;
    target.btnRent = null;
    target.mStartDate = null;
    target.mEndDate = null;
    target.mDocumentID = null;
    target.relativeLayout = null;
  }
}
