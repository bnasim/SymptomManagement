// Generated code from Butter Knife. Do not modify!
package org.magnum.videoup.client;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PatientListActivity$$ViewInjector {
  public static void inject(Finder finder, final org.magnum.videoup.client.PatientListActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296288, "field 'patientList_'");
    target.patientList_ = (android.widget.ListView) view;
  }

  public static void reset(org.magnum.videoup.client.PatientListActivity target) {
    target.patientList_ = null;
  }
}
