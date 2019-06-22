// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StreamSettingFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.StreamSettingFragment> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131165267, "field 'fpsFrame' and method 'onViewClicked'");
    target.fpsFrame = finder.castView(view, 2131165267, "field 'fpsFrame'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165322, "field 'pixelFrame' and method 'onViewClicked'");
    target.pixelFrame = finder.castView(view, 2131165322, "field 'pixelFrame'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165220, "field 'bitFrame' and method 'onViewClicked'");
    target.bitFrame = finder.castView(view, 2131165220, "field 'bitFrame'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165270, "field 'fpsTitle'");
    target.fpsTitle = finder.castView(view, 2131165270, "field 'fpsTitle'");
    view = finder.findRequiredView(source, 2131165269, "field 'fpsText'");
    target.fpsText = finder.castView(view, 2131165269, "field 'fpsText'");
    view = finder.findRequiredView(source, 2131165324, "field 'pixelTitle'");
    target.pixelTitle = finder.castView(view, 2131165324, "field 'pixelTitle'");
    view = finder.findRequiredView(source, 2131165323, "field 'pixelText'");
    target.pixelText = finder.castView(view, 2131165323, "field 'pixelText'");
    view = finder.findRequiredView(source, 2131165291, "field 'kbpsTitle'");
    target.kbpsTitle = finder.castView(view, 2131165291, "field 'kbpsTitle'");
    view = finder.findRequiredView(source, 2131165290, "field 'kbpsText'");
    target.kbpsText = finder.castView(view, 2131165290, "field 'kbpsText'");
  }

  @Override public void unbind(T target) {
    target.leftIcon = null;
    target.fpsFrame = null;
    target.pixelFrame = null;
    target.bitFrame = null;
    target.fpsTitle = null;
    target.fpsText = null;
    target.pixelTitle = null;
    target.pixelText = null;
    target.kbpsTitle = null;
    target.kbpsText = null;
  }
}
