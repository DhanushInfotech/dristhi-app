package org.ei.telemedicine.test.doctor;

import android.content.Intent;

import org.ei.telemedicine.BuildConfig;
import org.ei.telemedicine.doctor.DoctorANCScreenActivity;
import org.ei.telemedicine.doctor.DoctorFormDataConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;

@Config(sdk = 21, manifest = "src/main/AndroidManifest.xml", constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class DoctorANCScreenActivityTest {

    private DoctorANCScreenActivity doctorANCScreenActivity;

    @Before
    public void setup() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra(DoctorFormDataConstants.formData, "sdsd");
        ActivityController activityController = Robolectric.buildActivity(DoctorANCScreenActivity.class).withIntent(intent.putExtra(DoctorFormDataConstants.formData, "sdf")).create();
    }

    @Test
    public void testSample() {
        assertEquals("2", 1 + 1 + "");
    }
}