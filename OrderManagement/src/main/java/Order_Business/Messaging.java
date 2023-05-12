/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order_Business;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.Event;
import io.kubemq.sdk.tools.Converter;

import javax.net.ssl.SSLException;
import java.io.IOException;

/**
 *
 * @author student
 */
public class Messaging {

    public static void sendmessage(String message) throws IOException {
        String channelName = "order_channel",
                clientID = "order-subscriber";
                String kubeMQAddress = System.getenv("kubeMQAddress");
         
  io.kubemq.sdk.event.Channel channel = new io.kubemq.sdk.event.Channel(channelName, clientID, false,
          kubeMQAddress);
 
     channel.setStore(true);
      Event event = new Event();
      event.setBody(Converter.ToByteArray(message));
      event.setEventId("event-Store-" );
      System.out.println("SETTING UP MESSAGING");
      try {
          channel.SendEvent(event);
          System.out.println("SENT MESSAGE");
      } catch (SSLException e) {
          System.out.printf("SSLException: %s", e.getMessage());
          e.printStackTrace();
      } catch (ServerAddressNotSuppliedException e) {
          System.out.printf("ServerAddressNotSuppliedException: %s", e.getMessage());
          e.printStackTrace();
      }
    }
}
