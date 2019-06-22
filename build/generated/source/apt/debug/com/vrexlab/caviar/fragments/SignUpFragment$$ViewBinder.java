// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SignUpFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.SignUpFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165413, "field 'usernameEdit'");
    target.usernameEdit = finder.castView(view, 2131165413, "field 'usernameEdit'");
    view = finder.findRequiredView(source, 2131165228, "field 'buttonConfirm'");
    target.buttonConfirm = finder.castView(view, 2131165228, "field 'buttonConfirm'");
  }

  @Override public void unbind(T target) {
    target.usernameEdit = null;
    target.buttonConfirm = null;
  }
}
