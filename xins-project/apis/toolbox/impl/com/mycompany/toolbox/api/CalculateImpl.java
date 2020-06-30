/*
 * $Id: CalculateImpl.java,v 1.4 2007/03/12 10:46:16 agoubard Exp $
 */
package com.mycompany.toolbox.api;

import java.math.BigDecimal;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.xins.common.BeanUtils;


/**
 * Implementation of the <code>Calculate</code> function.
 *
 * <p>Description: Make a mathematical calculation.
 *
 * @version $Revision: 1.4 $ $Date: 2007/03/12 10:46:16 $
 * @author TODO
 */
public final class CalculateImpl extends Calculate {

   /**
    * Constructs a new <code>CalculateImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public CalculateImpl(APIImpl api) {
      super(api);
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
      GroovyShell shell = new GroovyShell();

      Object value = shell.evaluate(request.getCalculation());
      if (value instanceof Integer) {
         result.setResult(Double.parseDouble(value.toString()));
      } else if (value instanceof BigDecimal) {
         result.setResult(((BigDecimal)value).doubleValue());
      }
      return result;
   }
}
