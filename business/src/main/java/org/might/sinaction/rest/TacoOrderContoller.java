package org.might.sinaction.rest;

import org.might.sinaction.db.entity.TacoOrder;
import org.might.sinaction.db.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/orders",
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:8080")
public class TacoOrderContoller {

    private final OrderRepository orderRepository;

    @Autowired
    public TacoOrderContoller(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping(path = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TacoOrder putOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder order) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TacoOrder> patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch) {
        TacoOrder patchedTaco = orderRepository.findById(orderId).orElse(null);
        if (patchedTaco == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (patch.getDeliveryName() != null) {
            patchedTaco.setDeliveryName(patch.getDeliveryName());
        }

        if (patch.getDeliveryStreet() != null) {
            patchedTaco.setDeliveryStreet(patch.getDeliveryStreet());
        }

        if (patch.getDeliveryCity() != null) {
            patchedTaco.setDeliveryCity(patch.getDeliveryCity());
        }

        if (patch.getDeliveryState() != null) {
            patchedTaco.setDeliveryState(patch.getDeliveryState());
        }

        if (patch.getDeliveryZip() != null) {
            patchedTaco.setDeliveryZip(patch.getDeliveryZip());
        }

        if (patch.getCcNumber() != null) {
            patchedTaco.setCcNumber(patch.getCcNumber());
        }

        if (patch.getCcExpiration() != null) {
            patchedTaco.setCcExpiration(patch.getCcExpiration());
        }

        if (patch.getCcCVV() != null) {
            patchedTaco.setCcCVV(patch.getCcCVV());
        }

        return new ResponseEntity<>(orderRepository.save(patchedTaco), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }

}
