// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FeedbackTitleFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.FeedbackTitleFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165217, "field 'back'");
    target.back = finder.castView(view, 2131165217, "field 'back'");
    view = finder.findRequiredView(source, 2131165328, "field 'question'");
    target.question = finder.castView(view, 2131165328, "field 'question'");
    view = finder.findRequiredView(source, 2131165308, "field 'need'");
    target.need = finder.castView(view, 2131165308, "field 'need'");
    view = finder.findRequiredView(source, 2131165225, "field 'bug'");
    target.bug = finder.castView(view, 2131165225, "field 'bug'");
    view = finder.findRequiredView(source, 2131165316, "field 'other'");
    target.other = finder.castView(view, 2131165316, "field 'other'");
    view = finder.findRequiredView(source, 2131165329, "field 'questionTitle'");
    target.questionTitle = finder.castView(view, 2131165329, "field 'questionTitle'");
    view = finder.findRequiredView(source, 2131165309, "field 'needTitle'");
    target.needTitle = finder.castView(view, 2131165309, "field 'needTitle'");
    view = finder.findRequiredView(source, 2131165226, "field 'bugTitle'");
    target.bugTitle = finder.castView(view, 2131165226, "field 'bugTitle'");
    view = finder.findRequiredView(source, 2131165317, "field 'otherTitle'");
    target.otherTitle = finder.castView(view, 2131165317, "field 'otherTitle'");
  }

  @Override public void unbind(T target) {
    target.back = null;
    target.question = null;
    target.need = null;
    target.bug = null;
    target.other = null;
    target.questionTitle = null;
    target.needTitle = null;
    target.bugTitle = null;
    target.otherTitle = null;
  }
}
