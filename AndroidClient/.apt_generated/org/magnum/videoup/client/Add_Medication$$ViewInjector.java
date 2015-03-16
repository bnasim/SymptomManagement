// Generated code from Butter Knife. Do not modify!
package org.magnum.videoup.client;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class Add_Medication$$ViewInjector {
  public static void inject(Finder finder, final org.magnum.videoup.client.Add_Medication target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296293, "field 'rule_'");
    target.rule_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296292, "field 'med2Name_'");
    target.med2Name_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296291, "field 'med1Name_'");
    target.med1Name_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296290, "field 'patientName_'");
    target.patientName_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296294, "method 'login'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.login();
        }
      });
  }

  public static void reset(org.magnum.videoup.client.Add_Medication target) {
    target.rule_ = null;
    target.med2Name_ = null;
    target.med1Name_ = null;
    target.patientName_ = null;
  }
}
