// Generated code from Butter Knife. Do not modify!
package org.magnum.videoup.client;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MedicationForPatientActivity$$ViewInjector {
  public static void inject(Finder finder, final org.magnum.videoup.client.MedicationForPatientActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296264, "field 'videoList_'");
    target.videoList_ = (android.widget.ListView) view;
  }

  public static void reset(org.magnum.videoup.client.MedicationForPatientActivity target) {
    target.videoList_ = null;
  }
}
