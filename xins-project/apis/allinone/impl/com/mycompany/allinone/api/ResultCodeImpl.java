/*
 * $Id: ResultCodeImpl.java,v 1.8 2007/09/18 11:27:13 agoubard Exp $
 */
package com.mycompany.allinone.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the <code>ResultCode</code> function.
 *
 * @version $Revision: 1.8 $ $Date: 2007/09/18 11:27:13 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class ResultCodeImpl extends ResultCode  {

   /**
    * Constructs a new <code>ResultCodeImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public ResultCodeImpl(APIImpl api) {
      super(api);
   }

   private Map paramsCount = new HashMap();

   public final Result call(Request request) throws Throwable {
      String parameter = request.getInputText();

      // Test if the input parameter contains no vowel
      if (parameter.indexOf('a') == -1 && parameter.indexOf('e') == -1 &&
          parameter.indexOf('i') == -1 && parameter.indexOf('o') == -1 &&
          parameter.indexOf('u') == -1 && parameter.indexOf('y') == -1) {
         return new NoVowelResult();
      }

      // Test in the local map
      if (paramsCount.containsKey(parameter)) {
         int count = ((Integer) paramsCount.get(parameter)).intValue();
         AlreadySetResult invalidResult = new AlreadySetResult();
         invalidResult.setCount(count);
         count++;
         paramsCount.put(parameter, new Integer(count));
         return invalidResult;
      }

      // Lookup in the shared map
      if (_sharedInstance.get(parameter) != null) {
         AlreadySetResult invalidResult = new AlreadySetResult();
         invalidResult.setCount(-1);
         return invalidResult;
      }

      paramsCount.put(parameter, new Integer(1));

      SuccessfulResult result = new SuccessfulResult();
      result.setOutputText(parameter + " added.");
      return result;
   }
}
