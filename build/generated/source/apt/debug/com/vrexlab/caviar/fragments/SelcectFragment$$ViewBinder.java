// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SelcectFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.SelcectFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165383, "field 'sureButton'");
    target.sureButton = finder.castView(view, 2131165383, "field 'sureButton'");
  }

  @Override public void unbind(T target) {
    target.sureButton = null;
  }
}
