package org.submarine;

import org.submarine.client.AppTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AppSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for App");
    suite.addTestSuite(AppTest.class);
    return suite;
  }
}
