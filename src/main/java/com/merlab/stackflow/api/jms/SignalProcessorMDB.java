/*
 * Copyright 2025 Merly Abondano
 *
 * Created:   2025-06-09
 * Author:    Merly Abondano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.merlab.stackflow.api.jms;


public class SignalProcessorMDB {


}

/*
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
*/