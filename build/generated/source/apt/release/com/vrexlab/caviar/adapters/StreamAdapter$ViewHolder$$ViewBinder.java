// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.adapters;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StreamAdapter$ViewHolder$$ViewBinder<T extends com.vrexlab.caviar.adapters.StreamAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165377, "field 'streamInfoText'");
    target.streamInfoText = finder.castView(view, 2131165377, "field 'streamInfoText'");
    view = finder.findRequiredView(source, 2131165375, "field 'streamCheckImage'");
    target.streamCheckImage = finder.castView(view, 2131165375, "field 'streamCheckImage'");
    view = finder.findRequiredView(source, 2131165275, "field 'frameStreamItem'");
    target.frameStreamItem = finder.castView(view, 2131165275, "field 'frameStreamItem'");
  }

  @Override public void unbind(T target) {
    target.streamInfoText = null;
    target.streamCheckImage = null;
    target.frameStreamItem = null;
  }
}
