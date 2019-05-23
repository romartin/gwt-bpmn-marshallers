package org.submarine;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.submarine.client.XmlTest;

public class AppSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for App");
    suite.addTestSuite(XmlTest.class);
    return suite;
  }
}
