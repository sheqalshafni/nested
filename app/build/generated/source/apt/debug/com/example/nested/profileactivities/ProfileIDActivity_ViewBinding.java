// Generated code from Butter Knife. Do not modify!
package com.example.nested.profileactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileIDActivity_ViewBinding implements Unbinder {
  private ProfileIDActivity target;

  @UiThread
  public ProfileIDActivity_ViewBinding(ProfileIDActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileIDActivity_ViewBinding(ProfileIDActivity target, View source) {
    this.target = target;

    target.uploadImage = Utils.findRequiredViewAsType(source, R.id.profileImage, "field 'uploadImage'", CircleImageView.class);
    target.btnPInfo = Utils.findRequiredView(source, R.id.personalization_placeholder, "field 'btnPInfo'");
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.txtEmail = Utils.findRequiredViewAsType(source, R.id.txtUserEmail, "field 'txtEmail'", TextView.class);
    target.txtUsername = Utils.findRequiredViewAsType(source, R.id.txtUsername, "field 'txtUsername'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileIDActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.uploadImage = null;
    target.btnPInfo = null;
    target.btnBack = null;
    target.txtEmail = null;
    target.txtUsername = null;
  }
}
