package com.electromart.electromart.controller;

import com.electromart.electromart.dto.PaymentDTO;
import com.electromart.electromart.service.AddressService;
import com.electromart.electromart.service.PaymentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Constructor for PaymentController.
     *
     * @param paymentService The service responsible for handling payment-related operations.
     */
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Handles GET requests to retrieve all payments.
     *
     * @return A list of PaymentDTO objects representing all payments.
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if no payments are found.
     */
    @GetMapping({"", "/"})
    public List<PaymentDTO> fetchAllPayments() {
        List<PaymentDTO> paymentList = paymentService.getAllPayments();
        if (paymentList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No payments found.");
        } else {
            return paymentList;
        }
    }

    /**
     * Handles GET requests to retrieve a specific payment by ID.
     *
     * @param id The ID of the payment to retrieve.
     * @return ResponseEntity with the PaymentDTO object if found, or NOT_FOUND status with
     * a message if not found.
     */
    @GetMapping("/payment_id={id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        Optional<PaymentDTO> optionalPayment = paymentService.getPaymentById(id);
        if (optionalPayment.isPresent()) {
            return new ResponseEntity<>(optionalPayment.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Payment with ID: '" + id + "' not found.");
        }
    }

    /**
     * Handles POST requests to add a new payment.
     *
     * @param paymentRequest The PaymentDTO object representing the new payment.
     * @return ResponseEntity with a success message and CREATED status.
     */
    @PostMapping({"", "/"})
    public ResponseEntity<String> addNewPayment(@RequestBody PaymentDTO paymentRequest) {
        paymentService.addPayment(paymentRequest);
        return new ResponseEntity<>(
            "The requested payment was created successfully.", HttpStatus.CREATED);
    }

    /**
     * Handles DELETE requests to delete a payment by ID.
     *
     * @param id The ID of the payment to delete.
     * @return ResponseEntity with a success message if deleted, or
     * NOT_FOUND status with an error message if not found.
     */
    @DeleteMapping("/payment_id={id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.ok().body("Payment with ID: '"
                + id + "' successfully deleted.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Payment with ID: '" + id + "' was not found.");
        }
    }

    /**
     * Handles HTTP message not readable exceptions.
     *
     * @param e The HttpMessageNotReadableException object.
     * @return ResponseEntity with a BAD_REQUEST status and an error message.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid JSON payload. Please ensure that all the data types are correct.");
    }
}