// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChooseModeFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.ChooseModeFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165333, "field 'recordText'");
    target.recordText = finder.castView(view, 2131165333, "field 'recordText'");
    view = finder.findRequiredView(source, 2131165332, "field 'recordFrame'");
    target.recordFrame = finder.castView(view, 2131165332, "field 'recordFrame'");
    view = finder.findRequiredView(source, 2131165379, "field 'streamingText'");
    target.streamingText = finder.castView(view, 2131165379, "field 'streamingText'");
    view = finder.findRequiredView(source, 2131165378, "field 'streamingFrame'");
    target.streamingFrame = finder.castView(view, 2131165378, "field 'streamingFrame'");
    view = finder.findRequiredView(source, 2131165384, "field 'sureChoose'");
    target.sureChoose = finder.castView(view, 2131165384, "field 'sureChoose'");
  }

  @Override public void unbind(T target) {
    target.recordText = null;
    target.recordFrame = null;
    target.streamingText = null;
    target.streamingFrame = null;
    target.sureChoose = null;
  }
}
