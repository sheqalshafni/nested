// Generated code from Butter Knife. Do not modify!
package com.example.nested.logonfragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignInFragment_ViewBinding implements Unbinder {
  private SignInFragment target;

  @UiThread
  public SignInFragment_ViewBinding(SignInFragment target, View source) {
    this.target = target;

    target.guestSignIn = Utils.findRequiredViewAsType(source, R.id.btnGuest, "field 'guestSignIn'", Button.class);
    target.signin = Utils.findRequiredViewAsType(source, R.id.btnSignIn, "field 'signin'", Button.class);
    target.txtUsername = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'txtUsername'", EditText.class);
    target.txtPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'txtPassword'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignInFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.guestSignIn = null;
    target.signin = null;
    target.txtUsername = null;
    target.txtPassword = null;
  }
}
