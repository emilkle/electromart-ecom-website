package com.electromart.electromart.service;

import com.electromart.electromart.entity.*;
import com.electromart.electromart.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    /*
    This method of getting all payments must probably be refactored due to Payment being an abstract class
    public List<Payment> getAllPayments {
        return paymentRepository.findAll();
    }

     */

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
     * @param orderID corresponding order ID
     * @param shippingID corresponding shipping ID
     * @param paymentType method of payment
     * @param paymentDate date of payment
     * @param paymentStatus status of payment
     */
    public void newPayment (Order orderID, Shipping shippingID,  String paymentType, String paymentDate, String paymentStatus) {
        //User clicks on button for desired payment method, given method is used as parameter in this method.
        float totalAmount = calculateTotalPayment(orderID,shippingID);

        switch (paymentType){
            case "Credit card":
                CreditCard creditCardPayment = new CreditCard();
                creditCardPayment.setOrderID(orderID);
                creditCardPayment.setPaymentAmount(totalAmount);
                creditCardPayment.setThirdPartService("PayPal");
                creditCardPayment.setPaymentDate(LocalDate.now().toString());
                creditCardPayment.setPaymentStatus("Status for payment");
                creditCardPayment.setPaymentType("credit card");
                paymentRepository.save(creditCardPayment);
                break;
            case "Klarna":
                Klarna klarnaPayment = new Klarna();
                klarnaPayment.setOrderID(orderID);
                klarnaPayment.setPaymentAmount(totalAmount);
                klarnaPayment.setEmailAddress("EmailAddresshere");
                klarnaPayment.setPaymentDate(LocalDate.now().toString());
                klarnaPayment.setPaymentStatus("Status for payment");
                klarnaPayment.setPaymentType("Klarna");
                paymentRepository.save(klarnaPayment);
                break;
            case "Vipps":
                Vipps vippsPayment = new Vipps();
                vippsPayment.setOrderID(orderID);
                vippsPayment.setPaymentAmount(totalAmount);
                vippsPayment.setPhoneNumber(123415135);
                vippsPayment.setPaymentDate(LocalDate.now().toString());
                vippsPayment.setPaymentStatus("Status for payment");
                vippsPayment.setPaymentType("Vipps");
                paymentRepository.save(vippsPayment);
                break;
            case "Cryptocurrency":
                Cryptocurrency cryptoPayment = new Cryptocurrency();
                cryptoPayment.setOrderID(orderID);
                cryptoPayment.setPaymentAmount(totalAmount);
                cryptoPayment.setCurrencyName("Bitcoin");
                cryptoPayment.setPaymentDate(LocalDate.now().toString());
                cryptoPayment.setPaymentStatus("Status for payment");
                cryptoPayment.setPaymentType("Cryptocurrency");
                paymentRepository.save(cryptoPayment);
                break;
        }
    }
}
