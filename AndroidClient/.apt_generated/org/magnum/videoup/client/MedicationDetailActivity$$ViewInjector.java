// Generated code from Butter Knife. Do not modify!
package org.magnum.videoup.client;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MedicationDetailActivity$$ViewInjector {
  public static void inject(Finder finder, final org.magnum.videoup.client.MedicationDetailActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296359, "field 'edit3_'");
    target.edit3_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296357, "field 'edit1_'");
    target.edit1_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296360, "field 'edit4_'");
    target.edit4_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296358, "field 'edit2_'");
    target.edit2_ = (android.widget.EditText) view;
  }

  public static void reset(org.magnum.videoup.client.MedicationDetailActivity target) {
    target.edit3_ = null;
    target.edit1_ = null;
    target.edit4_ = null;
    target.edit2_ = null;
  }
}
