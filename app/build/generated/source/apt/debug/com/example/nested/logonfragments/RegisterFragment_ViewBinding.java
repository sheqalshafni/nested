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

public class RegisterFragment_ViewBinding implements Unbinder {
  private RegisterFragment target;

  @UiThread
  public RegisterFragment_ViewBinding(RegisterFragment target, View source) {
    this.target = target;

    target.txtUsername = Utils.findRequiredViewAsType(source, R.id.txtUsername, "field 'txtUsername'", EditText.class);
    target.txtPassword = Utils.findRequiredViewAsType(source, R.id.txtPassword, "field 'txtPassword'", EditText.class);
    target.btnRegister = Utils.findRequiredViewAsType(source, R.id.btnRegister, "field 'btnRegister'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtUsername = null;
    target.txtPassword = null;
    target.btnRegister = null;
  }
}
