// Generated code from Butter Knife. Do not modify!
package com.example.nested.payment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MakePaymentActivity_ViewBinding implements Unbinder {
  private MakePaymentActivity target;

  @UiThread
  public MakePaymentActivity_ViewBinding(MakePaymentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MakePaymentActivity_ViewBinding(MakePaymentActivity target, View source) {
    this.target = target;

    target.txtInvoiceNo = Utils.findRequiredViewAsType(source, R.id.txtInvoiceNo, "field 'txtInvoiceNo'", TextView.class);
    target.txtTenantName = Utils.findRequiredViewAsType(source, R.id.txtTenantName, "field 'txtTenantName'", TextView.class);
    target.txtRoomTitle = Utils.findRequiredViewAsType(source, R.id.txtRoomTitle, "field 'txtRoomTitle'", TextView.class);
    target.txtRoomPrice = Utils.findRequiredViewAsType(source, R.id.txtRoomPrice, "field 'txtRoomPrice'", TextView.class);
    target.txtSubtotal = Utils.findRequiredViewAsType(source, R.id.txtSubtotal, "field 'txtSubtotal'", TextView.class);
    target.btnPayrent = Utils.findRequiredViewAsType(source, R.id.btnPayRent, "field 'btnPayrent'", Button.class);
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.etReference = Utils.findRequiredViewAsType(source, R.id.etReference, "field 'etReference'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MakePaymentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtInvoiceNo = null;
    target.txtTenantName = null;
    target.txtRoomTitle = null;
    target.txtRoomPrice = null;
    target.txtSubtotal = null;
    target.btnPayrent = null;
    target.btnBack = null;
    target.etReference = null;
  }
}
