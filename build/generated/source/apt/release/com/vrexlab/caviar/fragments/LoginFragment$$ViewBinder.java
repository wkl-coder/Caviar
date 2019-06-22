// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.LoginFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165373, "field 'startTwitch'");
    target.startTwitch = finder.castView(view, 2131165373, "field 'startTwitch'");
    view = finder.findRequiredView(source, 2131165371, "field 'startEmail'");
    target.startEmail = finder.castView(view, 2131165371, "field 'startEmail'");
    view = finder.findRequiredView(source, 2131165372, "field 'startNow'");
    target.startNow = finder.castView(view, 2131165372, "field 'startNow'");
  }

  @Override public void unbind(T target) {
    target.startTwitch = null;
    target.startEmail = null;
    target.startNow = null;
  }
}
