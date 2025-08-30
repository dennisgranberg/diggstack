import axios from 'axios';
import type { Customer } from '@/types/Customer';

const API_URL = 'http://localhost:8080/v1/customer';

export const getCustomers = async (): Promise<Customer[]> => {
    const response = await axios.get<Customer[]>(API_URL);
    return response.data;
};

export const createCustomer = async (customer: Customer): Promise<void> => {
    await axios.post(API_URL, customer);
};