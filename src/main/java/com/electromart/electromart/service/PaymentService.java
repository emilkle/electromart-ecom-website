package com.electromart.electromart.service;

import com.electromart.electromart.dto.PaymentDTO;
import com.electromart.electromart.entity.CreditCard;
import com.electromart.electromart.entity.Cryptocurrency;
import com.electromart.electromart.entity.Klarna;
import com.electromart.electromart.entity.Order;
import com.electromart.electromart.entity.Payment;
import com.electromart.electromart.entity.Shipping;
import com.electromart.electromart.entity.Vipps;
import com.electromart.electromart.repository.PaymentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    /**
     * Fetches all product reviews stored in the database.
     *
     * @return A list of ProductReviewDTO objects corresponding to all
     * product reviews in the database.
     */
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Adds a new payment to the database using a PaymentDTO object.
     *
     * @param paymentDTO The PaymentDTO object representing the payment to be added.
     */
    public void addPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        paymentRepository.save(payment);
    }

    /**
     * Fetches a specific payment based on the payment ID.
     *
     * @param id The ID of the desired payment.
     * @return An Optional containing a PaymentDTO that matches the specified ID,
     * or an empty Optional if no payment is found.
     */
    public Optional<PaymentDTO> getPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.map(this::convertToDTO);
    }

    /**
     * Deletes a payment from the database based on a specified payment ID.
     *
     * @param id The ID of the payment to be deleted.
     * @throws ResponseStatusException If no payment with the specified ID is found.
     */
    public void deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No payment found with ID: " + id);
        }
    }

    /**
     * Converts a Payment entity object into a PaymentDTO data transfer object.
     *
     * @param payment The Payment entity object to convert.
     * @return The converted PaymentDTO object.
     */
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        BeanUtils.copyProperties(payment, paymentDTO);
        paymentDTO.setPaymentID(payment.getPaymentID());
        paymentDTO.setOrderID(payment.getOrder().getOrderID());
        return paymentDTO;
    }


    /**
     * Converts a Payment data transfer object into a Payment entity object.
     *
     * @param paymentDTO The PaymentDTO object to convert.
     * @return The converted Payment entity object.
     */
    private Payment convertToEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDTO, payment);
        Order order = new Order();
        order.setOrderID(paymentDTO.getOrderID());
        payment.setOrder(order);
        payment.getOrder().setOrderID(paymentDTO.getOrderID());
        return payment;
    }
}
