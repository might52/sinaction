package org.might.sinaction.db.repositories;

import org.might.sinaction.db.entity.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
