// Generated code from Butter Knife. Do not modify!
package com.example.nested.profileactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileActivity_ViewBinding implements Unbinder {
  private ProfileActivity target;

  @UiThread
  public ProfileActivity_ViewBinding(ProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileActivity_ViewBinding(ProfileActivity target, View source) {
    this.target = target;

    target.btnCard = Utils.findRequiredView(source, R.id.card_placeholder, "field 'btnCard'");
    target.btnAddress = Utils.findRequiredView(source, R.id.address_placeholder, "field 'btnAddress'");
    target.btnProfile = Utils.findRequiredView(source, R.id.profile_placeholder, "field 'btnProfile'");
    target.btnPrivacy = Utils.findRequiredView(source, R.id.privacy_placeholder, "field 'btnPrivacy'");
    target.btnLanguage = Utils.findRequiredView(source, R.id.language_placeholder, "field 'btnLanguage'");
    target.btnAbout = Utils.findRequiredView(source, R.id.about_placeholder, "field 'btnAbout'");
    target.btnRules = Utils.findRequiredView(source, R.id.rules_placeholder, "field 'btnRules'");
    target.btnAccDelete = Utils.findRequiredView(source, R.id.accountdelete_placeholder, "field 'btnAccDelete'");
    target.btnSignout = Utils.findRequiredViewAsType(source, R.id.btnSignOut, "field 'btnSignout'", Button.class);
    target.btnSignin = Utils.findRequiredViewAsType(source, R.id.btnSignIn, "field 'btnSignin'", Button.class);
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnCard = null;
    target.btnAddress = null;
    target.btnProfile = null;
    target.btnPrivacy = null;
    target.btnLanguage = null;
    target.btnAbout = null;
    target.btnRules = null;
    target.btnAccDelete = null;
    target.btnSignout = null;
    target.btnSignin = null;
    target.btnBack = null;
  }
}
