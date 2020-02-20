// Generated code from Butter Knife. Do not modify!
package com.example.nested.profileactivities.accountactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardActivity_ViewBinding implements Unbinder {
  private CardActivity target;

  @UiThread
  public CardActivity_ViewBinding(CardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CardActivity_ViewBinding(CardActivity target, View source) {
    this.target = target;

    target.btnViewCard = Utils.findRequiredView(source, R.id.default_card_placeholder, "field 'btnViewCard'");
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.btnAddCard = Utils.findRequiredViewAsType(source, R.id.btnAddCard, "field 'btnAddCard'", FloatingActionButton.class);
    target.txtCardNumber = Utils.findRequiredViewAsType(source, R.id.txtCardNumber, "field 'txtCardNumber'", TextView.class);
    target.cardType = Utils.findRequiredViewAsType(source, R.id.cardType, "field 'cardType'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnViewCard = null;
    target.btnBack = null;
    target.btnAddCard = null;
    target.txtCardNumber = null;
    target.cardType = null;
  }
}
