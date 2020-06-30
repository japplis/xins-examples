/*
 * $Id: MatchRegExpImpl.java,v 1.6 2012/06/09 12:28:32 agoubard Exp $
 */
package com.mycompany.toolbox.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;

import org.xins.common.BeanUtils;
import org.xins.common.collections.InvalidPropertyValueException;
import org.xins.common.collections.MissingRequiredPropertyException;
import org.xins.common.io.IOReader;
import org.xins.common.manageable.InitializationException;


/**
 * Implementation of the <code>MatchRegExp</code> function.
 * This example is using the new JavaSE 6 javax.script API, meaning that in order
 * to compile and run this API you need at least JavaSE 6:
 * http://java.sun.com/javase/downloads/index.jsp
 *
 * <p>Description: Returns whether a text matches a regular expression.
 *
 * @version $Revision: 1.6 $ $Date: 2012/06/09 12:28:32 $
 * @author TODO
 */
public final class MatchRegExpImpl extends MatchRegExp {

   /**
    * The scripting engine.
    */
   private ScriptEngine _groovyEngine;

   /**
    * The script.
    */
   private String _matchScript;

   /**
    * Constructs a new <code>MatchRegExpImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public MatchRegExpImpl(APIImpl api) {
      super(api);
   }

   protected void initImpl(Map<String, String> properties)
   throws MissingRequiredPropertyException,
          InvalidPropertyValueException,
          InitializationException {
      ScriptEngineManager scriptingManager = new ScriptEngineManager();
      _groovyEngine = scriptingManager.getEngineByExtension("groovy");
      if (_groovyEngine == null) {
          throw new InitializationException("Cannot find scripting library.");
      }
      try {
          InputStream matchStream = getAPI().getResourceAsStream("/WEB-INF/classes/com/mycompany/toolbox/api/match.groovy");
          if (matchStream == null) {
              throw new InitializationException("Cannot find script at /WEB-INF/classes/com/mycompany/toolbox/api/match.groovy");
          }
          _matchScript = IOReader.readFully(matchStream);
      } catch (IOException ioe) {
          throw new InitializationException(ioe);
      }
   }

   /**
    * Calls this function. If the function fails, it may throw any kind of
    * exception. All exceptions will be handled by the caller.
    *
    * @param request
    *    the request, never <code>null</code>.
    *
    * @return
    *    the result of the function call, should never be <code>null</code>.
    *
    * @throws Throwable
    *    if anything went wrong.
    */
   public Result call(Request request) throws Throwable {
      SuccessfulResult result = new SuccessfulResult();

      // Put the request values in the script
      Bindings bindings = new SimpleBindings(BeanUtils.getParameters(request));

      // You may also want to catch the possible exceptions thrown
      Map<String, Object> values = (Map<String, Object>) _groovyEngine.eval(_matchScript, bindings);

      BeanUtils.setParameters(values, result);
      return result;
   }
}
