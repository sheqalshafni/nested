// Generated code from Butter Knife. Do not modify!
package com.example.nested.searchactivities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nested.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PostRoommateActivity_ViewBinding implements Unbinder {
  private PostRoommateActivity target;

  @UiThread
  public PostRoommateActivity_ViewBinding(PostRoommateActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PostRoommateActivity_ViewBinding(PostRoommateActivity target, View source) {
    this.target = target;

    target.radUser = Utils.findRequiredViewAsType(source, R.id.radUserType, "field 'radUser'", RadioGroup.class);
    target.radGender = Utils.findRequiredViewAsType(source, R.id.radGender, "field 'radGender'", RadioGroup.class);
    target.radState = Utils.findRequiredViewAsType(source, R.id.radRoomState, "field 'radState'", RadioGroup.class);
    target.radRType = Utils.findRequiredViewAsType(source, R.id.radRoomType, "field 'radRType'", RadioGroup.class);
    target.radPType = Utils.findRequiredViewAsType(source, R.id.radPropertyType, "field 'radPType'", RadioGroup.class);
    target.radProfession = Utils.findRequiredViewAsType(source, R.id.radProfession, "field 'radProfession'", RadioGroup.class);
    target.roomPrice = Utils.findRequiredViewAsType(source, R.id.etRoomPrice, "field 'roomPrice'", EditText.class);
    target.roomTitle = Utils.findRequiredViewAsType(source, R.id.etRoomTitle, "field 'roomTitle'", EditText.class);
    target.btnSubmit = Utils.findRequiredViewAsType(source, R.id.btnSubmit, "field 'btnSubmit'", Button.class);
    target.uploadImage = Utils.findRequiredViewAsType(source, R.id.uploadImg, "field 'uploadImage'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PostRoommateActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radUser = null;
    target.radGender = null;
    target.radState = null;
    target.radRType = null;
    target.radPType = null;
    target.radProfession = null;
    target.roomPrice = null;
    target.roomTitle = null;
    target.btnSubmit = null;
    target.uploadImage = null;
  }
}
