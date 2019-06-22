// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.activitys;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LiveActivity$$ViewBinder<T extends com.vrexlab.caviar.activitys.LiveActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165272, "field 'frameLive'");
    target.frameLive = finder.castView(view, 2131165272, "field 'frameLive'");
  }

  @Override public void unbind(T target) {
    target.frameLive = null;
  }
}
