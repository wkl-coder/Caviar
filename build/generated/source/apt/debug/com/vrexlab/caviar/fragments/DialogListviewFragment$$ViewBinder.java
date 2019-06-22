// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DialogListviewFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.DialogListviewFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165338, "field 'saveText'");
    target.saveText = finder.castView(view, 2131165338, "field 'saveText'");
    view = finder.findRequiredView(source, 2131165297, "field 'listViewStreamInfo'");
    target.listViewStreamInfo = finder.castView(view, 2131165297, "field 'listViewStreamInfo'");
  }

  @Override public void unbind(T target) {
    target.saveText = null;
    target.listViewStreamInfo = null;
  }
}
