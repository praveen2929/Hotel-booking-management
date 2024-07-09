//package com.ums.controller;
//
//import com.ums.entity.AppUser;
//import com.ums.entity.Booking;
//import com.ums.entity.Property;
//import com.ums.repository.BookingRepository;
//import com.ums.repository.PropertyRepository;
//import com.ums.service.BucketService;
//import com.ums.service.PDFService;
//import com.ums.service.TwilioService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/api/v1/bookings")
//public class BookingController {
//    private BookingRepository bookingRepository;
//    private PropertyRepository propertyRepository;
//    private TwilioService twilioService;
//    private BucketService bucketService;
//    private PDFService pdfService;
//
//    public BookingController(BookingRepository bookingRepository, PropertyRepository propertyRepository, TwilioService twilioService, BucketService bucketService, PDFService pdfService) {
//        this.bookingRepository = bookingRepository;
//        this.propertyRepository = propertyRepository;
//        this.twilioService = twilioService;
//        this.bucketService = bucketService;
//        this.pdfService = pdfService;
//    }
//    @PostMapping("/createBooking")
//    public ResponseEntity<Booking> createBooking(
//            @RequestParam Long propertyId,
//            @RequestBody Booking booking,
//            @AuthenticationPrincipal AppUser user
//            ){
//        Property property = propertyRepository.findById(propertyId).get();
//        int nightlyPrice = property.getNightlyPrice();
//        int totalPrice = nightlyPrice*booking.getTotalNights();
//        //double tax = totalPrice*(18/100);
//
//        booking.setTotalPrice(totalPrice);
//        booking.setProperty(property);
//        booking.setAppUser(user);
//        Booking savedBooking = bookingRepository.save(booking);
//        String filePath = pdfService.generateBookingDetailsPdf(savedBooking);
//
//       // bucketService.uploadFile(filePath,"myairbnb29")
//        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
//
//    }
//
//    @PostMapping("/sendSms")
//    public void sendMessage(){
//        twilioService.sendSms("+919784868829", "Your booking is confirmed. click here");
//    }
//
//}
