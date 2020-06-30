/*
 * $Id: Main.java,v 1.5 2013/01/15 10:55:00 agoubard Exp $
 */
package org.xins.demos.caller;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xins.client.XINSCallRequest;
import org.xins.client.XINSCallResult;
import org.xins.client.XINSServiceCaller;
import org.xins.common.collections.MapStringUtils;
import org.xins.common.service.Descriptor;
import org.xins.common.service.DescriptorBuilder;

/**
 * Executes a call to a XINS API.
 *
 * <p>This program expects 3 or 4 arguments:
 *
 * <ol>
 *    <li>Function caller configuration file (e.g. <code>caller.properties</code>)</li>
 *    <li>Function name (e.g. <code>ping</code>)</li>
 *    <li>Request file (e.g. <code>params.properties</code>)</li>
 *    <li>Count (optional, e.g. 3, default is 1)</li>
 * </ol>
 *
 * @version $Revision: 1.5 $ $Date: 2013/01/15 10:55:00 $
 * @author Ernst de Haan (<a href="mailto:znerd@FreeBSD.org">znerd@FreeBSD.org</a>)
 *
 * @since XINS 0.46
 */
public final class Main extends Object {

   /**
    * Main method.
    *
    * @param args
    *    the arguments passed to this program, not <code>null</code>.
    *
    * @throws Throwable
    *    if anything goes wrong.
    */
   public static void main(String[] args) throws Throwable {

      int argCount = (args != null) ? args.length : 0;

      if (argCount < 2 || argCount > 3) {
         System.err.println("Usage: java " + Main.class.getName() + " <config> <request> <count>");
         System.err.println("   <config>  -- Name of config file (required).");
         System.err.println("   <request> -- Name of request file (required).");
         System.err.println("   <count>   -- Number of times to execute the request (optional).");
         System.exit(1);
      }

      // Initialize Log4J
      Properties settings = new Properties();
      settings.setProperty("log4j.rootLogger",                                "DEBUG, console");
      settings.setProperty("log4j.appender.console",                          "org.apache.log4j.ConsoleAppender");
      settings.setProperty("log4j.appender.console.layout",                   "org.apache.log4j.PatternLayout");
      settings.setProperty("log4j.appender.console.layout.ConversionPattern", "%d %-5p [%c] %m%n");
      settings.setProperty("log4j.logger.org.apache.http",                    "WARN");
      PropertyConfigurator.configure(settings);
      Logger log = Logger.getLogger(Main.class.getName());

      // Get all parameters
      String configFileName  = args[0];
      String functionName = args[1];
      String paramsFile = args[2];
      int count = (argCount > 3) ? Integer.parseInt(args[3]) : 1;

      // Read the config file
      Map<String, String> properties = MapStringUtils.createMapString(new FileInputStream(configFileName));
      Descriptor descriptor = DescriptorBuilder.build(properties, "caller");
      XINSServiceCaller caller = new XINSServiceCaller(descriptor);

      // Read the request file
      Map<String, String> params = MapStringUtils.createMapString(new FileInputStream(paramsFile));
      XINSCallRequest request = new XINSCallRequest(functionName, params, null);

      // Execute the call(s)
      try {
         for (int i = 0; i < count; i++) {
            XINSCallResult result = caller.call(request);
            log.info("Call " + i + " performed (success=" + (result.getErrorCode() == null) + ").");
         }
      } catch (Throwable exception) {
         log.error("Failed to execute call.", exception);
      }
   }
}
