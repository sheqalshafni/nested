// Generated code from Butter Knife. Do not modify!
package com.example.nested.profileactivities.accountactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import com.tenbis.support.views.CompactCreditInput;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardDetailsActivity_ViewBinding implements Unbinder {
  private CardDetailsActivity target;

  @UiThread
  public CardDetailsActivity_ViewBinding(CardDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CardDetailsActivity_ViewBinding(CardDetailsActivity target, View source) {
    this.target = target;

    target.btnAddCard = Utils.findRequiredViewAsType(source, R.id.btnAddCard, "field 'btnAddCard'", Button.class);
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.txtCardDetails = Utils.findRequiredViewAsType(source, R.id.txtCardDetails, "field 'txtCardDetails'", CompactCreditInput.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CardDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnAddCard = null;
    target.btnBack = null;
    target.txtCardDetails = null;
  }
}
