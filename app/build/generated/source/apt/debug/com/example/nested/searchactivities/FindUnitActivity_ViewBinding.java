// Generated code from Butter Knife. Do not modify!
package com.example.nested.searchactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FindUnitActivity_ViewBinding implements Unbinder {
  private FindUnitActivity target;

  @UiThread
  public FindUnitActivity_ViewBinding(FindUnitActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FindUnitActivity_ViewBinding(FindUnitActivity target, View source) {
    this.target = target;

    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.unitRecyclerView = Utils.findRequiredViewAsType(source, R.id.unitRecyclerView, "field 'unitRecyclerView'", RecyclerView.class);
    target.btnPostUnit = Utils.findRequiredViewAsType(source, R.id.btnPostUnit, "field 'btnPostUnit'", FloatingActionButton.class);
    target.mRefreshList = Utils.findRequiredViewAsType(source, R.id.mUnitRefreshList, "field 'mRefreshList'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FindUnitActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
    target.unitRecyclerView = null;
    target.btnPostUnit = null;
    target.mRefreshList = null;
  }
}
