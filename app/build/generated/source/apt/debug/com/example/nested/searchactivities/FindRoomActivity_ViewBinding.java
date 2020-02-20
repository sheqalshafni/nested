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

public class FindRoomActivity_ViewBinding implements Unbinder {
  private FindRoomActivity target;

  @UiThread
  public FindRoomActivity_ViewBinding(FindRoomActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FindRoomActivity_ViewBinding(FindRoomActivity target, View source) {
    this.target = target;

    target.room_RecyclerView = Utils.findRequiredViewAsType(source, R.id.roomRecyclerView, "field 'room_RecyclerView'", RecyclerView.class);
    target.fabPostRoom = Utils.findRequiredViewAsType(source, R.id.btnPostRoom, "field 'fabPostRoom'", FloatingActionButton.class);
    target.mRefreshList = Utils.findRequiredViewAsType(source, R.id.mRoomListRefresh, "field 'mRefreshList'", SwipeRefreshLayout.class);
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btnBack, "field 'btnBack'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FindRoomActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.room_RecyclerView = null;
    target.fabPostRoom = null;
    target.mRefreshList = null;
    target.btnBack = null;
  }
}
