// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DisplayFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.DisplayFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165301, "field 'maskSwitch' and method 'onViewClicked'");
    target.maskSwitch = finder.castView(view, 2131165301, "field 'maskSwitch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165334, "field 'resolutionSwitch' and method 'onViewClicked'");
    target.resolutionSwitch = finder.castView(view, 2131165334, "field 'resolutionSwitch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165276, "field 'framerateSwitch' and method 'onViewClicked'");
    target.framerateSwitch = finder.castView(view, 2131165276, "field 'framerateSwitch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165221, "field 'bitrateSwitch' and method 'onViewClicked'");
    target.bitrateSwitch = finder.castView(view, 2131165221, "field 'bitrateSwitch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165213, "field 'alertSwitch' and method 'onViewClicked'");
    target.alertSwitch = finder.castView(view, 2131165213, "field 'alertSwitch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165338, "field 'saveText' and method 'onViewClicked'");
    target.saveText = finder.castView(view, 2131165338, "field 'saveText'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.maskSwitch = null;
    target.resolutionSwitch = null;
    target.framerateSwitch = null;
    target.bitrateSwitch = null;
    target.alertSwitch = null;
    target.saveText = null;
  }
}
