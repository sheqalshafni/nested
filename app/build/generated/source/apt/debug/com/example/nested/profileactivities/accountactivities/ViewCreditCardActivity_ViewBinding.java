// Generated code from Butter Knife. Do not modify!
package com.example.nested.profileactivities.accountactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ViewCreditCardActivity_ViewBinding implements Unbinder {
  private ViewCreditCardActivity target;

  @UiThread
  public ViewCreditCardActivity_ViewBinding(ViewCreditCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ViewCreditCardActivity_ViewBinding(ViewCreditCardActivity target, View source) {
    this.target = target;

    target.txtCardNumber = Utils.findRequiredViewAsType(source, R.id.cardNumber, "field 'txtCardNumber'", TextView.class);
    target.expiryDate = Utils.findRequiredViewAsType(source, R.id.expiryDate, "field 'expiryDate'", TextView.class);
    target.cardType = Utils.findRequiredViewAsType(source, R.id.cardtype_image, "field 'cardType'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ViewCreditCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtCardNumber = null;
    target.expiryDate = null;
    target.cardType = null;
  }
}
