package ch.hsr.waktu;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;

import com.trolltech.qt.gui.QApplication;

public class TestSuite {
	
	private boolean run = false;
	
	@Before
	public void testSuiteSetUp() {
		
		if(run) {
			String[] args = {};
			QApplication.initialize(args);
		}
		ClassLoader loader = TestSuite.class.getClassLoader();
		URL url = loader.getResource("testsettings");
    	PropertyConfigurator.configure(url);
	}
}
