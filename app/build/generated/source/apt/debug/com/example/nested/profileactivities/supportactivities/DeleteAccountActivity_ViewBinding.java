// Generated code from Butter Knife. Do not modify!
package com.example.nested.profileactivities.supportactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DeleteAccountActivity_ViewBinding implements Unbinder {
  private DeleteAccountActivity target;

  @UiThread
  public DeleteAccountActivity_ViewBinding(DeleteAccountActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DeleteAccountActivity_ViewBinding(DeleteAccountActivity target, View source) {
    this.target = target;

    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DeleteAccountActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
  }
}
