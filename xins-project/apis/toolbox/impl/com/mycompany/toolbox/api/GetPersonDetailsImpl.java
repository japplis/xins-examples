/*
 * $Id: GetPersonDetailsImpl.java,v 1.5 2012/06/09 12:28:32 agoubard Exp $
 */
package com.mycompany.toolbox.api;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.xins.common.BeanUtils;
import org.xins.common.collections.InvalidPropertyValueException;
import org.xins.common.collections.MissingRequiredPropertyException;
import org.xins.common.io.IOReader;
import org.xins.common.manageable.InitializationException;

/**
 * Implementation of the <code>GetPersonDetails</code> function.
 *
 * <p>Description: Gets the detail of some people.
 *
 * @version $Revision: 1.5 $ $Date: 2012/06/09 12:28:32 $
 * @author TODO
 */
public final class GetPersonDetailsImpl extends GetPersonDetails {

   /**
    * The script.
    */
   private String _matchScript;

   /**
    * Constructs a new <code>GetPersonDetailsImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public GetPersonDetailsImpl(APIImpl api) {
      super(api);
   }

   protected void initImpl(Map<String, String> properties)
   throws MissingRequiredPropertyException,
          InvalidPropertyValueException,
          InitializationException {
      try {
          InputStream matchStream = getAPI().getResourceAsStream("/WEB-INF/classes/com/mycompany/toolbox/api/personsStub.groovy");
          if (matchStream == null) {
              throw new InitializationException("Cannot find script at /WEB-INF/classes/com/mycompany/toolbox/api/personsStub.groovy");
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

      // Put the values of the request in the script
      Binding binding = new Binding(BeanUtils.getParameters(request));
      GroovyShell shell = new GroovyShell(binding);

      Object value = shell.evaluate(_matchScript);
      BeanUtils.setParameters((Map) value, result);

      return result;
   }
}
