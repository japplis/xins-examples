/*
 * $Id: SimpleTypesImpl.java,v 1.8 2007/09/18 11:27:13 agoubard Exp $
 */
package com.mycompany.allinone.api;

import org.xins.common.types.standard.Date;

/**
 * Implementation of the <code>SimpleTypes</code> function.
 *
 * @version $Revision: 1.8 $ $Date: 2007/09/18 11:27:13 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class SimpleTypesImpl extends SimpleTypes  {

   /**
    * Constructs a new <code>SimpleTypesImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public SimpleTypesImpl(APIImpl api) {
      super(api);
   }

   public final Result call(Request request) throws Throwable {

      short shortValue = -1;
      if (request.isSetInputShort()) {
         shortValue = request.getInputShort().shortValue();
         shortValue += 10;
      }

      SuccessfulResult result = new SuccessfulResult();
      result.setOutputShort(shortValue);
      result.setOutputInt(request.getInputByte() * 2);
      result.setOutputLong(14L);
      result.setOutputFloat(3.5F);
      result.setOutputDouble(3.1415);
      result.setOutputText("hello");
      Date.Value outputDate = new Date.Value(2004,6,21);
      result.setOutputDate(outputDate);

      if (request.isSetInputBinary()) {
         byte[] inputBinary = request.getInputBinary();
         result.setOutputBinary(inputBinary);
      }

      return result;
   }
}
