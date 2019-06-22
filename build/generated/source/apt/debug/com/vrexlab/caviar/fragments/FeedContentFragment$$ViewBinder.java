// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FeedContentFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.FeedContentFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165217, "field 'back'");
    target.back = finder.castView(view, 2131165217, "field 'back'");
    view = finder.findRequiredView(source, 2131165355, "field 'send'");
    target.send = finder.castView(view, 2131165355, "field 'send'");
    view = finder.findRequiredView(source, 2131165354, "field 'selectTitle'");
    target.selectTitle = finder.castView(view, 2131165354, "field 'selectTitle'");
    view = finder.findRequiredView(source, 2131165244, "field 'contentEdit'");
    target.contentEdit = finder.castView(view, 2131165244, "field 'contentEdit'");
    view = finder.findRequiredView(source, 2131165286, "field 'imageSupport'");
    target.imageSupport = finder.castView(view, 2131165286, "field 'imageSupport'");
    view = finder.findRequiredView(source, 2131165411, "field 'uploadPicture'");
    target.uploadPicture = finder.castView(view, 2131165411, "field 'uploadPicture'");
    view = finder.findRequiredView(source, 2131165400, "field 'title'");
    target.title = finder.castView(view, 2131165400, "field 'title'");
  }

  @Override public void unbind(T target) {
    target.back = null;
    target.send = null;
    target.selectTitle = null;
    target.contentEdit = null;
    target.imageSupport = null;
    target.uploadPicture = null;
    target.title = null;
  }
}
