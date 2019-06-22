// Generated code from Butter Knife. Do not modify!
package com.vrexlab.caviar.adapters;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatAdapter$ViewHolder$$ViewBinder<T extends com.vrexlab.caviar.adapters.ChatAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165236, "field 'chatName'");
    target.chatName = finder.castView(view, 2131165236, "field 'chatName'");
    view = finder.findRequiredView(source, 2131165235, "field 'chatMessage'");
    target.chatMessage = finder.castView(view, 2131165235, "field 'chatMessage'");
  }

  @Override public void unbind(T target) {
    target.chatName = null;
    target.chatMessage = null;
  }
}
