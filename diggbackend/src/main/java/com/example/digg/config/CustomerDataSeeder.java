package com.example.digg.config;

import com.example.digg.domain.CustomerRepository;
import com.example.digg.domain.model.Address;
import com.example.digg.domain.model.Customer;
import com.example.digg.domain.model.CustomerId;
import com.example.digg.domain.model.Email;
import com.example.digg.domain.model.Name;
import com.example.digg.domain.model.Telephone;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
//@Profile({"dev", "test"})
@AllArgsConstructor
public class CustomerDataSeeder {
    private final CustomerRepository customerRepository;

    @PostConstruct
    public void init() {
        generateSampleCustomers().forEach(customerRepository::createCustomer);
    }

    public static List<Customer> generateSampleCustomers() {
        List<String> names = List.of(
                "Alice Smith", "Bob Johnson", "Charlie Brown", "Diana Prince", "Ethan Hunt",
                "Fiona Gallagher", "George Michael", "Hannah Baker", "Ian Malcolm", "Jane Doe",
                "Karl Urban", "Laura Palmer", "Mike Ross", "Nina Dobrev", "Oscar Isaac"
        );

        return IntStream.range(0, names.size())
                .mapToObj(i -> {
                    String nameStr = names.get(i);
                    String emailStr = nameStr.toLowerCase().replace(" ", ".") + "@example.com";
                    String addressStr = (100 + i) + " Main Street, Cityville";
                    String phoneStr = "+123456789" + String.format("%02d", i);

                    return new Customer(
                            CustomerId.generate(),
                            Name.of(nameStr),
                            Address.of(addressStr),
                            Email.of(emailStr),
                            Telephone.of(phoneStr)
                    );
                })
                .toList();
    }
}
