package com.example.digg.api;

import com.example.digg.application.usecase.CreateCustomerCommand;
import com.example.digg.application.usecase.CreateCustomerUseCase;
import com.example.digg.application.usecase.GetCustomersUseCase;
import com.example.digg.domain.model.Address;
import com.example.digg.domain.model.Email;
import com.example.digg.domain.model.Name;
import com.example.digg.domain.model.Telephone;
import com.example.digg.util.CreateCustomerError;
import com.example.digg.util.GetCustomersError;
import com.example.digg.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer management operations")
class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomersUseCase getCustomersUseCase;


    @Operation(
            summary = "Create a new customer",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Customer created successfully",
                            headers = {
                                    @Header(
                                            name = "CustomerId",
                                            description = "ID of the created customer",
                                            schema = @Schema(implementation = String.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {

        final CreateCustomerCommand command = CreateCustomerUseCase.builder()
                .name(Name.of(createCustomerRequest.name()))
                .address(Address.of(createCustomerRequest.address()))
                .email(Email.of(createCustomerRequest.email()))
                .telephone(Telephone.of(createCustomerRequest.telephone()))
                .build();

        return switch (createCustomerUseCase.execute(command)) {
            case Result.Success<CreateCustomerError, com.example.digg.domain.model.Customer> success ->
                    ResponseEntity.status(HttpStatus.CREATED)
                            .header("CustomerId", success.value().customerId().getValue())
                            .build();
            case Result.Reject<CreateCustomerError, com.example.digg.domain.model.Customer> __ ->
                    ResponseEntity.internalServerError().build();
        };
    }


    @Operation(
            summary = "Get all customers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of customers currently in database",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Customer.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomers() {
        return switch (getCustomersUseCase.execute(GetCustomersUseCase.builder().build())) {
            case Result.Success<GetCustomersError, List<com.example.digg.domain.model.Customer>> success ->
                    ResponseEntity.ok(mapCustomers(success.value()));
            case Result.Reject<GetCustomersError, List<com.example.digg.domain.model.Customer>> v ->
                    ResponseEntity.internalServerError().build();
        };
    }

    private List<Customer> mapCustomers(List<com.example.digg.domain.model.Customer> customers) {
        return customers.stream()
                .map(this::mapCustomer)
                .toList();
    }

    private Customer mapCustomer(com.example.digg.domain.model.Customer customer) {
        return Customer.builder()
                .id(customer.customerId().getValue())
                .name(customer.name().getValue())
                .address(customer.address().getValue())
                .email(customer.email().getValue())
                .telephone(customer.telephone().getValue())
                .build();
    }
}
