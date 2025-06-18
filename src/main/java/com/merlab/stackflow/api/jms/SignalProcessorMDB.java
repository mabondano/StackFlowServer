
package com.merlab.stackflow.api.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import com.merlab.stackflow.service.SignalServiceLocal;
import com.merlab.stackflow.service.SignalServiceLocal;
import jakarta.ejb.EJB;

@MessageDriven(
  activationConfig = {
    @ActivationConfigProperty(propertyName="destinationLookup", propertyValue="java:/jms/queue/SignalQueue"),
    @ActivationConfigProperty(propertyName="destinationType",    propertyValue="jakarta.jms.Queue")
  }
)
public class SignalProcessorMDB implements MessageListener {

  @EJB
  private SignalServiceLocal signalService;

  @Override
  public void onMessage(Message msg) {
    // 1) extraer tu SignalDTO del mensaje
    // 2) llamar signalService.processSignal(dto)
  }
}
