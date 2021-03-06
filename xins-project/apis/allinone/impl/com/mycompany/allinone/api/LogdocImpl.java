/*
 * $Id: LogdocImpl.java,v 1.9 2007/09/18 11:27:13 agoubard Exp $
 */
package com.mycompany.allinone.api;

import java.util.Date;

/**
 * Implementation of the <code>Logdoc</code> function.
 *
 * @version $Revision: 1.9 $ $Date: 2007/09/18 11:27:13 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class LogdocImpl extends Logdoc  {

   /**
    * Constructs a new <code>LogdocImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public LogdocImpl(APIImpl api) {
      super(api);
   }

   public final Result call(Request request) throws Throwable {
      String input = request.getInputText();
      int firstSpacePos = input.indexOf(' ');
      try {
         int number = -1;
         if (firstSpacePos != -1) {
            number = Integer.parseInt(input.substring(0, firstSpacePos));
         } else {
            number = Integer.parseInt(input);
         }
         Log.log_10000(input, number);
         boolean odd = number % 2 == 0;
         boolean thousands = number > 1000;
         long squareNumber = (long) (number * number);
         Log.log_10002(odd, new Boolean(thousands), squareNumber, new Date(), getClass().getName());
      } catch (NumberFormatException nfe) {
         Log.log_10001(nfe);
         return new InvalidNumberResult();
      }
      return new SuccessfulResult();
   }
}
