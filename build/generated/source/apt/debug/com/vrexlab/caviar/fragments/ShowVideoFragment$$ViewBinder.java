// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ShowVideoFragment$$ViewBinder<T extends com.vrexlab.caviar.fragments.ShowVideoFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165414, "field 'videoView'");
    target.videoView = finder.castView(view, 2131165414, "field 'videoView'");
    view = finder.findRequiredView(source, 2131165325, "field 'playVideo'");
    target.playVideo = finder.castView(view, 2131165325, "field 'playVideo'");
    view = finder.findRequiredView(source, 2131165396, "field 'textSave'");
    target.textSave = finder.castView(view, 2131165396, "field 'textSave'");
    view = finder.findRequiredView(source, 2131165397, "field 'textShare'");
    target.textShare = finder.castView(view, 2131165397, "field 'textShare'");
  }

  @Override public void unbind(T target) {
    target.videoView = null;
    target.playVideo = null;
    target.textSave = null;
    target.textShare = null;
  }
}
