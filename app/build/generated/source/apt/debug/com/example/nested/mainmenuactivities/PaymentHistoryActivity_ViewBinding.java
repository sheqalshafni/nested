// Generated code from Butter Knife. Do not modify!
package com.example.nested.mainmenuactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PaymentHistoryActivity_ViewBinding implements Unbinder {
  private PaymentHistoryActivity target;

  @UiThread
  public PaymentHistoryActivity_ViewBinding(PaymentHistoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PaymentHistoryActivity_ViewBinding(PaymentHistoryActivity target, View source) {
    this.target = target;

    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.paymentRV = Utils.findRequiredViewAsType(source, R.id.paymentHistoryRecyclerView, "field 'paymentRV'", RecyclerView.class);
    target.mPaymentRefresh = Utils.findRequiredViewAsType(source, R.id.mPaymentHistoryRefresh, "field 'mPaymentRefresh'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PaymentHistoryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
    target.paymentRV = null;
    target.mPaymentRefresh = null;
  }
}
