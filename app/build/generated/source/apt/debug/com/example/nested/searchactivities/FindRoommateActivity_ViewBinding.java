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

public class FindRoommateActivity_ViewBinding implements Unbinder {
  private FindRoommateActivity target;

  @UiThread
  public FindRoommateActivity_ViewBinding(FindRoommateActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FindRoommateActivity_ViewBinding(FindRoommateActivity target, View source) {
    this.target = target;

    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
    target.btnPostRoommate = Utils.findRequiredViewAsType(source, R.id.btnPostRoommate, "field 'btnPostRoommate'", FloatingActionButton.class);
    target.roommateRecyclerView = Utils.findRequiredViewAsType(source, R.id.roommateRecyclerView, "field 'roommateRecyclerView'", RecyclerView.class);
    target.mRefreshList = Utils.findRequiredViewAsType(source, R.id.mRoommateListRefresh, "field 'mRefreshList'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FindRoommateActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
    target.btnPostRoommate = null;
    target.roommateRecyclerView = null;
    target.mRefreshList = null;
  }
}
