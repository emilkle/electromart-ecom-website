package com.electromart.electromart.service;

import com.electromart.electromart.dto.PaymentDTO;
import com.electromart.electromart.dto.ProductReviewDTO;
import com.electromart.electromart.entity.*;
import com.electromart.electromart.repository.PaymentRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
        List<Payment> productReviews = paymentRepository.findAll();
        return productReviews.stream()
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
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
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

    /**
     * Calculates the total payment amount from order total and shipping cost
     * @param order the given order
     * @param shipping the given method of shipping
     * @return the sum of order cost and shipping cost
     */
    public float calculateTotalPayment (Order order, Shipping shipping) {
        float sumOfOrder = order.getOrderTotalAmount();
        float shippingCost = shipping.getShippingCost();
        return sumOfOrder + shippingCost;
    }

    /**
     * Creates a new payment and checks what kind of payment is to be made
     * @param order corresponding order ID
     * @param shippingID corresponding shipping ID
     * @param paymentType method of payment
     * @param paymentDate date of payment
     * @param paymentStatus status of payment
     */
 /*   public void newPayment (Order order, Shipping shippingID,  String paymentType, String paymentDate, String paymentStatus) {
        //User clicks on button for desired payment method, given method is used as parameter in this method.
        float totalAmount = calculateTotalPayment(order,shippingID);

        switch (paymentType){
            case "Credit card":
                CreditCard creditCardPayment = new CreditCard();
                creditCardPayment.setOrderID(order);
                creditCardPayment.setPaymentAmount(totalAmount);
                creditCardPayment.setThirdPartService("PayPal");
                creditCardPayment.setPaymentDate(LocalDate.now().toString());
                creditCardPayment.setPaymentStatus("Status for payment");
                creditCardPayment.setPaymentType("credit card");
                paymentRepository.save(creditCardPayment);
                break;
            case "Klarna":
                Klarna klarnaPayment = new Klarna();
                klarnaPayment.setOrderID(order);
                klarnaPayment.setPaymentAmount(totalAmount);
                klarnaPayment.setEmailAddress("EmailAddresshere");
                klarnaPayment.setPaymentDate(LocalDate.now().toString());
                klarnaPayment.setPaymentStatus("Status for payment");
                klarnaPayment.setPaymentType("Klarna");
                paymentRepository.save(klarnaPayment);
                break;
            case "Vipps":
                Vipps vippsPayment = new Vipps();
                vippsPayment.setOrderID(order);
                vippsPayment.setPaymentAmount(totalAmount);
                vippsPayment.setPhoneNumber(123415135);
                vippsPayment.setPaymentDate(LocalDate.now().toString());
                vippsPayment.setPaymentStatus("Status for payment");
                vippsPayment.setPaymentType("Vipps");
                paymentRepository.save(vippsPayment);
                break;
            case "Cryptocurrency":
                Cryptocurrency cryptoPayment = new Cryptocurrency();
                cryptoPayment.setOrderID(order);
                cryptoPayment.setPaymentAmount(totalAmount);
                cryptoPayment.setCurrencyName("Bitcoin");
                cryptoPayment.setPaymentDate(LocalDate.now().toString());
                cryptoPayment.setPaymentStatus("Status for payment");
                cryptoPayment.setPaymentType("Cryptocurrency");
                paymentRepository.save(cryptoPayment);
                break;
        }
    }*/
}
