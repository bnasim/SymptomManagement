// Generated code from Butter Knife. Do not modify!
package org.magnum.videoup.client;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class TestBC$$ViewInjector {
  public static void inject(Finder finder, final org.magnum.videoup.client.TestBC target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296286, "field 'textview1_'");
    target.textview1_ = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296264, "field 'patientList_'");
    target.patientList_ = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131296351, "field 'edittext1_'");
    target.edittext1_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296305, "field 'textview2_'");
    target.textview2_ = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296306, "field 'textview3_'");
    target.textview3_ = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296352, "method 'find'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.find();
        }
      });
  }

  public static void reset(org.magnum.videoup.client.TestBC target) {
    target.textview1_ = null;
    target.patientList_ = null;
    target.edittext1_ = null;
    target.textview2_ = null;
    target.textview3_ = null;
  }
}
