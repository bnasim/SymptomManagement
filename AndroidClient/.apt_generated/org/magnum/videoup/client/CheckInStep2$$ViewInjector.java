// Generated code from Butter Knife. Do not modify!
package org.magnum.videoup.client;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CheckInStep2$$ViewInjector {
  public static void inject(Finder finder, final org.magnum.videoup.client.CheckInStep2 target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296306, "field 'textView3_'");
    target.textView3_ = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296305, "field 'textView2_'");
    target.textView2_ = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296307, "field 'textView4_'");
    target.textView4_ = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296286, "field 'textView1_'");
    target.textView1_ = (android.widget.TextView) view;
  }

  public static void reset(org.magnum.videoup.client.CheckInStep2 target) {
    target.textView3_ = null;
    target.textView2_ = null;
    target.textView4_ = null;
    target.textView1_ = null;
  }
}
