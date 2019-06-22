// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SettingFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.SettingFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165293, "field 'leftIcon' and method 'onViewClicked'");
    target.leftIcon = finder.castView(view, 2131165293, "field 'leftIcon'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165307, "field 'myAccountFrame' and method 'onViewClicked'");
    target.myAccountFrame = finder.castView(view, 2131165307, "field 'myAccountFrame'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165407, "field 'twitchIngest' and method 'onViewClicked'");
    target.twitchIngest = finder.castView(view, 2131165407, "field 'twitchIngest'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165224, "field 'broadcastFrame' and method 'onViewClicked'");
    target.broadcastFrame = finder.castView(view, 2131165224, "field 'broadcastFrame'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165299, "field 'logoutFrame' and method 'onViewClicked'");
    target.logoutFrame = finder.castView(view, 2131165299, "field 'logoutFrame'");
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
    target.leftIcon = null;
    target.myAccountFrame = null;
    target.twitchIngest = null;
    target.broadcastFrame = null;
    target.logoutFrame = null;
  }
}
