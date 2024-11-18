package org.might.sinaction.messaging;

import org.might.sinaction.db.entity.TacoOrder;

public interface OrderReceiver {

    TacoOrder receiveOrder();
}
