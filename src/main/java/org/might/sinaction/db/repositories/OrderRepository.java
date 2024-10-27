package org.might.sinaction.db.repositories;

import org.might.sinaction.db.entity.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
